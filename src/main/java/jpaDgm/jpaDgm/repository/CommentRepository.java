package jpaDgm.jpaDgm.repository;

import jpaDgm.jpaDgm.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Comment comment) { em.persist(comment); }

    public Comment findById(Long id) { return em.find(Comment.class, id); }

    public void delete(Comment comment) {
        em.remove(comment);
    }

    public List<Comment> findAll() {
        return em.createQuery("SELECT c FROM Comment c", Comment.class)
                .getResultList();
    }
}
