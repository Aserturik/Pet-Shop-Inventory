package com.petshop.petshop_inventory.model.product;

import com.petshop.petshop_inventory.dto.product.ProductRegisterDTO;
import com.petshop.petshop_inventory.dto.product.ProductUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @Column(unique = true)
    private String  barCode;
    private Double purchasePrice;
    private Double salePrice;
    private Integer stock = 0;

    public Product(ProductRegisterDTO productRegisterData) {
        this.name = productRegisterData.name();
        this.barCode = productRegisterData.barCode();
        this.purchasePrice = productRegisterData.purchasePrice();
        this.salePrice = productRegisterData.salePrice();
        this.stock = productRegisterData.stock();
    }

    public void update(ProductUpdateDTO productUpdateData) {
        if(productUpdateData.name() != null){
            this.name = productUpdateData.name();
        }
        if(productUpdateData.barCode() != null){
            this.barCode = productUpdateData.barCode();
        }
        if(productUpdateData.salePrice() != null){
            this.salePrice = productUpdateData.salePrice();
        }
    }
}
