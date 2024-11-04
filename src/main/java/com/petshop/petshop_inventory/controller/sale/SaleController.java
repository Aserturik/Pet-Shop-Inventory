package com.petshop.petshop_inventory.controller.sale;

import com.petshop.petshop_inventory.dto.sale.SaleRegisterDTO;
import com.petshop.petshop_inventory.dto.sale.SaleResponseDTO;
import com.petshop.petshop_inventory.service.sale.SaleService;
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
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    @Transactional
    public ResponseEntity<SaleResponseDTO> registerSale(@RequestBody @Valid SaleRegisterDTO saleRegisterDTO, UriComponentsBuilder uriBuilder){
        var sale = saleService.registerSale(saleRegisterDTO);

        return ResponseEntity.ok(sale);


    }

}
