package jpaDgm.jpaDgm.controller;

import jpaDgm.jpaDgm.domain.Board;
import jpaDgm.jpaDgm.domain.Comment;
import jpaDgm.jpaDgm.form.CommentForm;
import jpaDgm.jpaDgm.repository.BoardRepository;
import jpaDgm.jpaDgm.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardRepository boardRepository;

    /**
     *    댓글 작성
     * */
    @PostMapping(value = "/comment/{id}/write")
    public String write(@PathVariable("id") Long commentId, @ModelAttribute("commentForm") CommentForm form, Model model) {

        Board board = boardRepository.findById(commentId);
        if (board == null) {
            return "redirect:/board/list";
        }
        Comment comment = new Comment();

        comment.setWriter(form.getWriter());
        comment.setContents(form.getContents());
        comment.setBoard(board);

        commentService.saveComment(comment);

        model.addAttribute(board);
        return "redirect:/board/{boardId}/detail";
    }
    /**
     *    댓글 목록
     * */
    @GetMapping(value = "/comment/{id}/list")
    public String list(@PathVariable("id") Long commentId, Model model) {
        Board board = boardRepository.findById(commentId);
        List<Comment> commentList = commentService.findCommentList();
        if (board != null) {
            model.addAttribute("commentList", commentList);
            return "board/boardDetail";
        } else {
            return "redirect:/board/list";
        }
    }
    /**
     *    댓글 삭제
     * */

}
