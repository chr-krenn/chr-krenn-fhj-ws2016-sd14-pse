package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Person entity
 *
 * @author Patrick Kainz
 */
@Entity
@Table(name = "person")
@NamedQuery(name = "Person.findByUserId", query = "SELECT p FROM Person p WHERE p.user.id = :userid")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column
    private String address;

    @Column
    private String place;

    @Column(name = "imageurl")
    private String imageUrl;

    @OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Hobby> hobbies = new LinkedList<>();

    @OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Knowledge> knowledges = new LinkedList<>();

    @OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Mailaddress> additionalMails = new LinkedList<>();

    @OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Phonenumber> numbers = new LinkedList<>();

    @ManyToOne(optional = false)
    private Status status;

    @ManyToOne
    private Department department;

    public Person(Long id, User user) {
        this.id = id;
        setUser(user);
    }

    public Person() {
        //don't do anything, its just here so that you can instantiate without params
    }


    public List<Phonenumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Phonenumber> numbers) {
        this.numbers = numbers;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

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
        if (user == null) {
            throw new IllegalArgumentException("User may not be null");
        }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
