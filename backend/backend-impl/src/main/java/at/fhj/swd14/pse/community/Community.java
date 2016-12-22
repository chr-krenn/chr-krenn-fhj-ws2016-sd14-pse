package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;

import javax.persistence.*;

import org.eclipse.persistence.platform.database.oracle.ucp.UCPDataPartitioningCallback;

import com.mysql.cj.mysqlx.protobuf.MysqlxDatatypes.Array;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "community")
@NamedQueries({
        @NamedQuery(name = "Community.findByAuthorId", query = "SELECT c FROM Community c WHERE c.author.id = :authorUserId"),
        @NamedQuery(name = "Community.findUserRelated", query = "SELECT c FROM Community c"),})
public class Community implements Serializable {
    private static final long serialVersionUID = 1L;


    public Community() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private boolean isPublic;

    public boolean getPublicState() {
        return this.isPublic;
    }

    public void setPublicState(boolean publicState) {
        this.isPublic = publicState;
    }

    @Column
    private boolean isActive;


    public boolean geActiveState() {
        return this.isActive;
    }

    public void setActiveState(boolean activeState) {
        this.isActive = activeState;
    }

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<UserCommunity> userCommunities = new ArrayList<>();

    public List<UserCommunity> getUserCommunities() {
        return userCommunities;
    }

    public void setUserCommunities(List<UserCommunity> userCommunities) {
        this.userCommunities = userCommunities;
    }

    public List<User> getAllowedUsers() {
    	
    	List<User> users = new ArrayList<>();
    	for (UserCommunity userCommunity : userCommunities) {
    		if(userCommunity.getActivated())
    			users.add(userCommunity.getUser());					
		}
    	
       return users;        
    }

    public void setAllowedUsers(List<User> allowedUsers) {

    	this.userCommunities.forEach(uc -> uc.setActivated(false));
   	 	
    	for (User user : allowedUsers) {
			
			if(containsSuchUser(user)){
				activateUserInUserCommunities(user);
				continue;
			} else {
				UserCommunity userCom = new UserCommunity(user, this, true);
				this.userCommunities.add(userCom);
			}			
		}   	
    }
    
    private boolean containsSuchUser(User user){
    for (UserCommunity userCommunity : this.userCommunities) {
		if(userCommunity.getUser().getId() == user.getId()){
			return true;
		} 
	}
    return false;
    }
    
    private boolean activateUserInUserCommunities(User user){
        for (UserCommunity userCommunity : this.userCommunities) {
    		if(userCommunity.getUser().getId() == user.getId()){
    			userCommunity.setActivated(true);
    			return true;
    		} 
    	}
    return false;
     
    }
    

	public void setAllowedUsersInactive(UserDto allowedUsers) {

			User user = UserConverter.convert(allowedUsers);
			UserCommunity userCom = new UserCommunity(user, this, false);
			
			int index = -1;
			
			for (int i = 0; i <  userCommunities.size(); i++) {
				if(userCommunities.get(i).getCommunity().getId() == this.getId() && userCommunities.get(i).getUser().getId() == user.getId()){
					index = i;
					break;
				}
			}
			
			this.userCommunities.get(index).setActivated(false);
    }
    

}