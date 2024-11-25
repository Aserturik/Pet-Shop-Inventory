package com.petshop.petshop_inventory.model.person;


import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.model.invoice.Invoice;
import com.petshop.petshop_inventory.model.person.add_ons.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private List<Invoice> invoices;

    public Person(PersonRegisterDTO personRegisterDTO) {
        this.name = personRegisterDTO.name();
        this.documentNumber = personRegisterDTO.documentNumber();
        this.documentType = personRegisterDTO.documentType();
        this.email = personRegisterDTO.email();
        this.phone = personRegisterDTO.phone();

    }
}
