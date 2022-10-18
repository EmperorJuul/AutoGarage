package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "authoriteiten")
public class Authority implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String gebruikersnaam;

    @Column(nullable = false)
    private String Authority;

    public Authority() {
    }

    public Authority(String gebruikersnaam, String authority) {
        this.gebruikersnaam = gebruikersnaam;
        Authority = authority;
    }
}
