package com.petshop.petshop_inventory.controller.purchase;

import com.petshop.petshop_inventory.dto.purchase.PurchaseRegisterDTO;
import com.petshop.petshop_inventory.dto.purchase.PurchaseResponseDTO;
import com.petshop.petshop_inventory.service.purchase.PurchaseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    @Transactional
    public ResponseEntity<PurchaseResponseDTO> registerPurchase(@RequestBody @Valid PurchaseRegisterDTO purchaseRegisterDTO, UriComponentsBuilder uriBuilder){
        var purchase = purchaseService.registerPurchase(purchaseRegisterDTO);
        var uri = uriBuilder.path("/purchases/{id}").buildAndExpand(purchase.id()).toUri();
        return ResponseEntity.created(uri).body(purchase);

    }

}
