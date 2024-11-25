package com.petshop.petshop_inventory.model.sale;


import com.petshop.petshop_inventory.dto.sale.SaleRegisterDTO;
import com.petshop.petshop_inventory.model.invoice.Invoice;
import com.petshop.petshop_inventory.model.person.Login;
import com.petshop.petshop_inventory.model.sale.add_ons.PaymentMethod;
import com.petshop.petshop_inventory.model.sale.add_ons.SaleDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sale")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime saleDate;
    private Double total;
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SaleDetails> saleDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id")
    private Login login;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;



    public Sale(SaleRegisterDTO saleRegisterDTO, Login login) {
        this.saleDate = LocalDateTime.now();
        this.paymentMethod = saleRegisterDTO.paymentMethod();
        this.login = login;
    }

    public void addSaleDetails(List<SaleDetails> saleDetails) {
        this.saleDetails.addAll(saleDetails);
    }

}
