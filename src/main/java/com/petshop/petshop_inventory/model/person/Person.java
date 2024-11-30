package com.petshop.petshop_inventory.model.person;


import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.dto.person.PersonUpdateDTO;
import com.petshop.petshop_inventory.model.person.add_ons.DocumentType;
import com.petshop.petshop_inventory.model.sale.Sale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


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

    @Column(unique = true)
    private Long documentNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DocumentType documentType;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;



    @OneToOne(mappedBy = "person")
    private Login login;


    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sale> sale = new ArrayList<>();





    public Person(PersonRegisterDTO personRegisterDTO) {
        this.name = personRegisterDTO.name();
        this.documentNumber = personRegisterDTO.documentNumber();
        this.documentType = personRegisterDTO.documentType();
        this.email = personRegisterDTO.email();
        this.phone = personRegisterDTO.phone();

    }

    public void update(PersonUpdateDTO personUpdateDTO) {
        if(personUpdateDTO.name() != null){
            this.name = personUpdateDTO.name();
        }
        if(personUpdateDTO.documentNumber() != null){
            this.documentNumber = personUpdateDTO.documentNumber();
        }
        if(personUpdateDTO.documentType() != null){
            this.documentType = personUpdateDTO.documentType();
        }
        if(personUpdateDTO.email() != null){
            this.email = personUpdateDTO.email();
        }
        if(personUpdateDTO.phone() != null){
            this.phone = personUpdateDTO.phone();
        }
    }
}
