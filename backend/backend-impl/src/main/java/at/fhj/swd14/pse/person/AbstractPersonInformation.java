package at.fhj.swd14.pse.person;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Abstract Super Class for ManyToOne Text Information Entitys for the Person class
 *
 * @author Patrick Kainz
 */
@MappedSuperclass
public abstract class AbstractPersonInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    @ManyToOne(optional = false)
    private Person person;

    public AbstractPersonInformation(Long id) {
        this.id = id;
    }

    public AbstractPersonInformation(String value) {
        setValue(value);
    }

    public AbstractPersonInformation() {

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
