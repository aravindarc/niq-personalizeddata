package com.niq.personalizeddata;

import com.niq.personalizeddata.models.AddToShelfRequest;
import com.niq.personalizeddata.models.Product;
import com.niq.personalizeddata.repositories.ProductRepo;
import com.niq.personalizeddata.services.ProductService;
import com.niq.personalizeddata.services.ShopperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")

class PersonalisedApplicationTests {
    @Autowired
    private ProductService productService;

    @Autowired
    private ShopperService shopperService;

    @Test
    void saveToShelf() {
        productService.addProduct(new Product("1000", "category1", "brand1"));
        productService.addProduct(new Product("2000", "category2", "brand2"));

        shopperService.addToShelf(new AddToShelfRequest("100", List.of(
                new AddToShelfRequest.Shelf("1000", 10.0),
                new AddToShelfRequest.Shelf("2000", 20.0)
        )));

        assert shopperService.getProductsByShopper("100", null, null, 10).size() == 2;
    }

    @Test
    @Sql(scripts = {"insert-products.sql", "insert-shelves.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getProductsForShopperId() {
        // 5 products are added to shelf for shopper 1
        assert shopperService.getProductsByShopper("1", null, null, 10).spliterator().getExactSizeIfKnown() == 5;

        // 2 products are added to shelf for shopper 1 in electronics category
        assert shopperService.getProductsByShopper("1", "electronics", null, 10).spliterator().getExactSizeIfKnown() == 2;

        // 1 product is added to shelf for shopper 1 in electronics category and brand Apple
        assert shopperService.getProductsByShopper("1", "electronics", "Apple", 10).spliterator().getExactSizeIfKnown() == 1;

        // check if limit is working
        assert shopperService.getProductsByShopper("1", null, null, 1).spliterator().getExactSizeIfKnown() == 1;
    }
}
