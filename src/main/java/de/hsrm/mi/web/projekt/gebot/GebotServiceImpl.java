package de.hsrm.mi.web.projekt.gebot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerprofilService;



@Service
public class GebotServiceImpl implements GebotService {

    
    private final GebotRepository gebotRepository;
    private final BenutzerprofilService benutzerprofilService;

    @Autowired
    public GebotServiceImpl(GebotRepository gebotRepository, BenutzerprofilService benutzerprofilService) {
        this.gebotRepository = gebotRepository;
        this.benutzerprofilService = benutzerprofilService;
    }

    @Override
    public List<Gebot> findeAlleGebote() {
        // TODO Auto-generated method stub
        return gebotRepository.findAll();
    }

    @Override
    public List<Gebot> findeAlleGeboteFuerAngebot(long angebotid) {
        // TODO Auto-generated method stub
        return benutzerprofilService.findeAngebotMitId(angebotid).get().getGebote();
    }

    @Override
    public Gebot bieteFuerAngebot(long benutzerprofilid, long angebotid, long betrag) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void loescheGebot(long gebotid) {
        // TODO Auto-generated method stub
        
    }
    
}
