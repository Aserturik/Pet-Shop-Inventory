package com.petshop.petshop_inventory.model.sale.add_ons;


import com.petshop.petshop_inventory.dto.sale.add_ons.SaleDetailsRegisterDTO;
import com.petshop.petshop_inventory.model.product.Product;
import com.petshop.petshop_inventory.model.sale.Sale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "sale_details")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double unitSalePrice;
    private Double total;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public SaleDetails(SaleDetailsRegisterDTO saleDetailsRegisterDTO, Product product, Sale sale) {
        this.quantity = saleDetailsRegisterDTO.quantity();
        this.unitSalePrice = product.getSalePrice();
        this.total = this.unitSalePrice * this.quantity;
        this.product = product;
        this.sale = sale;

    }

}
