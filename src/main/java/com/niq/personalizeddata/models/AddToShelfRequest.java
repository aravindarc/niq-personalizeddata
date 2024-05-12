package com.niq.personalizeddata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AddToShelfRequest {
    private String shopperId;
    private List<Shelf> shelf;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Shelf {
        private String productId;
        private Double relevancyScore;
    }
}
