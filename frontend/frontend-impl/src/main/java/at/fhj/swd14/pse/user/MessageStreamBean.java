package at.fhj.swd14.pse.user;

import javax.ejb.EJB;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.message.MessageService;

@Named
public class MessageStreamBean {

	private static final Logger LOGGER = LogManager.getLogger(MessageStreamBean.class);

    @EJB(name = "ejb/MessageService")
    private MessageService messageService;
	
    //TODO: implement bean here
}
