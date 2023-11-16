package jpaDgm.jpaDgm.service;

import jpaDgm.jpaDgm.domain.*;
import jpaDgm.jpaDgm.repository.ItemRepository;
import jpaDgm.jpaDgm.repository.MemberRepository;
import jpaDgm.jpaDgm.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count, String order_num) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Orders orders = Orders.createOrder(member, delivery, order_num,orderItem);

        orderRepository.save(orders);

        return orders.getId();
    }

    /**
     * 주문 취소
     * */

    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 취소
        Orders orders = orderRepository.findOne(orderId);

        //주문 취소
        orders.cancel();
        //JPa의 변경내역 감지에 의해서 Entity의 바뀐 필드값을 읽어 update query가 날라간다.
    }

    /**
     * 검색
     * */
    //임시 주문 조회
    @Transactional
    public List<Orders> findByOrders(Long memberId) { return orderRepository.findByMemberId(memberId); }

    public List<Orders> findAllOrders() { return orderRepository.findAllOrders(); }
    @Transactional
    public List<Orders> findSearchOrders(OrderSearch orderSearch) { return orderRepository.findAllByString(orderSearch);}


    public Orders findById(Long orderId) { return orderRepository.findOne(orderId); }
}
