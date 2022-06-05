package de.hsrm.mi.web.projekt.gebot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerProfil;
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
        Optional<Gebot> gebotVorhanden = gebotRepository.findByAngebotIdAndBieterId(angebotid, benutzerprofilid);
        if(gebotVorhanden.isPresent()){
            Gebot gebot = gebotVorhanden.get();
            gebot.setBetrag(betrag);
            gebot.setGebotszeitpunkt(LocalDateTime.now());
            //return gebot;
            return gebotRepository.save(gebot);
        }else {
            Gebot neuesGebot = new Gebot();
            neuesGebot.setBetrag(betrag);
            neuesGebot.setGebieter(benutzerprofilService.holeBenutzerProfilMitId(benutzerprofilid).get());
            neuesGebot.setAngebot(benutzerprofilService.findeAngebotMitId(angebotid).get());
            benutzerprofilService.findeAngebotMitId(angebotid).get().getGebote().add(neuesGebot);
            benutzerprofilService.holeBenutzerProfilMitId(benutzerprofilid).get().getGebote().add(neuesGebot);
            return gebotRepository.save(neuesGebot);
        }
    }

    @Override
    public void loescheGebot(long gebotid) {
        // TODO Auto-generated method stub
        //aus benutzerprofil löschen
        BenutzerProfil gebieter = gebotRepository.getById(gebotid).getGebieter();
        gebieter.getGebote().remove(gebotRepository.getById(gebotid));

        //aus Angebot löschen
        Angebot angebot = gebotRepository.getById(gebotid).getAngebot();
        angebot.getGebote().remove(gebotRepository.getById(gebotid));
        
        //aus Repository löschen
        gebotRepository.deleteById(gebotid);
        
    }
    
}
