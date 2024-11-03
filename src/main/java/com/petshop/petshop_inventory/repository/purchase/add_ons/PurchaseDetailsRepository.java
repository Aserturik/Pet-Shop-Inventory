package com.petshop.petshop_inventory.repository.purchase.add_ons;

import com.petshop.petshop_inventory.model.purchase.add_ons.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, Long> {

}
