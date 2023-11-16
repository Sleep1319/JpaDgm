package jpaDgm.jpaDgm.service;

import jpaDgm.jpaDgm.domain.Member;
import jpaDgm.jpaDgm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public List<Member> findMember() { return memberRepository.findAll(); }

    public Member findOne(Long member_Id) { return memberRepository.findOne(member_Id); }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }

    //로그인 기능
    public Member tryLogin(String email, String pw) {
        return memberRepository.tryLogin(email, pw);
    }

    //회원 정보 수정
    @Transactional
    public void updateUserInfo (Member member) {
        memberRepository.updateUserInfo(member);
    }

    @Transactional
    public void DeleteMember(Long id) { memberRepository.DeleteMember(id); }

//    /**
//     *      페이징 관련
//     * */
//    private int start;
//    private int end;
//
//    private int maxPage;

//    public List<Member> getBoardPage(int firstPage, int maxPage) {
//        this.maxPage = maxPage;
//        return memberRepository.getBoardList(firstPage, maxPage);
//    }

//    public void setStartEndPage(int currentPage) {
//        int maxPage = (memberRepository.getRowCount() / this.maxPage) + 1;
//
//        int unit = 5;    // 나타낼 페이징 수 5개씩
//        int firstPage = currentPage / unit + 1;
//        int lastPage = firstPage + unit - 1;  // 첫 페이지 + 4
//
//        if (lastPage < 1) {
//            firstPage = 1;
//        }
//        this.start = firstPage;
//
//        if (lastPage > maxPage) {
//            this.end = maxPage;
//        } else {
//            this.end = lastPage;
//        }
//    }

//    public int getStart() { return this.start; }
//
//    public int getEnd() { return this.end; }
//
////    public long getAllMemberCount() { return memberRepository.getRowCount(); }
//
    public Member getLoginMember(HttpSession session) {
        try {
            return (Member) session.getAttribute("member");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getLoginMemberId(HttpSession session) {
        Member member = getLoginMember(session);
        return member.getId();
    }

//    public Role getRole(HttpSession session) {
//        Member member = getLoginMember(session);
//        return member.getRole();
//    }

//    public Boolean notAdmin(HttpSession session) {
//        if (getLoginMember(session).getRole() == Role.ADMIN) {
//            return false;
//        }
//        return true;
//    }
}
