package de.hsrm.mi.web.projekt.projektuser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerProfil;

@Entity
public class ProjektUser {

    @Id @NotBlank @Size(min=3)
    private String username;

    @NotBlank @Size(min=3) 
    private String password;

    @OneToOne
    private BenutzerProfil benutzerprofil;

    private String role;
    
    public ProjektUser(){
        role = "";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BenutzerProfil getBenutzerProfil() {
        return benutzerprofil;
    }

    public void setBenutzerprofil(BenutzerProfil benutzerprofil) {
        this.benutzerprofil = benutzerprofil;
    }

    @Override
    public String toString() {
        return "ProjektUser [password=" + password + ", role=" + role + ", username=" + username + "]";
    }
}
