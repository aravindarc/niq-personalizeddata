package com.niq.personalizeddata.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductsRequest {
    private String shopperId;
    private String category;
    private String brand;
    private long limit;
    private long offset;
}
