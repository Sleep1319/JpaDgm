package jpaDgm.jpaDgm.controller;

import jpaDgm.jpaDgm.domain.*;
import jpaDgm.jpaDgm.form.LoginForm;
import jpaDgm.jpaDgm.form.RegisterForm;
import jpaDgm.jpaDgm.service.MemberService;
import jpaDgm.jpaDgm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final OrderService orderService;
    //GetMapping

    /**
     *
     */

    //회원가입 html
    @GetMapping("/register")
    public String signup(Model model) {
        model.addAttribute("memberForm", new LoginForm());
        return "members/register";
    }

    //로그인 html
    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if(session.getAttribute("member_id") != null) {
            model.addAttribute("message", "이미 로그인이 된 상태입니다");
            return "index";
        }
        return "members/login";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //아이디 찾기 html
//    @GetMapping("/findId")
//    public String findId() {
//        return "members/findId";
//    }
//
//    //비밀번호 찾시 html
//    @GetMapping("/findPwd")
//    public String findPwd() {
//        return "member/findPwd";
//    }

    //유저정보 html
    @GetMapping("/myPage")
    public String myPage(Model model, HttpSession session) {
        if (session.getAttribute("member_id") == null){
            model.addAttribute("message", "로그인 후 이용해주세요");
            return "members/login";
        }

        Object id = session.getAttribute("member_id");
        if (id instanceof Long) {
            Long member_id = (Long) id;
            Member member = memberService.findOne(member_id);
            model.addAttribute("phone", member.getPhone());
            model.addAttribute("address", member.getAddress());
            return "members/myPage";
        } else {
            model.addAttribute("message", "고유 아이디 확인 실패");
            return "index";
        }
    }

    //유저 주문 조회
    @Transactional
    @GetMapping("/userOrderList")
    public String userOrderList(Model model, HttpSession session) {
        if (session.getAttribute("member_id") == null){
            model.addAttribute("message", "로그인 후 이용 해주세요");
            return "members/login";
        }

        Object id = session.getAttribute("member_id");
        Long member_id = 0L;
        if (id instanceof Long) {
            member_id = (Long) id;
        }
        try {
//            Member member = memberService.findOne(member_id);
//            Hibernate.initialize(member.getOrders());
//            model.addAttribute("userOrderList", member.getOrders());
            List<Orders> ordersList = orderService.findByOrders(member_id);
            model.addAttribute("userOrderList", ordersList);

        } catch (IllegalAccessError e) {
            model.addAttribute("message", "주문 목록이 없습니다");
        }
        return "members/userOrderList";
    }
    //PostMaping


    //로그인 요청
    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginForm form, BindingResult result,
                        Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("message", "아이디 또는 비밀번호를 입력해 주십시오.");
            return "members/login";
        }
        Member member = memberService.tryLogin(form.getEmail(), form.getPw());
        if (member.getId() != null){
            session.setAttribute("member_id", member.getId());//첫 멤버변수값
            session.setAttribute("email", member.getEmail());
            session.setAttribute("name", member.getName());//4번째 값 name
            session.setAttribute("role", member.getRole());
            model.addAttribute("message", "로그인 성공");
            return "redirect:/";
        } else {
            model.addAttribute("message", "아이디 또는 비밀 번호가 다릅니다!");
            return "members/login";
        }
    }

    @PostMapping("/register")
    public String signup(@ModelAttribute @Valid RegisterForm form, BindingResult result, Model model) {


        if(result.hasErrors()){
            model.addAttribute("message", "입력하지 않거나 잘못 입력된 사항이 있습니다");
            return "members/register";
        }

        Address address = new Address(form.getAddr(), form.getDetail_addr(), form.getZipcode());

        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());
        member.setName(form.getName());
        member.setPhone(form.getPhone());
        member.setRole(Role.USER);
        if (form.getName().equals("admin")) {
            member.setRole(Role.ADMIN);
        }
        member.setAddress(address);

        try {
            memberService.join(member);
            model.addAttribute("message", "회원 가입 성공");
            return "members/login";
        } catch (IllegalAccessError e) {
            model.addAttribute("message", "회원 가입 실패");
            return "members/register";
        }
    }


}
