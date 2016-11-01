package at.fhj.swd14.pse.user;

import java.io.Serializable;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String lastname;
    private String firstname;

    public UserDto() {
    }

    public UserDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
