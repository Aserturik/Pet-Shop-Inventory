package com.petshop.petshop_inventory.model.purchase.add_ons;


import com.petshop.petshop_inventory.dto.purchase.add_ons.PurchaseDetailsRegisterDTO;
import com.petshop.petshop_inventory.model.product.add_ons.Batch;
import com.petshop.petshop_inventory.model.purchase.Purchase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "purchase_details")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double unitPrice;
    private Double total;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "batch_id")
    private Batch batch;

    public PurchaseDetails(PurchaseDetailsRegisterDTO purchaseDetailsRegisterDTO, Batch batch) {
        this.unitPrice = purchaseDetailsRegisterDTO.unitPrice();
        this.total = purchaseDetailsRegisterDTO.unitPrice() * purchaseDetailsRegisterDTO.quantity();
        this.quantity = purchaseDetailsRegisterDTO.quantity();
        this.batch = batch;


    }

}
