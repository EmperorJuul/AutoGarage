package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "gebruikers")
public class Gebruiker {

    @Id
    @Column(nullable = false, unique = true)
    private String gebruikersnaam;

    @Column(nullable = false, length = 255)
    private String wachtwoord;

    @Column(nullable = false)
    private boolean ingeschakeld = true;

    @Column
    private String email;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();


}
