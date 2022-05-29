package de.hsrm.mi.web.projekt.benutzerprofil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.validierung.Bunt;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;


@Entity
public class BenutzerProfil {
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @Size(min=3, max=60) @NotNull @NotBlank
    private String name;
    @DateTimeFormat(iso = ISO.DATE)
    @PastOrPresent @NotNull
    private LocalDate geburtsdatum;
    @NotBlank
    private String adresse;
    @Email
    private String email;
    @Bunt(message="{bunt.fehler}") @NotNull
    private String lieblingsfarbe;
    @NotNull @NotBlank
    private String interessen;

    private double lat;
    private double lon;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "anbieter") 
    private List<Angebot> angebote = new ArrayList<Angebot>();


    @Valid
    public BenutzerProfil(){
        name = "Marc";
        geburtsdatum = LocalDate.of(1,1,1);
        adresse = "";
        email = null;
        lieblingsfarbe = "";
        interessen = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLieblingsfarbe() {
        return lieblingsfarbe;
    }

    public void setLieblingsfarbe(String lieblingsfarbe) {
        this.lieblingsfarbe = lieblingsfarbe;
    }

    public String getInteressen() {
        return interessen;
    }

    public void setInteressen(String interessen) {
        this.interessen = interessen;
    }

    public long getId(){
        return id;
    }

    public long getVersion(){
        return version;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public List<Angebot> getAngebote(){
        return angebote;
    }

    @Override
    public String toString() {
        return "BenutzerProfil [adresse=" + adresse + ", email=" + email + ", geburtsdatum=" + geburtsdatum
                + ", interessen=" + interessen + ", lieblingsfarbe=" + lieblingsfarbe + ", name=" + name 
                + ", id=" + id + ", version=" + version + ", lat="+ lat + ", lon" + lon + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((geburtsdatum == null) ? 0 : geburtsdatum.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BenutzerProfil other = (BenutzerProfil) obj;
        if (geburtsdatum == null) {
            if (other.geburtsdatum != null)
                return false;
        } else if (!geburtsdatum.equals(other.geburtsdatum))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
    

    public List<String> getInteressenListe(){
        List<String> interessenListe = new ArrayList<>();
        if(interessen.equals("")){
            //interessen="17";
            return interessenListe;
        }else{
            interessenListe = Arrays.asList(interessen.split("\\s*,\\s*"));
            return interessenListe;
        }
    }
}
