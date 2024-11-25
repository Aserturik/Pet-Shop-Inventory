package com.petshop.petshop_inventory.dto.sale;

import com.petshop.petshop_inventory.dto.sale.add_ons.SaleDetailsRegisterDTO;
import com.petshop.petshop_inventory.dto.sale.add_ons.SaleDetailsResponseDTO;
import com.petshop.petshop_inventory.model.sale.Sale;
import com.petshop.petshop_inventory.model.sale.add_ons.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(

        Long id,
        List<SaleDetailsResponseDTO> saleDetails,
        LocalDateTime saleDate,
        Double total,
        PaymentMethod paymentMethod,
        String workerName
        //ToDo agregar referencia de la factura
        //Long invoiceNumber


) {

    public SaleResponseDTO(Sale sale) {
        this(sale.getId(),sale.getSaleDetails().stream()
                        .map(SaleDetailsResponseDTO::new)
                        .toList(),

                sale.getSaleDate(),
                sale.getTotal(),
                sale.getPaymentMethod(),
                sale.getLogin().getPerson().getName()
        );
    }
}
