package jpaDgm.jpaDgm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    //    private String name;
    @NotNull
    @Size(min=2, max=20, message = "제목은 2글자 이상 20글자 이하로 작성하셔야 합니다.")
    private String title;
    @NotNull
    @Size(min = 2, max = 1000, message = "내용을 작성해 주세요.(2글자 이상 1000글자 이하)")
    private String contents;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;
}
