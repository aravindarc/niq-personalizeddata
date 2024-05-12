package com.niq.personalizeddata.services;

import com.niq.personalizeddata.models.AddToShelfRequest;
import com.niq.personalizeddata.models.Product;
import com.niq.personalizeddata.models.Shelf;
import com.niq.personalizeddata.repositories.ProductRepo;
import com.niq.personalizeddata.repositories.ShelfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopperService {
    @Autowired
    private ShelfRepo shelfRepo;

    @Autowired
    private ProductRepo productRepo;

    public String addToShelf(AddToShelfRequest request) {
        for (AddToShelfRequest.Shelf shelf : request.getShelf()) {
            Product product = productRepo.findById(shelf.getProductId()).orElseThrow();
            shelfRepo.save(new Shelf(request.getShopperId(), product, shelf.getRelevancyScore()));
        }

        return request.getShopperId();
    }

    public List<Product> getProductsByShopper(String shopperId, String category, String brand, long limit) {
        return shelfRepo.getProductsByShopper(shopperId, category, brand, limit);
    }
}
