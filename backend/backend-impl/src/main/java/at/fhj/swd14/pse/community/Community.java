package at.fhj.swd14.pse.community;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

@Entity
@Table(name = "community")
public class Community implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
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

    //	private User author;
    public User getAuthor() {
        return null;
//        return this.author;
    }

    public void setAuthor(User author) {
//		this.author = author;
    }

    @Column
    private boolean isPublic = false;

    public boolean getPublicState() {
        return this.isPublic;
    }

    public void setPublicState(boolean publicState) {
        this.isPublic = publicState;
    }

//    private List<UserDto> allowedUsers = new ArrayList<UserDto>();

    public List<UserDto> getAllowedUsers() {        
//    	return this.allowedUsers;
    	return null;
    }

    public void setAllowedUsers(List<UserDto> allowedUsers) {
//        this.allowedUsers = allowedUsers;
    }

    @Column
    private Date createTime = new Date(0);

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createDate) {
        this.createTime = createDate;
    }

}
