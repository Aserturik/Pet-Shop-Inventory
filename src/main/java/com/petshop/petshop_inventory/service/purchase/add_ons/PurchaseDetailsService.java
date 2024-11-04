package com.petshop.petshop_inventory.service.purchase.add_ons;


import com.petshop.petshop_inventory.dto.purchase.add_ons.PurchaseDetailsRegisterDTO;
import com.petshop.petshop_inventory.dto.purchase.add_ons.PurchaseDetailsResponseDTO;
import com.petshop.petshop_inventory.model.product.add_ons.Batch;
import com.petshop.petshop_inventory.model.purchase.Purchase;
import com.petshop.petshop_inventory.model.purchase.add_ons.PurchaseDetails;
import com.petshop.petshop_inventory.repository.product.ProductRepository;
import com.petshop.petshop_inventory.repository.product.add_ons.BatchRepository;
import com.petshop.petshop_inventory.repository.purchase.add_ons.PurchaseDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailsService {

    @Autowired
    private PurchaseDetailsRepository purchaseDetailsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BatchRepository batchRepository;

    public PurchaseDetails registerPurchaseDetails(PurchaseDetailsRegisterDTO purchaseDetailsRegisterDTO, Purchase purchase){
        var product = productRepository.findByBarCode(purchaseDetailsRegisterDTO.productBarCode())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStock(product.getStock() + purchaseDetailsRegisterDTO.quantity());
        product.setPurchasePrice(purchaseDetailsRegisterDTO.unitPrice());



        var batch = new Batch(purchaseDetailsRegisterDTO.batch(), product, purchaseDetailsRegisterDTO.quantity());
        System.out.println("Batch: " + batch);
        batchRepository.save(batch);
        product.addBatch(batch);
        productRepository.save(product);

        var purchaseDetails = new PurchaseDetails(purchaseDetailsRegisterDTO, batch);
        purchaseDetails.setPurchase(purchase);
        purchaseDetailsRepository.save(purchaseDetails);

        return purchaseDetails;

    }


}
