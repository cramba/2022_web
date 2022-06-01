package de.hsrm.mi.web.projekt.benutzerprofil;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.angebot.AngebotRepository;
import de.hsrm.mi.web.projekt.geo.AdressInfo;
import de.hsrm.mi.web.projekt.geo.GeoServiceImpl;

@Service
public class BenutzerprofilServiceImpl implements BenutzerprofilService{

    private final BenutzerprofilRepository benutzerprofilRepository;
    private final GeoServiceImpl geoService;
    private final AngebotRepository angebotRepository;

    @Autowired
    public BenutzerprofilServiceImpl(BenutzerprofilRepository benutzerRepository, GeoServiceImpl geoService, AngebotRepository angebotRepository) {
        this.benutzerprofilRepository = benutzerRepository;
        this.geoService = geoService;
        this.angebotRepository = angebotRepository;
    }

    @Override
    public BenutzerProfil speichereBenutzerProfil(BenutzerProfil bp) {
        // das übergebene bp im Respository abspeichern
        //durch Speicheraktion entstandene Entity zurückgeben
        //bp = benutzerprofilRepository.save(bp);
        List<AdressInfo> adressInfos = geoService.findeAdressInfo(bp.getAdresse());
        if(adressInfos != null && !adressInfos.isEmpty()){
            AdressInfo chosenAdress = adressInfos.get(0);
            bp.setLat(chosenAdress.lat());
            bp.setLon(chosenAdress.lon());
        }else {
            bp.setLat(0);
            bp.setLon(0);
        }

        //alle Angebote des Nutzers im Repository speichern
        for (Angebot angebot : bp.getAngebote()){
            angebotRepository.save(angebot);
        }
        return benutzerprofilRepository.save(bp);
    }

    @Override
    public Optional<BenutzerProfil> holeBenutzerProfilMitId(Long id) {
        // TODO Auto-generated method stub
        Optional<BenutzerProfil> bpId = benutzerprofilRepository.findById(id);
        if(bpId.isEmpty()){
            return bpId;
        }
        return bpId;
    }

    @Override
    public void loescheBenutzerProfilMitId(Long loesch) {
        // TODO Auto-generated method stub
        benutzerprofilRepository.deleteById(loesch);
    }

    @Override
    public List<BenutzerProfil> alleBenutzerProfile() {
        List<BenutzerProfil> alleBenutzer = benutzerprofilRepository.findAll(Sort.by("name"));
        alleBenutzer.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        
        // TODO Auto-generated method stub
        return alleBenutzer;
    }

	@Override
	public void fuegeAngebotHinzu(long id, Angebot angebot) {
		// TODO Auto-generated method stub
        //lat und lon von Angebot festlegen
        List<AdressInfo> adressInfos = geoService.findeAdressInfo(angebot.getAbholort());
        if(adressInfos != null && !adressInfos.isEmpty()){
            AdressInfo chosenAdress = adressInfos.get(0);
            angebot.setLat(chosenAdress.lat());
            angebot.setLon(chosenAdress.lon());
        }else{
            angebot.setLat(0);
            angebot.setLon(0);
        }

        BenutzerProfil anbieter = this.holeBenutzerProfilMitId(id).get();
        anbieter.getAngebote().add(angebot);
        angebot.setAnbieter(anbieter);
        this.speichereBenutzerProfil(anbieter);

		
	}

	@Override
	public void loescheAngebot(long id) {
		// TODO Auto-generated method stub
        //1. Aus der benutzer Angebote Liste Löschen
        BenutzerProfil besitzer = angebotRepository.getById(id).getAnbieter();
        //angebotRepository.getById(id).getAnbieter().getAngebote().removeIf(AngebotangebotRepository.getById(id) -> angebotRepository.getById(id).getId() == id);
        int index = 0;
        int deleteIndex = 0;
        for (Angebot angebot : besitzer.getAngebote()){
            if(angebot.getId() == id){
                deleteIndex = index;
                //besitzer.getAngebote().remove(angebot);
            }
            index++;
        }
        besitzer.getAngebote().remove(deleteIndex);
        //2. Das Angebot aus dem Repository löschen
		angebotRepository.deleteById(id);
	}

    @Override
    public List<Angebot> alleAngebote() {
        // TODO Auto-generated method stub
        return angebotRepository.findAll();
    }

    @Override
    public Optional<Angebot> findeAngebotMitId(long angebotid) {
        // TODO Auto-generated method stub
        return angebotRepository.findById(angebotid);
    }
    
}
