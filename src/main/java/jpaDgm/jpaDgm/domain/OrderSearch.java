package jpaDgm.jpaDgm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String member_id; // 멤버 고유값


    /**
     *      23.11.02 동현
     * */
    private OrderStatus orderStatus; // 주문 상태
    private DeliveryStatus deliveryStatus; // 배송 상태
}
