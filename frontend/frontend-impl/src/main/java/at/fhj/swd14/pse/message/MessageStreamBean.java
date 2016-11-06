package at.fhj.swd14.pse.message;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.message.MessageService;

@Named
public class MessageStreamBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(MessageStreamBean.class);

    @EJB(name = "ejb/MessageService")
    private MessageService messageService;
	
    //TODO: implement bean here
    
    
    private List<MessageDto> getAllMessages()
    {
    	return messageService.findUserRelated(1L);
    }
    
    public int getLikeCount(int messageId){
    	
    	
    	
    	return 5;
    }
    
    
}
