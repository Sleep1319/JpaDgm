package jpaDgm.jpaDgm.service;


import jpaDgm.jpaDgm.domain.Board;
import jpaDgm.jpaDgm.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 작성
    @Transactional
    public void saveBoard(Board board) { boardRepository.save(board); }

    // 게시글 조회
    public List<Board> findBoardList() { return boardRepository.findAll(); }

    @Transactional
    // 게시글 수정
    public void updateBoard(Long id, String title, String contents) {
        Board board = boardRepository.findById(id);
        board.setTitle(title);
        board.setContents(contents);
    }
    @Transactional
    // 게시글 삭제
    public void deleteBoard(Long id) { boardRepository.delete(id); }

    //    // 게시글 상세 조회
    public Board detailBoard(Long id) { return boardRepository.findById(id); }
}
