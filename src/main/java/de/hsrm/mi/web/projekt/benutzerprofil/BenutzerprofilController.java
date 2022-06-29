package de.hsrm.mi.web.projekt.benutzerprofil;


import javax.validation.Valid;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.messaging.BackendInfoServiceImpl;
import de.hsrm.mi.web.projekt.messaging.BackendInfoMessage.BackendOperation;
import de.hsrm.mi.web.projekt.projektuser.ProjektUserServiceImpl;

@Controller
@RequestMapping("/")
@SessionAttributes(names = {"profil"})
public class BenutzerprofilController {
    private BenutzerprofilServiceImpl benutzerprofilService;

    Validator vali;
    Logger logger = LoggerFactory.getLogger(BenutzerprofilController.class);

    @Autowired
    public BenutzerprofilController(BenutzerprofilServiceImpl benutzerprofilService){
        this.benutzerprofilService = benutzerprofilService;
    }

    @Autowired
    BackendInfoServiceImpl backendInfoServiceImpl;

    @Autowired
    ProjektUserServiceImpl projektUserService;
    

    @ModelAttribute("profil")
    public void initProfil(Locale locale, Model m, Principal prinz){
        BenutzerProfil profil = new  BenutzerProfil();
        //m.addAttribute("profil", profil);
        m.addAttribute("sprache", locale.getDisplayLanguage());
        if(prinz != null){
            profil = projektUserService.findeBenutzer(prinz.getName()).getBenutzerprofil();
        }
        m.addAttribute("profil", profil);

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
  
    @GetMapping("/benutzerprofil/liste")
    public String liste_get(Model m, @RequestParam(required = false) String op, @RequestParam(required=false) Long id){

        //List<BenutzerProfil> profilliste = benutzerprofilService.alleBenutzerProfile();
        m.addAttribute("profilliste", benutzerprofilService.alleBenutzerProfile());
        //System.out.println(op);
        if(op != null && op.equals("loeschen")){
            benutzerprofilService.loescheBenutzerProfilMitId(id);
            return "redirect:/benutzerprofil/liste";
        }else if(op != null && op.equals("bearbeiten")){
            BenutzerProfil test = benutzerprofilService.holeBenutzerProfilMitId(id).get();
            logger.info(test.toString());
            m.addAttribute("profil", benutzerprofilService.holeBenutzerProfilMitId(id).get());
            return "redirect:/benutzerprofil/bearbeiten";
        }

        return "/benutzerprofil/liste";
    }

    @GetMapping("/benutzerprofil/angebot")
    public String angebot_get(Model m){
        Angebot angebot = new  Angebot();
        m.addAttribute("angebot", angebot);
        return "/benutzerprofil/angebotsformular";
    }


    @PostMapping("/benutzerprofil/bearbeiten")  
    public String postForm(@Valid @ModelAttribute("profil") BenutzerProfil profil, BindingResult result, Model m){
        if(result.hasErrors()){
            return "benutzerprofil/profileditor";
        }
        profil = benutzerprofilService.speichereBenutzerProfil(profil);
        m.addAttribute("profil", benutzerprofilService.holeBenutzerProfilMitId(profil.getId()).get());
        //HIER
        //m.addAttribute("profil", profil);
        return "redirect:/benutzerprofil";
    }

    @PostMapping("/benutzerprofil/angebot")
    public String postAngebot(@ModelAttribute("angebot") Angebot angebot, Model m){
        BenutzerProfil profil = (BenutzerProfil) m.getAttribute("profil");
        benutzerprofilService.fuegeAngebotHinzu(profil.getId(), angebot);
        //m.addAttribute("profil", profil);
        m.addAttribute("profil", benutzerprofilService.holeBenutzerProfilMitId(profil.getId()).get());
        //stimmt das?
        backendInfoServiceImpl.sendInfo("angebot", BackendOperation.CREATE, angebot.getId());
        return "redirect:/benutzerprofil";
    }

    @GetMapping("/benutzerprofil/angebot/{id}/del")
    public String deleteAngebot_get(Model m, @PathVariable("id") Long id){
        BenutzerProfil profil = (BenutzerProfil) m.getAttribute("profil");
        benutzerprofilService.loescheAngebot(id);
        m.addAttribute("profil", benutzerprofilService.holeBenutzerProfilMitId(profil.getId()).get());
        //stimmt das?
        backendInfoServiceImpl.sendInfo("angebot", BackendOperation.DELETE, id);
        return "redirect:/benutzerprofil";
    }

}
