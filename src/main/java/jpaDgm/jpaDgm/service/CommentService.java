package jpaDgm.jpaDgm.service;

import jpaDgm.jpaDgm.domain.Board;
import jpaDgm.jpaDgm.domain.Comment;
import jpaDgm.jpaDgm.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment) { commentRepository.save(comment); }

    @Transactional
    public void deleteComment(Long id) { commentRepository.delete(commentRepository.findById(id)); }

    public List<Comment> findCommentList() { return commentRepository.findAll(); }
}
