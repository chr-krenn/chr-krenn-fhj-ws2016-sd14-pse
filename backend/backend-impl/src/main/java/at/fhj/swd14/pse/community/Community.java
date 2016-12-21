package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public List<UserDto> getAllowedUsers() {
        List<User> allUsers = new ArrayList<>();

        userCommunities.forEach(userComm -> allUsers.add(userComm.getUser()));

        List<UserDto> allUsersDtos = (List<UserDto>) UserConverter.convertToDtoList((Collection) (allUsers));
        if ((allUsersDtos) instanceof List) {
            return allUsersDtos;
        }

        return new ArrayList<UserDto>();

    }

    public void setAllowedUsers(List<UserDto> allowedUsers) {

        //       this.allowedUsers = (List)UserConverter.convertToDoList(allowedUsers);
    }


}