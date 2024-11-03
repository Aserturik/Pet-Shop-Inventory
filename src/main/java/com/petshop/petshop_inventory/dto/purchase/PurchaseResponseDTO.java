package com.petshop.petshop_inventory.dto.purchase;



import com.petshop.petshop_inventory.dto.purchase.add_ons.PurchaseDetailsResponseDTO;
import com.petshop.petshop_inventory.model.purchase.Purchase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PurchaseResponseDTO(
        Long id,
        String purchaseNumber,

        LocalDate purchaseDate,
        Double total,
        List<PurchaseDetailsResponseDTO> purchaseDetails



) {
    public PurchaseResponseDTO(Purchase purchase) {
    this(
        purchase.getId(),
        purchase.getPurchaseNumber(),
        purchase.getPurchaseDate(),
        purchase.getTotal(),
        purchase.getPurchaseDetails().stream()
            .map(PurchaseDetailsResponseDTO::new)
            .toList()
    );
}

}
