package com.petshop.petshop_inventory.service.purchase;

import com.petshop.petshop_inventory.dto.purchase.PurchaseRegisterDTO;
import com.petshop.petshop_inventory.dto.purchase.PurchaseResponseDTO;
import com.petshop.petshop_inventory.model.purchase.Purchase;
import com.petshop.petshop_inventory.repository.purchase.PurchaseRepository;
import com.petshop.petshop_inventory.service.purchase.add_ons.PurchaseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseDetailsService purchaseDetailsService;

    public PurchaseResponseDTO registerPurchase(PurchaseRegisterDTO purchaseRegisterDTO) {
        var purchase = new Purchase(purchaseRegisterDTO);

        var purchaseDetails = purchaseRegisterDTO.purchaseDetails().stream()
                .map(purchaseDetailsRegisterDTO -> purchaseDetailsService.registerPurchaseDetails(purchaseDetailsRegisterDTO, purchase))
                .toList();

        var total = purchaseDetails.stream().map(purchaseDetails1 -> purchaseDetails1.getBatch().getProduct().getPurchasePrice() * purchaseDetails1.getQuantity()).reduce(0.0, Double::sum);
        purchase.setTotal(total);
        purchase.addPurchaseDetails(purchaseDetails);

        purchaseRepository.save(purchase);
        return new PurchaseResponseDTO(purchase);
    }


    public Page<PurchaseResponseDTO> getAllPurchase(Pageable pageable) {
        return purchaseRepository.findAll(pageable).map(PurchaseResponseDTO::new);
    }
}
