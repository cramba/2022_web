package de.hsrm.mi.web.projekt.projektuser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;




@Controller
@RequestMapping("/")
public class ProjektUserController {

    @Autowired 
    ProjektUserServiceImpl userService;

    Logger logger = LoggerFactory.getLogger(ProjektUserController.class);

    

    @ModelAttribute("projektUser")
    public void initProfil(Model m){
        ProjektUser projektUser = new ProjektUser();
        m.addAttribute("projektUser", projektUser);
    }

    @GetMapping("/registrieren")
    public String registrieren_get(){
        return "/projektuser/registrieren";
    }

    @PostMapping("/registrieren")
    public String postRegistrierung(@Valid @ModelAttribute("projektUser") ProjektUser user, BindingResult result, Model m){
        if(result.hasErrors()){
            return "projektuser/registrieren";
        }
        userService.neuenBenutzerAnlegen(user.getUsername(), user.getPassword(), user.getRole());
        //m.addAttribute("projektUser", user);
        return "redirect:/benutzerprofil";
    }          
    
    
}
