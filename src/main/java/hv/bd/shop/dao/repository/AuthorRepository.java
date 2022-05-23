package hv.bd.shop.dao.repository;

import hv.bd.shop.dao.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "select id,authorname from author a where a.authorname = :authorName",
            nativeQuery = true)
    Author findByName(String authorName);
}
