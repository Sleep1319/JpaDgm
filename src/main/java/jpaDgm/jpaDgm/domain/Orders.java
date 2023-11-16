package jpaDgm.jpaDgm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "order_date")
    private LocalDateTime order_date;

    @Column(name = "order_num")
    private String order_num;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //---------------------------
    public void setMember (Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }

    public static Orders createOrder(Member member, Delivery delivery, String order_num, OrderItem... orderItems) { // ... = 가변인자 오더아이템들의 들어온 정보수만큼 증가 되게 하기 위합
        Orders orders = new Orders();
        orders.setMember(member);
        orders.setDelivery(delivery);
        orders.setOrder_num(order_num);
        for( OrderItem orderItem : orderItems) {
            orders.addOrderItem(orderItem);
        }

        orders.setStatus(OrderStatus.ORDER);
        orders.setOrder_date(LocalDateTime.now());

        return orders;//오더 정보를 리턴
    }

    /**
     *      주문 취소
     * */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가함");
        }

        this.setStatus(OrderStatus.CANCLE);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

        public int getTotalPrice() {
            int totalPrice = 0;
            for( OrderItem orderItem : orderItems ) {
                totalPrice += orderItem.getTotalPrice();
            }
            return totalPrice;
        }
}
