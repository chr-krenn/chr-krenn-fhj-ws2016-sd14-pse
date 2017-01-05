package at.fhj.swd14.pse;

import java.time.Instant;
import java.util.ArrayList;

import at.fhj.swd14.pse.base.IntegrationTestUtil;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.comment.CommentService;
import at.fhj.swd14.pse.community.CommunityDto;
import at.fhj.swd14.pse.community.CommunityService;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.message.MessageService;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

public abstract class BaseIntegrationTest {
	protected MessageService messageService;
	protected UserService userService;
	protected CommentService commentService;
	protected CommunityService communityService;
	protected UserDto user;
	protected CommunityDto community;
	protected MessageDto message;
	protected CommentDto comment;
	
    public void initServices() {
		messageService = IntegrationTestUtil.getService(MessageService.class);
		userService = IntegrationTestUtil.getService(UserService.class);
		commentService = IntegrationTestUtil.getService(CommentService.class);
		communityService = IntegrationTestUtil.getService(CommunityService.class);
    }
    
    public void insertUser(){
    	user = new UserDto();
		user.setMail("user@test.com");
		user.setPassword("user");
		user.setSalt("salt");
		long userId = userService.save(user);
		user.setId(userId);
		ArrayList<UserDto> userList = new ArrayList<UserDto>();
		userList.add(user);
    }
    
    public void getCommunity(){
    	community = communityService.findAll().get(0);
    }
    
    public void insertMessage(){
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
    
    public void insertCommentForMessage(){
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
}
