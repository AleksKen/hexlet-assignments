package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findAllByPriceBetweenOrderByPrice(Integer startPrice, Integer endPrice);
    List<Product> findAllByPriceLessThanOrderByPrice(Integer price);
    List<Product> findAllByPriceGreaterThanOrderByPrice(int price);
    List<Product> findAllByOrderByPrice();
    // END
}
