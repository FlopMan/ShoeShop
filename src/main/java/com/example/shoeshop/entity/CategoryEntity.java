package com.example.shoeshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "statusdisplay")
    private Long statusDisplay;
    @OneToMany(mappedBy = "categoryEntity",fetch = FetchType.EAGER)
    private List<ProductEntity> productEntityList = new ArrayList<>();
}
