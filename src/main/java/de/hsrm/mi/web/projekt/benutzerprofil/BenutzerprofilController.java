package de.hsrm.mi.web.projekt.benutzerprofil;


import javax.validation.Valid;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes(names = {"profil"})
public class BenutzerprofilController {
    Validator vali;
    Logger logger = LoggerFactory.getLogger(BenutzerprofilController.class);
    

    @ModelAttribute("profil")
    public void initProfil(Locale locale, Model m){
        BenutzerProfil profil = new  BenutzerProfil();
        m.addAttribute("profil", profil);
        m.addAttribute("sprache", locale.getDisplayLanguage());

        /*
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
        */
        
    }

    @GetMapping("/benutzerprofil")
    public String benutzerprofil_get() {
        return "/benutzerprofil/profilansicht";
    }

    @GetMapping("/benutzerprofil/bearbeiten")
    public String bearbeiten_get(Model m){
        return "/benutzerprofil/profileditor";
    }

    @PostMapping("/benutzerprofil/bearbeiten")  
    public String postForm(@Valid @ModelAttribute("profil") BenutzerProfil profil, BindingResult result){
        if(result.hasErrors()){
            return "benutzerprofil/profileditor";
        }
  
        return "redirect:/benutzerprofil";
    }

}
