package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "community")
@NamedQueries({
        @NamedQuery(name = "Community.findByAuthorId", query = "SELECT c FROM Community c WHERE c.author.id = :authorUserId"),
        @NamedQuery(name = "Community.findUserRelated", query = "SELECT c FROM Community c"),
        @NamedQuery(name = "Community.findRequestedCommunities", query = "SELECT c FROM Community c WHERE c.id IN (select distinct uc.community.id from UserCommunity uc where uc.activated = false)"),})
public class Community implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private User author;

    @Column
    private boolean isPublic;

    @Column
    private boolean isActive;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<UserCommunity> userCommunities = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean getPublicState() {
        return this.isPublic;
    }

    public void setPublicState(boolean publicState) {
        this.isPublic = publicState;
    }


    public boolean geActiveState() {
        return this.isActive;
    }

    public void setActiveState(boolean activeState) {
        this.isActive = activeState;
    }

    public List<UserCommunity> getUserCommunities() {
        return userCommunities;
    }

    public void setUserCommunities(List<UserCommunity> userCommunities) {
        this.userCommunities = userCommunities;
    }

    public List<User> getAllowedUsers() {

        List<User> users = new ArrayList<>();
        for (UserCommunity userCommunity : userCommunities) {
            if (userCommunity.getActivated()) {
                users.add(userCommunity.getUser());
            }
        }

        return users;
    }

    public void setAllowedUsers(List<User> allowedUsers) {

        this.userCommunities.forEach(uc -> uc.setActivated(false));

        for (User user : allowedUsers) {
            if (containsSuchUser(user)) {
                activateUserInUserCommunities(user);
            } else {
                UserCommunity userCom = new UserCommunity(user, this, true);
                this.userCommunities.add(userCom);
            }
        }
    }

    public List<User> getPendingUsers() {

        List<User> users = new ArrayList<>();
        for (UserCommunity userCommunity : userCommunities) {
            if (!userCommunity.getActivated()) {
                users.add(userCommunity.getUser());
            }
        }

        return users;
    }

    public void setPendingUsers(List<User> pendingUsers) {

        for (User user : pendingUsers) {

            if (containsSuchUser(user)) {
                setAllowedUsersInactive(user);
            } else {
                UserCommunity userCom = new UserCommunity(user, this, false);
                this.userCommunities.add(userCom);
            }
        }
    }

    private boolean containsSuchUser(User user) {
        for (UserCommunity userCommunity : this.userCommunities) {
            if (Objects.equals(userCommunity.getUser().getId(), user.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean activateUserInUserCommunities(User user) {
        for (UserCommunity userCommunity : this.userCommunities) {
            if (Objects.equals(userCommunity.getUser().getId(), user.getId())) {
                userCommunity.setActivated(true);
                return true;
            }
        }
        return false;

    }


    public void setAllowedUsersInactive(User allowedUser) {

        int index = -1;

        for (int i = 0; i < userCommunities.size(); i++) {
            if (Objects.equals(userCommunities.get(i).getCommunity().getId(), this.getId())
                    && Objects.equals(userCommunities.get(i).getUser().getId(), allowedUser.getId())) {
                index = i;
                break;
            }
        }

        this.userCommunities.get(index).setActivated(false);
    }


}