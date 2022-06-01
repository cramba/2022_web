package de.hsrm.mi.web.projekt.gebot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebotRepository extends JpaRepository <Gebot, Long> {
    
}
