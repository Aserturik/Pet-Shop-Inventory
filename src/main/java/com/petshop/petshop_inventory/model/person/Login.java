package com.petshop.petshop_inventory.model.person;


import com.petshop.petshop_inventory.dto.person.add_ons.LoginRegisterDTO;
import com.petshop.petshop_inventory.infra.security.config.HmacEncryption;
import com.petshop.petshop_inventory.model.person.add_ons.Role;
import com.petshop.petshop_inventory.model.person.add_ons.State;
import com.petshop.petshop_inventory.model.sale.Sale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "login")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Enumerated(EnumType.STRING)
    @NotNull
    private State state;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "login", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sale> sales;



    public Login(LoginRegisterDTO loginRegisterData, Person person) throws Exception {
        this.username = loginRegisterData.email();
        this.password = HmacEncryption.encryptKey(loginRegisterData.password());
        this.role = loginRegisterData.role();
        this.state = State.ACTIVE;
        this.person = person;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
