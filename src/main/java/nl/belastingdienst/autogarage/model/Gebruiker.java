package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "gebruikers")
public class Gebruiker {

    @Id
    @Column(unique = true, nullable = false)
    private String gebruikersnaam;

    @Column(nullable = false)
    private String wachtwoord;

    @OneToMany
    private Set<Authority> authorities = new HashSet<>();

    public Gebruiker() {
    }

    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }

    public void removeAuthority(Authority authority){
        this.authorities.remove(authority);
    }
}
