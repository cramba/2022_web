package de.hsrm.mi.web.projekt.benutzerprofil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class BenutzerProfil {
    
    private String name = "";
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate geburtsdatum;
    private String adresse;
    private String email;
    private String lieblingsfarbe;
    private String interessen;

    public BenutzerProfil(){
        name = "";
        geburtsdatum = LocalDate.of(1,1,1);
        adresse = "";
        email = "";
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

    @Override
    public String toString() {
        return "BenutzerProfil [adresse=" + adresse + ", email=" + email + ", geburtsdatum=" + geburtsdatum
                + ", interessen=" + interessen + ", lieblingsfarbe=" + lieblingsfarbe + ", name=" + name + "]";
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
