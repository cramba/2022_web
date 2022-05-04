package de.hsrm.mi.web.projekt.benutzerprofil;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BenutzerprofilController {

    Logger logger = LoggerFactory.getLogger(BenutzerprofilController.class);
    

    @ModelAttribute("profil")
    public void initProfil(Model m){
        BenutzerProfil profil = new  BenutzerProfil();
        profil.setName("Marc");
        profil.setGeburtsdatum(LocalDate.of(1,1,1));
        profil.setAdresse("Altenbacher Straße");
        profil.setEmail("marc.bachmann@mail.com");
        profil.setLieblingsfarbe("Blau");
        profil.setInteressen("Programmieren, Schlafen");

        m.addAttribute("profil", profil);
        m.addAttribute("name", profil.getName());
        m.addAttribute("geburtsdatum", profil.getGeburtsdatum());
        m.addAttribute("adresse", profil.getAdresse());
        m.addAttribute("email", profil.getEmail());
        m.addAttribute("lieblingsfarbe", profil.getLieblingsfarbe());
        m.addAttribute("interessen", profil.getInteressenListe(profil.getInteressen()));
    }

    @GetMapping("/benutzerprofil")
    public String benutzerprofil_get() {
        
        return "/benutzerprofil/profilansicht";
    }


}
