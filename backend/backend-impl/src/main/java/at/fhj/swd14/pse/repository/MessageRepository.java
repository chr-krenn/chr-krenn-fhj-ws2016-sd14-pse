package at.fhj.swd14.pse.repository;

import javax.ejb.Stateless;

import at.fhj.swd14.pse.message.Message;

@Stateless
public class MessageRepository extends AbstractRepository<Message> {

    public MessageRepository() {
        super(Message.class);
    }

}
