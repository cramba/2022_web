package de.hsrm.mi.web.projekt.api.benutzer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.api.gebot.GetGebotResponseDTO;
import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerprofilServiceImpl;
import de.hsrm.mi.web.projekt.gebot.Gebot;

//@RequestMapping("/")
@RestController
public class BenutzerAngebotRestController {

    @Autowired
    private BenutzerprofilServiceImpl benutzerService;

    @GetMapping("/api/angebot")
    public List <GetAngebotResponseDTO> getAlleAngebote(){
        List <GetAngebotResponseDTO> angeboteDTOlist = new ArrayList<GetAngebotResponseDTO>();
        for(Angebot a : benutzerService.alleAngebote()){
            angeboteDTOlist.add(GetAngebotResponseDTO.from(a));
        }
        return angeboteDTOlist;
    }

    @GetMapping("/api/angebot/{id}")
    public GetAngebotResponseDTO dtoMitId(@PathVariable("id") Long id){
        return GetAngebotResponseDTO.from(benutzerService.findeAngebotMitId(id).get());
    }

    @GetMapping("/api/angebot/{id}/gebot")
    public List <GetGebotResponseDTO> getAlleGeboteZuAngebot(@PathVariable("id") Long id){
        List <GetGebotResponseDTO> geboteDTOlist= new ArrayList<GetGebotResponseDTO>();
        for(Gebot g : benutzerService.findeAngebotMitId(id).get().getGebote()){
            geboteDTOlist.add(GetGebotResponseDTO.from(g));
        }
        return geboteDTOlist;
    }

    @GetMapping("/api/benutzer/{id}/angebot")
    public List <GetAngebotResponseDTO> getAlleAngeboteZubenutzer(@PathVariable("id") Long id){
        List <GetAngebotResponseDTO> angeboteDTOlist = new ArrayList<GetAngebotResponseDTO>();
        for(Angebot a : benutzerService.holeBenutzerProfilMitId(id).get().getAngebote()){
            angeboteDTOlist.add(GetAngebotResponseDTO.from(a));
        }
        return angeboteDTOlist;
    }

    
}
