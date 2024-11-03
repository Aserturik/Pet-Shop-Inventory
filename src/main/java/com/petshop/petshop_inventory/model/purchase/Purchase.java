package com.petshop.petshop_inventory.model.purchase;

import com.petshop.petshop_inventory.dto.purchase.PurchaseRegisterDTO;
import com.petshop.petshop_inventory.model.purchase.add_ons.PurchaseDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "purchase")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String purchaseNumber;
    private LocalDate purchaseDate;
    private Double total;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PurchaseDetails> purchaseDetails = new ArrayList<>();


    public Purchase(PurchaseRegisterDTO purchaseRegisterData) {
    this.purchaseNumber = purchaseRegisterData.purchaseNumber();
    this.purchaseDate = LocalDate.now();

    }

    public void addPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
    this.purchaseDetails.addAll(purchaseDetails);
}





}
