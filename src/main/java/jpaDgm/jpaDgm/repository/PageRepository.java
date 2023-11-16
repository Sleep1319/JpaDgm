package jpaDgm.jpaDgm.repository;


import jpaDgm.jpaDgm.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PageRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String title);
    //    Page<Board> findByTitleOOrContents(String title);
    List<Board> findByTitleOrContents(String title, String contents);
    Page<Board> findByTitleContainingOrContentsContaining(String title, String contents, Pageable pageable);

}
