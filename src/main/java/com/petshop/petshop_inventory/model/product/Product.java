package com.petshop.petshop_inventory.model.product;

import com.petshop.petshop_inventory.dto.product.ProductRegisterDTO;
import com.petshop.petshop_inventory.dto.product.ProductUpdateDTO;
import com.petshop.petshop_inventory.model.product.add_ons.Batch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Column(unique = true)
    private String urlImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Batch> batches = new ArrayList<>();

    public Product(ProductRegisterDTO productRegisterData) {
        this.name = productRegisterData.name();
        this.barCode = productRegisterData.barCode();
        this.purchasePrice = productRegisterData.purchasePrice();
        this.salePrice = productRegisterData.salePrice();
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

    public void addBatch(Batch batch){
        this.batches.add(batch);
    }



    public void sellUnits(int quantityToSell) {
        this.batches.stream()
                .filter(batch -> batch.getUnitsStock() > 0)
                .sorted((b1, b2) -> b1.getExpirationDate().compareTo(b2.getExpirationDate()))
                .collect(Collectors.toList());

        for (Batch batch : batches) {
            if (quantityToSell <= 0) {
                break;
            }

            int availableStock = batch.getUnitsStock();

            if (availableStock > 0) {
                if (availableStock >= quantityToSell) {

                    batch.setUnitsStock(availableStock - quantityToSell);
                    quantityToSell = 0;
                } else {

                    quantityToSell -= availableStock;
                    batch.setUnitsStock(0);
                }
            }
        }

        if (quantityToSell > 0) {
            System.out.println("No hay suficiente stock disponible para completar la venta de " + quantityToSell + " unidades.");
        }
    }
}
