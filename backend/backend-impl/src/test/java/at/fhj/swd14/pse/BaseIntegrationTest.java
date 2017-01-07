package at.fhj.swd14.pse;

import at.fhj.swd14.pse.base.IntegrationTestUtil;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.comment.CommentService;
import at.fhj.swd14.pse.community.CommunityDto;
import at.fhj.swd14.pse.community.CommunityService;
import at.fhj.swd14.pse.database.DatabaseTestUtil;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.message.MessageService;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;
import at.fhj.swd14.pse.user.UserServiceHelper;
import org.junit.Before;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public abstract class BaseIntegrationTest {
    protected MessageService messageService;
    protected UserService userService;
    protected CommentService commentService;
    protected CommunityService communityService;
    protected UserDto user;
    protected CommunityDto community;
    protected MessageDto message;
    protected CommentDto comment;
    protected EntityManager manager;

    @Before
    public void initEntityManager() {
        manager = DatabaseTestUtil.getEntityManager();
    }

    protected void initServices() {
        messageService = IntegrationTestUtil.getService(MessageService.class);
        userService = IntegrationTestUtil.getService(UserService.class);
        commentService = IntegrationTestUtil.getService(CommentService.class);
        communityService = IntegrationTestUtil.getService(CommunityService.class);
    }

    public void insertUser() {
        user = UserServiceHelper.insertUser(userService);
    }

    protected void getCommunity() {
        community = communityService.findAll().get(0);
    }

    protected void insertMessage() {
        message = new MessageDto();
        message.setAuthor(user);
        message.setCommunity(community);
        message.setContent("testcontent");
        message.setCreated(Instant.now());
        message.setModified(Instant.now());
        message.setTitle("testtitle");
        long messageId = messageService.save(message);
        message.setId(messageId);
    }

    protected void insertCommentForMessage() {
        comment = new CommentDto();
        comment.setAuthor(user);
        comment.setParentMessage(message);
        comment.setText("testcomment");
        long commentId = commentService.save(comment);
        comment.setId(commentId);

        ArrayList<CommentDto> comments = new ArrayList<CommentDto>();
        comments.add(comment);
        message.setChilds(comments);
    }

    protected Person getTestingPerson() {
        List<Person> persons = manager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        assertTrue(!persons.isEmpty());
        return persons.get(0);
    }
}
