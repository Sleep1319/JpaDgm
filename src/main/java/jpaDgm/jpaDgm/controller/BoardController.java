package jpaDgm.jpaDgm.controller;

import jpaDgm.jpaDgm.domain.Board;
import jpaDgm.jpaDgm.form.BoardForm;
import jpaDgm.jpaDgm.form.CommentForm;
import jpaDgm.jpaDgm.repository.BoardRepository;
import jpaDgm.jpaDgm.repository.PageRepository;
import jpaDgm.jpaDgm.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final PageRepository pageRepository;


    /**
     *      글 작성
     * */
    @GetMapping(value = "/board/write")
    public String writeForm(Model model) {
        model.addAttribute("form", new BoardForm());
        return "board/boardWrite";
    }

    @PostMapping(value = "/board/write")
    public String write(BoardForm form) {
        Board board = new Board();

        board.setTitle(form.getTitle());
        board.setContents(form.getContents());

        boardService.saveBoard(board);
        return "redirect:/board/list";
    }

    /**
     *      글 목록
     * */
    @GetMapping(value = "/board/list")
    public String list(Model model, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board> boards = pageRepository.findByTitleContainingOrContentsContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);

        List<Board> boardList = boardService.findBoardList();
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    /**
     *      글 상세 페이지
     * */
    @GetMapping(value = "/board/{id}/detail")
    public String detail(@PathVariable("id") Long boardId, Model model) {
        Board board = boardService.detailBoard(boardId);
        model.addAttribute("board", board);
        model.addAttribute("commentForm", new CommentForm());
        return "board/boardDetail";
    }

    /**
     *      글 수정
     * */
    @GetMapping(value = "/board/{id}/update")
    public String updateForm(@PathVariable("id") Long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        BoardForm form = new BoardForm();

        form.setId(board.getId());
        form.setTitle(board.getTitle());
        form.setContents(board.getContents());
//        form.setCreateDate(board.getCreateDate());  작성일
//        form.setUpdateDate(board.getUpdateDate());  수정일

        model.addAttribute("form", form);
        return "board/boardUpdate";
    }

    /**
     *      글 삭제
     * */
    @PostMapping(value = "/board/{id}/delete")
    public String delete(@PathVariable("id") Long baordId) {
        boardService.deleteBoard(baordId);
        return "redirect:/board/list";
    }
}
