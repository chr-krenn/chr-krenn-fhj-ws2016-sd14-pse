package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.message.Message;

@Local
@Singleton
public class MessageRepository extends AbstractRepository<Message> {

    public MessageRepository() {
        super(Message.class);
    }

}
