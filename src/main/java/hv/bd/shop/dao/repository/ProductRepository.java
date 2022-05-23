package hv.bd.shop.dao.repository;

import hv.bd.shop.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product p where p.id = :id",
            nativeQuery = true)
    Product findProductById(Long id);
}
