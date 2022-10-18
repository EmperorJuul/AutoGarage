package nl.belastingdienst.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Gebruiker() {
    }
}
