package com.petshop.petshop_inventory.repository.purchase;

import com.petshop.petshop_inventory.model.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Page<Purchase> findAll(Pageable pageable);

}
