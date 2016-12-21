package at.fhj.swd14.pse.person;

import javax.persistence.*;
import java.io.Serializable;

/**
 * PersonImage Entity
 *
 * @author Patrick Kainz
 */
@Entity
@Table(name = "person_image")
@NamedQuery(name = "PersonImage.findByPerson", query = "SELECT p FROM PersonImage p WHERE p.person.id = :personid")
public class PersonImage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private byte[] data;

    @Column(name = "content_type")
    private String contentType;


    @ManyToOne(optional = false)
    private Person person;

    public PersonImage() {
        super();
    }

    public PersonImage(Long id) {
        setId(id);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


}
