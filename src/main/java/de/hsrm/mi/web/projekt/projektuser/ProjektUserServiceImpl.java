package de.hsrm.mi.web.projekt.projektuser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProjektUserServiceImpl implements ProjektUserService{

    
    private final ProjektUserRepository projektUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProjektUserServiceImpl(ProjektUserRepository projektUserRepository, PasswordEncoder passwordEncoder) {
        this.projektUserRepository = projektUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ProjektUser neuenBenutzerAnlegen(String username, String klartextpasswort, String rolle) {
        // TODO Auto-generated method stub
        ProjektUser user = new ProjektUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(klartextpasswort)); //PASSWORT NOCH ENCODEN
        if(rolle.isEmpty() || rolle == null){
            user.setRole("");
        }else {
            user.setRole(rolle);
        }
        try{
            return projektUserRepository.save(user);
        }catch(Exception e){
            throw new ProjektUserServiceException("Es existiert bereits ein Nutzer mit dem Namen: " + username);
        }
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
