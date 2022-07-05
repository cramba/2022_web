package de.hsrm.mi.web.projekt.api.gebot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.event.TransactionalEventListener;

public class GebotMessageSender {
    
    @Autowired
    private SimpMessagingTemplate messaging;

    @TransactionalEventListener
    @EventListener
    public void sendInfo(GetGebotResponseDTO gebotDTO){

        messaging.convertAndSend("/topic/gebot/" + gebotDTO.gebotid(), gebotDTO);

    }
}
