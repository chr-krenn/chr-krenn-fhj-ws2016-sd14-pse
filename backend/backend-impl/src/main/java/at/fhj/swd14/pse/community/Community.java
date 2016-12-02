package at.fhj.swd14.pse.community;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

@Entity
@Table(name = "community")
@NamedQueries({
	@NamedQuery(name="Community.findByAuthorId", query="SELECT c FROM Community c WHERE c.author.id = :authorUserId"),
	@NamedQuery(name="Community.findUserRelated", query="SELECT c FROM Community c"),
	@NamedQuery(name="Community.findAllowedUsers", query="SELECT u FROM User u , CommunityUser cu WHERE cu.community.id = :communityId and cu.user.id = u.id")
})
public class Community implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @ManyToOne
	private User author;
    public User getAuthor() {
        
       return this.author;
    }

    public void setAuthor(User author) {
		this.author = author;
    }

    @Column
    private boolean isPublic = false;

    public boolean getPublicState() {
        return this.isPublic;
    }

    public void setPublicState(boolean publicState) {
        this.isPublic = publicState;
    }
    
    @Column
    private boolean isActive = false;

    public boolean geActiveState() {
        return this.isActive;
    }

    public void setActiveState(boolean activeState) {
        this.isActive = activeState;
    }
    
    @ManyToMany(mappedBy = "communities")
    private List<User> allowedUsers;

    public List<UserDto> getAllowedUsers() {        
    	if (UserConverter.convertToDtoList(this.allowedUsers) instanceof List) {
    		return (List)UserConverter.convertToDtoList(this.allowedUsers);
    	}
    	return null;//TODO @Thomas
    }

    public void setAllowedUsers(List<UserDto> allowedUsers) {
       this.allowedUsers = (List)UserConverter.convertToDoList(allowedUsers);
    }

}
