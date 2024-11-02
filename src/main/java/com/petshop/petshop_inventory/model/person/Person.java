package com.petshop.petshop_inventory.model.person;


import com.petshop.petshop_inventory.model.person.add_ons.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "person")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long documentNumber;
    private DocumentType documentType;
    private String email;
    private String phone;



    @OneToOne(mappedBy = "person")
    private Login login;

}
