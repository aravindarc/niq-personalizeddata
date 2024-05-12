package com.niq.personalizeddata.controllers;

import com.niq.personalizeddata.models.Product;
import com.niq.personalizeddata.repositories.ShelfRepo;
import com.niq.personalizeddata.services.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("external")
public class External {
    @Autowired
    private ShopperService shopperService;

    @GetMapping("/getProducts")
    public List<Product> getProducts(@RequestParam String shopperId,
                                     @RequestParam(required = false) String category,
                                     @RequestParam(required = false) String brand,
                                     @RequestParam(required = false, defaultValue = "10") long limit) {
        if (limit > 100) {
            throw new IllegalArgumentException("Limit cannot be greater than 100");
        }
        return shopperService.getProductsByShopper(shopperId, category, brand, limit);
    }

}
