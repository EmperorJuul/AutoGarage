package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "authoriteiten")
public class Authoriteit implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String gebruikersnaam;

    @Column(nullable = false)
    private String authority;

    public Authoriteit() {
    }

    public Authoriteit(String gebruikersnaam, String authority) {
        this.gebruikersnaam = gebruikersnaam;
        this.authority = authority;
    }
}
