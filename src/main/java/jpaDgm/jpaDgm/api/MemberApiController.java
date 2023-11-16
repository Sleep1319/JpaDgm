package jpaDgm.jpaDgm.api;


import jpaDgm.jpaDgm.domain.Address;
import jpaDgm.jpaDgm.domain.Member;
import jpaDgm.jpaDgm.domain.Role;
import jpaDgm.jpaDgm.form.MemberUpdateForm;
import jpaDgm.jpaDgm.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;


    //수정
        @PutMapping ("/myPage/api")
        public ResponseEntity<Map<String, Object>> UpdateMemberInfo(@RequestBody @Valid MemberUpdateForm form, BindingResult result, HttpSession session) {
            Map<String, Object> response = new HashMap<>();

            if(result.hasErrors()){
                response.put("message", "비어 있거나 공백이 있는 내용이 있습니다");
                response.put("status", 400);
                return ResponseEntity.ok(response);
            }

            Long member_id = 0L;
            //세션 아이디 Long형인지 체크
            Object id = session.getAttribute("member_id");
            if (id instanceof Long) {
                member_id = (Long) id;
            } else {
                response.put("message", "수정 실패");
                response.put("status", 400);
                return ResponseEntity.ok(response);
            }
            
            Address address = new Address(form.getAddr(), form.getDetail_addr(), form.getZipcode());

            Member member = new Member();
            member.setId(member_id);
            member.setPhone(form.getPhone());
            member.setRole(Role.USER);
            member.setAddress(address);
            try {
                memberService.updateUserInfo(member);
                response.put("message", "수정 성공");
                response.put("status", 200);
                return ResponseEntity.ok(response);

            } catch (IllegalAccessError e) {
                response.put("message", "디비 에러");
                response.put("status", 400);
                return ResponseEntity.ok(response);
            }
        }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}
