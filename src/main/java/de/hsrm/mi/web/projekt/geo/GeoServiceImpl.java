package de.hsrm.mi.web.projekt.geo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GeoServiceImpl implements GeoService{

WebClient webClient = WebClient.create("https://nominatim. openstreetmap.org");
    @Override
    public List<AdressInfo> findeAdressInfo(String adresse) {
        if(adresse == null || adresse == ""){
            return null;
        }
        // TODO Auto-generated method stub
        return null;
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
