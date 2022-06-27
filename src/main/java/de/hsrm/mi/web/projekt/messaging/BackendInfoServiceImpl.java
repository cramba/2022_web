package de.hsrm.mi.web.projekt.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.messaging.BackendInfoMessage.BackendOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BackendInfoServiceImpl implements BackendInfoService{

    Logger logger = LoggerFactory.getLogger(BackendInfoServiceImpl.class);

    @Autowired
    private SimpMessagingTemplate messaging;

    @Override
    public void sendInfo(String topicname, BackendOperation operation, long id) {
        // TODO Auto-generated method stub
        BackendInfoMessage message = new BackendInfoMessage(topicname, operation, id);
        logger.info(message.toString());
        messaging.convertAndSend("/topic/" + topicname, message);
    }
    
}
