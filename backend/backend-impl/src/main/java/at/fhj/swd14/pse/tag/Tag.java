package at.fhj.swd14.pse.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class Tag implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5190605103970326377L;

	@Id
    private Long id;

    @Column
    private String name;

    public Tag(){}

    public Tag(Long id){

        setId(id);
    }

    public Tag(Long id, String name){

        setId(id);
        setName(name);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //---Getters and Setters---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
