package at.fhj.swd14.pse.department;

import at.fhj.swd14.pse.person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Department Entity
 *
 * @author Patrick Kainz
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Person> members;

    public Department(Long id) {
        this.id = id;
    }

    public Department() {
        // default public constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
