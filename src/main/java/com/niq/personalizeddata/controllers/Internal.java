package com.niq.personalizeddata.controllers;

import com.niq.personalizeddata.models.AddToShelfRequest;
import com.niq.personalizeddata.models.Product;
import com.niq.personalizeddata.services.ProductService;
import com.niq.personalizeddata.services.ShopperService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("internal")
public class Internal {

    private final ProductService productService;
    private final ShopperService shopperService;

    public Internal(ProductService productService, ShopperService shopperService) {
        this.productService = productService;
        this.shopperService = shopperService;
    }

    // write a post api to add a product
    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // write a post api to add to shelf of a shopper
    @PostMapping("/addToShelf")
    public String addToShelf(@RequestBody AddToShelfRequest request) {
        return shopperService.addToShelf(request);
    }
}
