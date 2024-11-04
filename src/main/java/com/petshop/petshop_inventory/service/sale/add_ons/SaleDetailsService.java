package com.petshop.petshop_inventory.service.sale.add_ons;

import com.petshop.petshop_inventory.dto.sale.add_ons.SaleDetailsRegisterDTO;
import com.petshop.petshop_inventory.model.sale.Sale;
import com.petshop.petshop_inventory.model.sale.add_ons.SaleDetails;
import com.petshop.petshop_inventory.repository.product.ProductRepository;
import com.petshop.petshop_inventory.repository.sale.add_ons.SaleDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleDetailsService {

    @Autowired
    private SaleDetailsRepository saleDetailsRepository;
    @Autowired
    private ProductRepository productRepository;

    public SaleDetails registerSaleDetails(SaleDetailsRegisterDTO saleDetailsRegisterDTO, Sale sale) {
        var product = productRepository.findByBarCode(saleDetailsRegisterDTO.productBarCode())
                .orElseThrow(() -> new RuntimeException("Product not found"));


        int quantityToSell = saleDetailsRegisterDTO.quantity();
        product.sellUnits(quantityToSell);


        if (product.getStock() < 0) {
            throw new RuntimeException("Product out of stock after selling from batches");
        }

        product.setStock(product.getStock() - quantityToSell);
        productRepository.save(product);
        var saleDetails = new SaleDetails(saleDetailsRegisterDTO, product, sale);
        saleDetailsRepository.save(saleDetails);
        return saleDetails;
    }



}
