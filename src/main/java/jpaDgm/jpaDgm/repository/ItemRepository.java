package jpaDgm.jpaDgm.repository;

import jpaDgm.jpaDgm.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) { return em.find(Item.class, id); }

    public List<Item> findAll() {
        return em.createQuery("SELECT i FROM Item i", Item.class)
                .getResultList();
    }

    public List<Item> findByCategory(String category) {
        return em.createQuery("SELECT i FROM Item i WHERE i.category =: category", Item.class)
                .setParameter("category", category)
                .getResultList();
    }

    //새값 추가되는 상황 에러 수정 필요
    public void updateItem (Item item) {
        em.merge(item);
    }


    public void DeleteItem (Long id) {
         em.createQuery("DELETE from Item i WHERE i.id = :id")
                .setParameter("id", id).executeUpdate();
    }
}
