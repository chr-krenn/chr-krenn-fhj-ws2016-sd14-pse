package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

@Local
@Singleton
public class MessageRepositoryImpl
        extends AbstractRepository<Message>
        implements MessageRepository {

    public MessageRepositoryImpl() {
        super(Message.class);
    }

}
