package de.hsrm.mi.web.projekt.api.benutzer;

import java.time.LocalDateTime;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.gebot.Gebot;

public record GetAngebotResponseDTO(
long angebotid,             //ID des Angebots
String beschreibung,        //Beschreibung
long anbieterid,            //ID des Anbieters
String anbietername,        //Name des Anbieters
long mindestpreis,          //geforderter Mindestpreis
LocalDateTime ablaufzeitpunkt,  //Ende der Versteigerung
String abholort,            //Abholort der Sache
double lat,                 //mit Breite
double lon,                 //mit Laenge
long topgebot,              //bisher max. Gebot für dieses Angebot (Default 0)
long gebote                 //Anzahl bisheriger Gebote
){
    public static GetAngebotResponseDTO from(Angebot a){
        //maximalgebot finden
        long topGebot = 0L;
        for(Gebot g : a.getGebote()){
            if(g.getBetrag() >= topGebot){
                topGebot = g.getBetrag();
            }
        }
        GetAngebotResponseDTO angebotDTO = new GetAngebotResponseDTO(
            a.getId(), 
            a.getBeschreibung(), 
            a.getAnbieter().getId(), 
            a.getAnbieter().getName(), 
            a.getMindestpreis(), 
            a.getAblaufzeitpunkt(), 
            a.getAbholort(), 
            a.getLat(), 
            a.getLon(), 
            topGebot, 
            a.getGebote().size());
        return angebotDTO;
        
    }

}
