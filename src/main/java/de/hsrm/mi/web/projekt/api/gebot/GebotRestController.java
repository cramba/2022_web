package de.hsrm.mi.web.projekt.api.gebot;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GebotRestController {

    @GetMapping("api/gebot")
    public List <GetGebotResponseDTO> getAlleGebote(){
        return null;
    }
    
    @PostMapping("api/gebot")
    public 
}
