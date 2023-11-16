package jpaDgm.jpaDgm;


import jpaDgm.jpaDgm.domain.Address;
import jpaDgm.jpaDgm.domain.Item;
import jpaDgm.jpaDgm.domain.Member;
import jpaDgm.jpaDgm.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/***
 * 빠르게 초기 값 세팅을 위한 DB이기 떄문에 yml create가 아니고 update일시 주석 처리 필요
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {

        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            //첫 관리자 생성
            Member member = createAdmin("admin@admin.com", "1234", "관리자", "01011112222",
                    "서울 양천구 ", "상세주소", "우편번호");
            em.persist(member);
            Member member1 = createMember("test@test.com", "1234", "테스터", "01011112222",
                    "테스트 주소", "101호", "000000");
            em.persist(member1);
            
            //아이템 생성
            Item item = createItem("프로틴1", 100000, 10, "프로틴입니다", "protein", "1Kg");
            em.persist(item);

            Item item2 = createItem("프로틴2", 200000, 10, "다른 프로틴입니다", "protein", "5Kg");
            em.persist(item2);

            Item item3 = createItem("우레탄 스트렙", 200000, 10, "우레탄을 사용한 스트렙", "equipment", "우레탄");
            em.persist(item3);

        }

        private Member createAdmin(String email, String pw, String name, String phone, String addr, String detail_addr, String zipcode) {
            Member member = new Member();
            member.setEmail(email);
            member.setPw(pw);
            member.setName(name);
            member.setPhone(phone);
            member.setRole(Role.ADMIN);
            member.setAddress(new Address(addr, detail_addr, zipcode));
            return member;
        }

        private Member createMember(String email, String pw, String name, String phone, String addr, String detail_addr, String zipcode) {
            Member member = new Member();
            member.setEmail(email);
            member.setPw(pw);
            member.setName(name);
            member.setPhone(phone);
            member.setRole(Role.USER);
            member.setAddress(new Address(addr, detail_addr, zipcode));
            return member;
        }

        private Item createItem(String name, int price, int quantity, String info, String category, String categoryDetail) {
            Item item = new Item();
            item.setName(name);
            item.setPrice(price);
            item.setStockQuantity(quantity);
            item.setInfo(info);
            item.setCategory(category);
            item.setCategoryDetail(categoryDetail);
            return item;
        }

    }


}
