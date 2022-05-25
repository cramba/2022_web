package de.hsrm.mi.web.projekt.angebot;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Angebot {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;
    
    private String beschreibung;
    private Long mindestpreis;
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private LocalDateTime ablaufzeitpunkt;
    private String abholort;
    private double lat;
    private double lon;

    public Angebot(){
        beschreibung = "";
        mindestpreis = 0L;
        ablaufzeitpunkt = LocalDateTime.now();
        abholort = "";
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Long getMindestpreis() {
        return mindestpreis;
    }

    public void setMindestpreis(Long mindestpreis) {
        this.mindestpreis = mindestpreis;
    }

    public LocalDateTime getAblaufzeitpunkt() {
        return ablaufzeitpunkt;
    }

    public void setAblaufzeitpunkt(LocalDateTime ablaufzeitpunkt) {
        this.ablaufzeitpunkt = ablaufzeitpunkt;
    }

    public String getAbholort() {
        return abholort;
    }

    public void setAbholort(String abholort) {
        this.abholort = abholort;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getId(){
        return id;
    }

    public long getVersion(){
        return version;
    }

    @Override
    public String toString() {
        return "Angebot [abholort=" + abholort + ", ablaufzeitpunkt=" + ablaufzeitpunkt + ", beschreibung="
                + beschreibung + ", id=" + id + ", lat=" + lat + ", lon=" + lon + ", mindestpreis=" + mindestpreis
                + ", version=" + version + "]";
    }
}
