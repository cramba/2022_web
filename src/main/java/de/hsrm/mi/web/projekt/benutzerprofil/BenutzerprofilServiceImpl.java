package de.hsrm.mi.web.projekt.benutzerprofil;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.geo.AdressInfo;
import de.hsrm.mi.web.projekt.geo.GeoServiceImpl;

@Service
public class BenutzerprofilServiceImpl implements BenutzerprofilService{

    private final BenutzerprofilRepository benutzerprofilRepository;
    private final GeoServiceImpl geoService;

    @Autowired
    public BenutzerprofilServiceImpl(BenutzerprofilRepository benutzerRepository, GeoServiceImpl geoService) {
        this.benutzerprofilRepository = benutzerRepository;
        this.geoService = geoService;
    }

    @Override
    public BenutzerProfil speichereBenutzerProfil(BenutzerProfil bp) {
        // das übergebene bp im Respository abspeichern
        //durch Speicheraktion entstandene Entity zurückgeben
        //bp = benutzerprofilRepository.save(bp);
        List<AdressInfo> adressInfos = geoService.findeAdressInfo(bp.getAdresse());
        if(adressInfos.isEmpty()){
            bp.setLat(0);
            bp.setLon(0);
        }else {
            AdressInfo chosenAdress = adressInfos.get(0);
            bp.setLat(chosenAdress.lat());
            bp.setLon(chosenAdress.lon());
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
    
}
