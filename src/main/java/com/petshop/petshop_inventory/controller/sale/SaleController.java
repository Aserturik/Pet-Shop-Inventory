package com.petshop.petshop_inventory.controller.sale;

import com.petshop.petshop_inventory.dto.sale.SaleRegisterDTO;
import com.petshop.petshop_inventory.dto.sale.SaleResponseDTO;
import com.petshop.petshop_inventory.service.sale.SaleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    @Transactional
    public ResponseEntity<PagedModel<SaleResponseDTO>> getAllSales(@PageableDefault(size = 50) Pageable pageable){
        var page = saleService.getAllSales(pageable);
        PagedModel<SaleResponseDTO> pagedModel = new PagedModel<>(page);
        return ResponseEntity.ok(pagedModel);
    }

}
