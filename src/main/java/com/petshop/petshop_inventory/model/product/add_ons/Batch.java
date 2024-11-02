package com.petshop.petshop_inventory.model.product.add_ons;



import com.petshop.petshop_inventory.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Table(name = "batch")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String batchNumber;
    private LocalDate entryDate;
    private LocalDate expirationDate;
    private Integer unitsStock = 0;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

//    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<PurchaseDetails> purchaseDetails;
//
//
//    public Batch(BatchRegisterDTO batchRegisterDTO, Product product) {
//        this.batchNumber = batchRegisterDTO.batchNumber();
//        this.entryDate = LocalDate.now();
//        this.expirationDate = batchRegisterDTO.expirationDate();
//        unitsStock = unitsStock + batchRegisterDTO.quantity();
//        this.product = product;
//    }

}
