package jpaDgm.jpaDgm.api;

import jpaDgm.jpaDgm.domain.Board;
import jpaDgm.jpaDgm.form.BoardForm;
import jpaDgm.jpaDgm.repository.BoardRepository;
import jpaDgm.jpaDgm.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
    private  final BoardService boardService;

//    private final BoardRepository boardRepository;
//
//    @GetMapping(value = "/api/boards")
//    public List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
//                           @RequestParam(required = false, defaultValue = "") String contents) {
//        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(contents)) {
//            return boardRepository.findAll();
//        } else {
//            return boardRepository.findByTitleOrContents(title, contents);
//        }
//    }
//    @PostMapping(value = "/api/boards")
//    Board newBoard(@RequestBody Board newBoard) { return boardRepository.save(newBoard); }
//
//    @PutMapping("/api/boards/{id}")
//    Board updateBoard(@RequestBody Board newBoard, @PathVariable Long id) {
//        return boardRepository.findById(id)
//                .map(board -> {
//                    board.setTitle(newBoard.getTitle());
//                    board.setContents(newBoard.getContents());
//                    return boardRepository.save(board);
//                })
//                .orElseGet(() -> {
//                    newBoard.setId(id);
//                    return boardRepository.save(newBoard);
//                });
//    }
//
//    @DeleteMapping(value = "/api/boards/{id}")
//    void deleteBoard(@PathVariable Long id) { boardRepository.deleteById(id); }

    //
    @PutMapping(value = "/boardUpdate")
    public ResponseEntity<Map<String, Object>> Board(@RequestBody BoardForm form) {
        Map<String, Object> response = new HashMap<>();
        try {
            boardService.updateBoard(form.getId(), form.getTitle(), form.getContents());
            response.put("message", "수정 성공");
            response.put("status", 200);
            return ResponseEntity.ok(response);
        } catch (IllegalAccessError e) {
            response.put("message", "수정 실패");
            response.put("status", 400);
            return ResponseEntity.ok(response);
        }

    }

    @DeleteMapping(value = "/board/{id}/delete")
    public ResponseEntity<Map<String, Object>> deleteBoard(@PathVariable("id") Long boardId) {
        Map<String, Object> response = new HashMap<>();
        try {
            boardService.deleteBoard(boardId);
            response.put("message", "삭제 성공");
            response.put("status", 200);
            return ResponseEntity.ok(response);

        } catch (IllegalAccessError e) {
            response.put("message", "삭제 실패");
            response.put("status", 400);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
