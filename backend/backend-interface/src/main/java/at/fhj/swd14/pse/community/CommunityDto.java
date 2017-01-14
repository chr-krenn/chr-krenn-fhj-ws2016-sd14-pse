package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.UserDto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommunityDto implements Serializable, Comparable<CommunityDto> {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String description;
    private boolean publicState = false;
    private boolean activeState = false;
    private String name;
    private UserDto author;
    private Date createTime = new Date(0);
    private List<UserDto> allowedUsers = new ArrayList<>();
    private List<UserDto> pendingUsers = new ArrayList<>();

    public CommunityDto() {
        // default public constructor
    }

    public CommunityDto(Long id) {
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getPublicState() {
        return this.publicState;
    }

    public void setPublicState(boolean publicState) {
        this.publicState = publicState;
    }

    public boolean getActiveState() {
        return this.activeState;
    }

    public void setActiveState(boolean activeState) {
        this.activeState = activeState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createDate) {
        this.createTime = createDate;
    }

    public List<UserDto> getAllowedUsers() {

        return this.allowedUsers;
    }

    public void setAllowedUsers(List<UserDto> allowedUsers) {
        this.allowedUsers = allowedUsers;
    }

    public void addUser(UserDto user) {
        this.allowedUsers.add(user);
    }

    public void deleteUser(Long userId) {
        this.allowedUsers.removeIf(user -> Objects.equals(user.getId(), userId));
    }

    public List<UserDto> getPendingUsers() {
        return pendingUsers;
    }

    public void setPendingUsers(List<UserDto> pendingUsers) {
        this.pendingUsers = pendingUsers;
    }

    public void addPendingUser(UserDto user) {
        this.pendingUsers.add(user);
    }

    public void deletePendingUser(Long userId) {
        this.pendingUsers.removeIf(user -> Objects.equals(user.getId(), userId));
    }

    @Override
    public String toString() {
        return "CommunityDto{" +
                "id=" + getId() +
                ", userId='" + getAuthor() + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CommunityDto other = (CommunityDto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(CommunityDto o) {
        return this.getName().compareTo(o.getName());
    }
}
