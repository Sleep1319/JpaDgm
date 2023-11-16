package jpaDgm.jpaDgm.domain;

//import jpaSparta.jpaProject.domain.board.Comment;
//import jpaSparta.jpaProject.domain.board.Review;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@DynamicUpdate
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "user_pw")
    private String pw;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_phone")
    private String phone;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;


    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();


//    @OneToMany(mappedBy = "member")
//    private List<Review> reviews = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Comment> comments = new ArrayList<>();
}
