package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String gebruikersnaam;

    @Column(nullable = false)
    private String authority;

    public Authority() {
    }

    public Authority(Long id, String gebruikersnaam, String authority) {
        this.id = id;
        this.gebruikersnaam = gebruikersnaam;
        this.authority = authority;
    }
}
