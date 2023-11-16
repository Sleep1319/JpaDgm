package jpaDgm.jpaDgm.controller;

import jpaDgm.jpaDgm.domain.Item;
import jpaDgm.jpaDgm.domain.Member;
import jpaDgm.jpaDgm.service.ItemService;
import jpaDgm.jpaDgm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/item/{id}/detail")
    public String userItemDetail(@PathVariable("id") Long id, Model model) {
        Item item = itemService.findOne(id);
        model.addAttribute("item", item);
        return "items/userItemDetail";
    }

    @GetMapping("/userBuy")
    public String userBuy(@RequestParam("itemId") Long itemId,
                          @RequestParam("count") int count, Model model, HttpSession session) {
        if(session.getAttribute("member_id") == null) {
            model.addAttribute("message", "로그인후 이용");
            return "members/login";
        }
        try{
            Long member_id = (Long) session.getAttribute("member_id");
            Member member = memberService.findOne(member_id);
            Item item = itemService.findOne(itemId);
            model.addAttribute("phone", member.getPhone());
            model.addAttribute("address", member.getAddress());
            model.addAttribute("item", item);
            model.addAttribute("count", count);
        } catch (IllegalAccessError e) {
            model.addAttribute("message", "아이템 조회 에러");
        }
        return "items/userBuy";
    }

}
