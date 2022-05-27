package de.hsrm.mi.web.projekt.geo;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeoServiceImpl implements GeoService{



    //ich gebe die String Adresse mit, die Methode liefert Liste mit record AdressInfo Zurück
    @Override
    public List<AdressInfo> findeAdressInfo(String adresse) {
        if(adresse == null || adresse == ""){
            return null;
        }
        //hässliche Lösung zum erstellen der URL (aber funktioniert)
        String[] adressSeperated = adresse.split(" ");
    	StringBuilder url = new StringBuilder();
    	url.append("https://nominatim.openstreetmap.org/search?q=");
        int i = 0;
    	for (String element : adressSeperated) {
    		if(i++ == adressSeperated.length - 1) {
    			url.append(element);
    		}else {
    			url.append(element + "+");
    		}
    		
    	}
    	url.append("&format=json");
    	String finalUrl = url.toString();

        //WebClient client = WebClient.create("https://nominatim.openstreetmap.org/search?<params>");
        WebClient client = WebClient.create(finalUrl);

        List<AdressInfo> adressInfos = client.get()
        .retrieve()
        .bodyToFlux(AdressInfo.class)
        .collectList()
        .block();
        // TODO Auto-generated method stub
        return adressInfos;
    }

    @Override
    public double abstandInKm(AdressInfo adr1, AdressInfo adr2) { final double ERDRADIUS_KM = 6370;
        var grad2rad = Math.PI / 180.0;
        var phi_a = adr1.lat() * grad2rad;
        var lambda_a = adr1.lon() * grad2rad;
        var phi_b =    adr2.lat() * grad2rad;
        var lambda_b = adr2.lon() * grad2rad;
        var zeta = Math.acos(Math.sin(phi_a) * Math.sin(phi_b)
            + Math.cos(phi_a)*Math.cos(phi_b)*Math.cos(lambda_b-lambda_a)); 
        return zeta * ERDRADIUS_KM;
        }
        

    @Override
    public double abstandKmNachGrad(double abstand) {
        // TODO Auto-generated method stub
        return abstand/111.139;
    }
    
}
