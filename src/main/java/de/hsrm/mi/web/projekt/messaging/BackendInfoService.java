package de.hsrm.mi.web.projekt.messaging;

import de.hsrm.mi.web.projekt.messaging.BackendInfoMessage.BackendOperation;

public interface BackendInfoService {
    
    public void sendInfo(String topicname, BackendOperation operation, long id);
}
