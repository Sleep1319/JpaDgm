package jpaDgm.jpaDgm.api;

import jpaDgm.jpaDgm.domain.Address;
import jpaDgm.jpaDgm.domain.Item;
import jpaDgm.jpaDgm.domain.Member;
import jpaDgm.jpaDgm.form.ItemForm;
import jpaDgm.jpaDgm.form.MemberAdminUpdateForm;
import jpaDgm.jpaDgm.form.MemberForm;
import jpaDgm.jpaDgm.form.MemberUpdateForm;
import jpaDgm.jpaDgm.service.ItemService;
import jpaDgm.jpaDgm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminApiController {
    private final MemberService memberService;
    private final ItemService itemService;

    @PutMapping("/memberUpdate")
    public ResponseEntity<Map<String, Object>> editMember(@RequestBody @Valid MemberAdminUpdateForm form, BindingResult result, Model model) {
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            response.put("message", "비어 있거나 공백이 있는 내용이 있습니다");
            response.put("status", 400);
            return ResponseEntity.ok(response);
        }
        Address address = new Address(form.getAddr(),form.getDetail_addr(), form.getZipcode());
        Member member = new Member();
        member.setId(form.getId());
        member.setPhone(form.getPhone());
        member.setRole(form.getRole());
        member.setAddress(address);

        try {
            memberService.updateUserInfo(member);
            response.put("message", "수정 성공");
            response.put("status", 200);
            return ResponseEntity.ok(response);

        } catch (IllegalAccessError e) {
            response.put("message", "수정 실패");
            response.put("status", 400);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping(value = "/members/{id}/delete")
    public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            memberService.DeleteMember(id);
            response.put("message", "삭제 성공");
            response.put("status", 200);
            return ResponseEntity.ok(response);

        } catch (IllegalAccessError e) {
            response.put("message", "삭제 실패");
            response.put("status", 400);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //아이템 관련

    @PutMapping("/itemUpdate/api")
    public ResponseEntity<Map<String, Object>> itemUpdate(@RequestBody @Valid ItemForm form) {
        Map<String, Object> response = new HashMap<>();

        Item item = new Item();
        item.setId(form.getId());
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());
        item.setInfo(form.getInfo());
        item.setCategory(form.getCategory());
        item.setCategoryDetail(form.getCateDetail());
        try {
            itemService.updateItem(item);
            response.put("message", "수정 성공");
            response.put("status", 200);
            return ResponseEntity.ok(response);
        } catch (IllegalAccessError e) {
            response.put("message", "수정 실패");
            response.put("status", 400);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping(value = "/item/{id}/delete")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable("id") Long itemId) {
        Map<String, Object> response = new HashMap<>();
        try {
            itemService.DeleteItem(itemId);
            response.put("message", "삭제 성공");
            response.put("status", 200);
            return ResponseEntity.ok(response);

        } catch (IllegalAccessError e) {
            response.put("message", "삭제 실패");
            response.put("status", 400);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}


