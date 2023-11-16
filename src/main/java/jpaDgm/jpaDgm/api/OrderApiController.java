package jpaDgm.jpaDgm.api;

import jpaDgm.jpaDgm.domain.Item;
import jpaDgm.jpaDgm.dto.OrderDTO;
import jpaDgm.jpaDgm.service.ItemService;
import jpaDgm.jpaDgm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;
    private final ItemService itemService;

    @PostMapping("/orderInsert/api")
    public ResponseEntity<Map<String, Object>> InsertOrder (@RequestBody @Valid OrderDTO orderDTO, HttpSession session) {
    Map<String, Object> response = new HashMap<>();
    if(session.getAttribute("member_id") == null) {
        response.put("message", "로그인 상태가 아닙니다");
        response.put("status", 400);
        return ResponseEntity.ok(response);
    }

        Long member_id = 0L;
        member_id = (Long) session.getAttribute("member_id");
        orderDTO.setMember_id(member_id);
        try {
            orderService.order(orderDTO.getMember_id(), orderDTO.getItem_id(), orderDTO.getCount(), orderDTO.getOrder_num());
            response.put("message", "오더 등록 성공");
            response.put("status", 200);
            return ResponseEntity.ok(response);
        } catch (IllegalAccessError e) {
            response.put("message", "오더 등록 실패");
            response.put("status", 400);
            return ResponseEntity.ok(response);
        }
    }

}
