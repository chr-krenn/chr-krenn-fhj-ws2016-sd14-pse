package at.fhj.swd14.pse.tag;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import at.fhj.swd14.pse.message.Message;

@Entity
@Table(name = "tag")
@NamedQueries({
	@NamedQuery(name="Tag.findByName", query="SELECT t FROM Tag t WHERE t.name = LOWER(:name)"),
})
public class Tag implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5190605103970326377L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Message> messages;

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

    public List<Message> getMessages(){return this.messages;}
    public void setMessages(List<Message> messages){this.messages = messages;}
    public void addMessage(Message message){this.messages.add(message);}
}
