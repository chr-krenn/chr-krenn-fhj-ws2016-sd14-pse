package at.fhj.swd14.pse.repository.internal;


import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageConverter;
import at.fhj.swd14.pse.message.MessageAssert;
import at.fhj.swd14.pse.user.User;

public class MessageRepositoryImplIntegrationTest extends AbstractRepositoryIDIntegrationTest<Message> {

    public MessageRepositoryImplIntegrationTest() {
        super(Message.class);
    }

    @Override
    protected long getDummyId(Message dummy) {
        return dummy.getId();
    }

    @Override
    protected AbstractRepository<Message> getRepository() {
        return new MessageRepositoryImpl();
    }

    @Override
    protected Message createDummyEntity() {
    	User author = new User();
    	author.setMail("user@swd.com");
    	author.setPassword("password");
    	author.setSalt("salt");
    	
    	manager.persist(author);
    	manager.flush();
    	Message message = new Message();
    	message.setAuthor(author);
    	message.setTitle("It's a title");
    	message.setContent("It's a content");
        return message;
    }

    @Override
    protected void assertEquals(Message expected, Message actual) {
        MessageAssert.assertEquals(MessageConverter.convert(expected), MessageConverter.convert(actual));
    }

    @Override
    protected Message modifyDummy(Message dummy) {
    	dummy.setTitle("It's another title");
        return dummy;
    }

    @Override
    protected void copyDummyPK(Message destination, Message source) {
        destination.setId(source.getId());
    }

}
