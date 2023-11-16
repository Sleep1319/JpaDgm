package jpaDgm.jpaDgm.repository;

import jpaDgm.jpaDgm.domain.Orders;
import jpaDgm.jpaDgm.domain.Member;
import jpaDgm.jpaDgm.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Orders orders) {
        em.persist(orders);
    }

    public Orders findOne(Long id) {
        return em.find(Orders.class, id);
    }
    //전체 조회
    public List<Orders> findAllOrders() {
        return em.createQuery("SELECT o FROM Orders o", Orders.class)
                .getResultList();
    }

    /***
     * 주문 검색 로직
     * QueryDSL(하이버네이트가 쓰는 고유의 쿼리)이 가장 권장 되는 방식
     * 쿼리가 완벽해야하고 이프로젝트를 다루면서더이상 깊게 들어 갈 수 없음
     */
    //1. JPQL처리부분
    public List<Orders> findAllByString(OrderSearch orderSearch) { //languge=JPQL

        String jpql = "select o from Orders o join o.member m";

        boolean isFirstCondition = true;

        //주문상태 검색
        if(orderSearch.getOrderStatus() != null) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        //회원고유 아이디 검색
        if(StringUtils.hasText(orderSearch.getMember_id())) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.id like :id";
        }
        TypedQuery<Orders> query = em.createQuery(jpql, Orders.class)
//                .setFirstResult(100) //페이지 참고
                .setMaxResults(1000); //최대 천건까지
        if(orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if(StringUtils.hasText(orderSearch.getMember_id())) {
            query = query.setParameter("id", orderSearch.getMember_id());
        }
        return query.getResultList();
    }

    // 2. JPA Criteria로 처리

    /**
     * 설명
     * 빌더에 주문 쿼리를 받는다
     *
     * Specification
     * toPredicate()메소드 사용시 축약
     */
//    public List<Orders> findAllByCriteria(OrderSearch orderSearch) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
//        Root<Orders> o = cq.from(Orders.class);
//        Join<Orders, Member> m = o.join("member", JoinType.INNER); //회원과 조인
//        List<Predicate> criteria = new ArrayList<>();
//        //주문 상태 검색
//        if (orderSearch.getOrderStatus() != null) {
//            Predicate status = cb.equal(o.get("status"),
//                    orderSearch.getOrderStatus());
//            criteria.add(status);
//        }
//        //회원 이름 검색
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            Predicate name =
//                    cb.like(m.<String>get("name"), "%" +
//                            orderSearch.getMemberName() + "%");
//            criteria.add(name);
//        }
//        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
//        TypedQuery<Orders> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
//        return query.getResultList();
//    }

    /**
     *      23.11.02 동현
     * */
    // 주문 전체 조회
    public List<Orders> findAll() {
        return em.createQuery("SELECT o FROM Orders o", Orders.class)
                .getResultList();
    }
    // 주문한 멤버 정보 가져오기
    public List<Orders> findByMemberId(Long memberId) {
        return em.createQuery("SELECT o FROM Orders o WHERE o.member.id =:memberId", Orders.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

}
