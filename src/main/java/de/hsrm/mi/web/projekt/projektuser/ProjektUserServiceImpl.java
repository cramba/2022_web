package de.hsrm.mi.web.projekt.projektuser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerProfil;
import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerprofilServiceImpl;

@Service
public class ProjektUserServiceImpl implements ProjektUserService{


    @Autowired private ProjektUserRepository projektUserRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private BenutzerprofilServiceImpl benutzerprofilService;

    @Override
    public ProjektUser neuenBenutzerAnlegen(String username, String klartextpasswort, String rolle) {
        // TODO Auto-generated method stub
        ProjektUser user = new ProjektUser();
        BenutzerProfil profil = new BenutzerProfil();
        
        if(projektUserRepository.existsById(username)){
            throw new ProjektUserServiceException("Es existiert bereits ein Nutzer mit dem Namen: " + username);
        }
        benutzerprofilService.speichereBenutzerProfil(profil);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(klartextpasswort)); 
        projektUserRepository.save(user);

        profil.setName(username);
        profil.setProjektUser(user);
        user.setBenutzerprofil(profil);
       

        // user.setPassword(passwordEncoder.encode(klartextpasswort)); 
        if(rolle.isEmpty() || rolle == null){
            user.setRole("USER");
        }else {
            user.setRole(rolle);
        }
        return projektUserRepository.save(user);
    }

    @Override
    public ProjektUser findeBenutzer(String username){
        Optional<ProjektUser> user = projektUserRepository.findById(username);

        if(user.isEmpty()){
            throw new ProjektUserServiceException("Es existiert kein Nutzer mit dem Namen: " + username);
        }else if(user.isPresent()){
            return user.get();
        }else {
            throw new ProjektUserServiceException("Ein unbekannter Fehler ist aufgetreten");
        }
    }
    
}
