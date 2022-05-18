package de.hsrm.mi.web.projekt.benutzerprofil;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenutzerprofilServiceImpl implements BenutzerprofilService{

    private BenutzerprofilRepository benutzerprofilRepository;

    @Autowired
    public BenutzerprofilServiceImpl(BenutzerprofilRepository br) {
        this.benutzerprofilRepository = br;
    }

    @Override
    public BenutzerProfil speichereBenutzerProfil(BenutzerProfil bp) {
        // das übergebene bp im Respository abspeichern
        //durch Speicheraktion entstandene Entity zurückgeben
        return null;
    }

    @Override
    public Optional<BenutzerProfil> holeBenutzerProfilMitId(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void loescheBenutzerProfilMitId(Long loesch) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<BenutzerProfil> alleBenutzerProfile() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
