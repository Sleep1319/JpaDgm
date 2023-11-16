package jpaDgm.jpaDgm.repository;


import jpaDgm.jpaDgm.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    //로그인
    public Member tryLogin(String email, String pw) {
        Member member = new Member();
        try {
            member = em.createQuery("SELECT m FROM Member m WHERE m.email = :email AND m.pw = :pw", Member.class)
                    .setParameter("email", email)
                    .setParameter("pw", pw)
                    .getSingleResult();
            return member;
        } catch (NoResultException e) {
            return member;
        }
//        return em.createQuery("SELECT m FROM Member m WHERE m.email = :email AND m.pw = :pw", Member.class)
//                .setParameter("email", email)
//                .setParameter("pw", pw)
//                .getSingleResult();
    }

    //회원정보 수정
    public void updateUserInfo(Member member) {
        //값이 있는지 한번더 검증
        Member updateUser = em.find(Member.class, member.getId());
        //변경할 값 대입
            updateUser.setPhone(member.getPhone());
            updateUser.setRole(member.getRole());
            updateUser.setAddress(member.getAddress());
        em.merge(updateUser);

    }

    public void DeleteMember(Long id) {
        em.createQuery("delete from Member m WHERE m.id = :id")
                .setParameter("id", id).executeUpdate();
    }
//    public int updateUserInfo(Long id, String phone, Address address) {
//        return em.createQuery("UPDATE Member m SET m.phone = :phone, m.address.addr = :addr, m.address.detail_addr = :detail_addr, m.address.zipcode = :zipcode" +
//                        " WHERE m.id = :id")
//                .setParameter("phone", phone).setParameter("addr", address.getAddr()).setParameter("detail_addr", address.getDetail_addr())
//                .setParameter("zipcode", address.getZipcode()).setParameter("id", id)
//                .executeUpdate();
//    }

//    // 페이징 관련
//    public List<Member> getBoardList(int first, int max) {
//        int start = (first - 1) * max;
//
//        if(first < 0) {
//            first = 0;
//        }
//
//        System.out.println("first =" + first + ", max =" + max);
//
//        return em.createQuery("select m from Member m ORDER BY m.id DESC", Member.class)
//                .setFirstResult(start)
//                .setMaxResults(max)
//                .getResultList();
//    }
//
//
//    public int getRowCount() {
//        return em.createQuery("SELECT count(m.id) FROM Member m", Long.class)
//                .getFirstResult();
//    }
}
