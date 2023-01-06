package com.example.shoeshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "size")
    private String size;
    @Column(name = "imageSP")
    private String imageSp;
    @Column(name = "price")
    private Double price;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;
    @OneToMany(mappedBy = "productEntityId")
    private List<OrderDetailEntity>  productEntityList;
}
