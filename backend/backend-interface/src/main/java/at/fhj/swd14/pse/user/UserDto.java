package at.fhj.swd14.pse.user;

import java.io.Serializable;

import javax.persistence.Column;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String mail;    
    
    private String password;
    
    private String salt;

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

    public String getMail() {
  		return mail;
  	}

  	public void setMail(String mail) {
  		this.mail = mail;
  	}

  	public String getPassword() {
  		return password;
  	}

  	public void setPassword(String password) {
  		this.password = password;
  	}

  	public String getSalt() {
  		return salt;
  	}

  	public void setSalt(String salt) {
  		this.salt = salt;
  	}

  	@Override
      public String toString() {
          return "User{" +
                  "id=" + id +
                  ", mail='" + mail + '\'' +
                  '}';
      }
}
