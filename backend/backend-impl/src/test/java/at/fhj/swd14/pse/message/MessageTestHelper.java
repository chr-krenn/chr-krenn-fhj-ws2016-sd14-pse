package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.user.User;

public class MessageTestHelper {

    public static Message getGlobalMessageDummy(Long id, User author) {

        return createMessage(id, author);
    }

    public static Message getCommunityMessageDummy(Long id, User author, Community community) {

        Message communityMessage = createMessage(id, author);
        communityMessage.setCommunity(community);
        return communityMessage;
    }

    public static Message getPrivateMessageDummy(Long id, User author, User recipient) {
        Message privateMessage = createMessage(id, author);
        privateMessage.setRecipient(recipient);
        return privateMessage;
    }

    private static Message createMessage(Long id, User author) {
        Message message = new Message(id);
        message.setAuthor(author);
        message.setTitle("dummy-title for message id '" + id + "'");
        message.setContent("dummy-content for message id '" + id + "'");
        return message;
    }

}
