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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/")
@SessionAttributes(names = {"projektUser"})
public class ProjektUserController {

    private ProjektUserServiceImpl userService;

    Logger logger = LoggerFactory.getLogger(ProjektUserController.class);

    @Autowired
    public ProjektUserController(ProjektUserServiceImpl userService){
        this.userService = userService;
    }

    @ModelAttribute("projektUser")
    public void initProfil(Locale locale, Model m){
        ProjektUser projektUser = new ProjektUser();
        m.addAttribute("projektUser", projektUser);
        m.addAttribute("sprache", locale.getDisplayLanguage());
    }

    @GetMapping("/registrieren")
    public String registrieren_get(){
        return "/projektuser/registrieren";
    }

    @PostMapping("projektuser/registrieren")
    public String postRegistrierung(@Valid, @ModelAttribute("projektUser") ProjektUser user, BindingResult result, Model m){
        if(result.hasErrors()){
            return "projektuser/registrieren"
        }
    }
    
    
}
