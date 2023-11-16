package jpaDgm.jpaDgm.repository;

import jpaDgm.jpaDgm.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Board board) { em.persist(board); }

    public Board findById(Long id) { return em.find(Board.class, id); }

    public List<Board> findAll() {
        return em.createQuery("SELECT b FROM Board b", Board.class)
                .getResultList();
    }

    public void delete(Long id) {
        em.createQuery("DELETE FROM Board b WHERE b.id =:id")
                .setParameter("id", id).executeUpdate();
    }

}
