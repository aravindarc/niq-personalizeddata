package com.niq.personalizeddata.repositories;

import com.niq.personalizeddata.models.Product;
import com.niq.personalizeddata.models.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepo extends JpaRepository<Shelf, String> {
    // Get Products by shopper (with filters)
    //○ Shopper ID - String, required
    //○ Category - String, optional
    //○ Brand - String, optional
    //○ Limit - Integer, optional, default = 10, max = 100
    //○ Returns - List of Products
    @Query("SELECT s.product " +
            "FROM Shelf s " +
            "JOIN Product p ON s.product.productId = p.productId " +
            "WHERE s.shopperId = :shopperId " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:brand IS NULL OR p.brand = :brand) " +
            "ORDER BY s.relevancyScore DESC " +
            "LIMIT :limit")
    List<Product> getProductsByShopper(String shopperId, String category, String brand, Long limit);
}
