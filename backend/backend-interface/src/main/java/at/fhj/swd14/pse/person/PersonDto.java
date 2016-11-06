package at.fhj.swd14.pse.person;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.user.UserDto;

/**
 * Data transfer object for person
 *
 * @author Patrick Kainz
 */
public class PersonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstname;

    private String lastname;

    private String address;

    private String place;

    private String imageUrl;

    private String status;

    private List<String> hobbies = new LinkedList<>();

    private List<String> knowledges = new LinkedList<>();

    private List<String> additionalMails = new LinkedList<>();

    private List<String> phonenumbers = new LinkedList<>();

    private DepartmentDto department;

    private UserDto user;

    private long id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(List<String> knowledges) {
        this.knowledges = knowledges;
    }

    public List<String> getAdditionalMails() {
        return additionalMails;
    }

    public void setAdditionalMails(List<String> additionalMails) {
        this.additionalMails = additionalMails;
    }

    public List<String> getPhonenumbers() {
        return phonenumbers;
    }

    public void setPhonenumbers(List<String> phonenumbers) {
        this.phonenumbers = phonenumbers;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public PersonDto(long id) {
        setId(id);
    }


}
