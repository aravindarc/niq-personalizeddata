package com.niq.personalizeddata.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // required
    @NonNull
    private String shopperId;

    @OneToOne
    @NonNull
    private Product product;

    // required arg
    @NonNull
    private Double relevancyScore;
}
