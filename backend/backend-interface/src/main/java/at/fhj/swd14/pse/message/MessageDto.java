package at.fhj.swd14.pse.message;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.community.CommunityDto;
import at.fhj.swd14.pse.user.UserDto;

public class MessageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private List<CommentDto> childs = new LinkedList<>();
	private UserDto author;
	private UserDto recipient;
	
	private CommunityDto community; 

	private String title;
	private String content;
    private Instant created;
    private Instant modified;
	
	public MessageDto() {
	}

	public MessageDto(Long id) {
		this();
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getAuthor() {
		return author;
	}

	public void setAuthor(UserDto author) {
		this.author = author;
	}
	
	public List<CommentDto> getChilds() {
		return childs;
	}
	public void setChilds(List<CommentDto> childs){
		this.childs = childs;
	}
	
	public void addChild(CommentDto comment){
		if(childs == null)
			childs = new ArrayList<>();
		comment.setParentMessage(this);
		childs.add(comment);
	}

	/**
	 * returns the user who is the recipient from this private message.
	 * If it's not a private message it will return NULL
	 * @return recipient
	 */
	public UserDto getRecipient() {
		return recipient;
	}

	public void setRecipient(UserDto recipient) {
		this.recipient = recipient;
	}
	
	public CommunityDto getCommunity() {
		return community;
	}

	public void setCommunity(CommunityDto community) {
		this.community = community;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Instant getCreated() {
        return created;
    }
	
	/**
	 * do not use this setter on frontend site. It will be automatically set as
	 * soon as it gets saved to the DB. It's only need to convert an entity to a DTO
	 * @param created
	 */
	public void setCreated(Instant created){
		this.created = created;
	}

    public Instant getModified() {
        return modified;
    }
    /**
     * do not use this setter on frontend site. It will be automatically set as
	 * soon as it gets updated in the DB. It's only need to convert an entity to a DTO
     * @param modified
     */
    public void setModified(Instant modified) {
		this.modified = modified;
	}
	
	@Override
	public String toString(){
		return "MessageDto{" +
                "id=" + getId() +
                "recipientId=" + getRecipient() +
                ", userId='" + getAuthor().getId() + '\'' +
                ", communityId='" + getCommunity() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
	}

}
