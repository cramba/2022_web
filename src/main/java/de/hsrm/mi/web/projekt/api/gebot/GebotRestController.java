package de.hsrm.mi.web.projekt.api.gebot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.gebot.Gebot;
import de.hsrm.mi.web.projekt.gebot.GebotServiceImpl;

@RestController
public class GebotRestController {

    @Autowired
    private GebotServiceImpl gebotService;

    @GetMapping("/api/gebot")
    public List <GetGebotResponseDTO> getAlleGebote(){
        List <GetGebotResponseDTO> geboteDTOlist= new ArrayList<GetGebotResponseDTO>();
        for(Gebot gebot : gebotService.findeAlleGebote()){
            geboteDTOlist.add(GetGebotResponseDTO.from(gebot));
        } 
        return geboteDTOlist;
    }
    
    @PostMapping("/api/gebot")
    public long neuesGebot(@RequestBody AddGebotRequestDTO gebotDTO){
        Gebot g = gebotService.bieteFuerAngebot(gebotDTO.benutzerprofilid(), gebotDTO.angebotid(), gebotDTO.betrag());
        return g.getId();
    }

    @DeleteMapping("/api/gebot/{id}")
    public void deleteGebot(@PathVariable("id") Long id){
        gebotService.loescheGebot(id);
    }
}
