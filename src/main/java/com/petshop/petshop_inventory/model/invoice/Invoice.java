package com.petshop.petshop_inventory.model.invoice;

import com.petshop.petshop_inventory.model.person.Person;
import com.petshop.petshop_inventory.model.sale.Sale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "invoice")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ToDo Agregar atributos

    @OneToOne(mappedBy = "invoice")
    private Sale sale;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

}
