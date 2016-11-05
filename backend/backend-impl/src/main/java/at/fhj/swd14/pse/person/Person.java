package at.fhj.swd14.pse.person;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import at.fhj.swd14.pse.user.User;

/**
 * Person entity
 * @author Patrick Kainz
 *
 */
@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(optional=false)
    private User user;
    
    @Column(nullable=false)
    private String firstname;
    
    @Column(nullable=false)
    private String lastname;
    
    @Column
    private String address;
    
    @Column
    private String place;
    
    @Column
    private String imageUrl;
    
    @OneToMany(mappedBy="person")
    private List<Hobby> hobbies;
    
    @OneToMany(mappedBy="person")
    private List<Knowledge> knowledges;
    
    @OneToMany(mappedBy="person")
    private List<Mailaddress> additionalMails;
    
    @OneToMany(mappedBy="person")
    private List<Phonenumber> numbers;
    
    @ManyToOne(optional=false)
    private Status status;
    
    public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public List<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}

	public List<Mailaddress> getAdditionalMails() {
		return additionalMails;
	}

	public void setAdditionalMails(List<Mailaddress> additionalMails) {
		this.additionalMails = additionalMails;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(user==null)
			throw new IllegalArgumentException("User may not be null");
		this.user = user;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Person(Long id, User user) {
        this.id = id;
        setUser(user);
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
