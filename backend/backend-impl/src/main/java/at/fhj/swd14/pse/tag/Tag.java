package at.fhj.swd14.pse.tag;

import at.fhj.swd14.pse.message.Message;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tag")
@NamedQueries(@NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = LOWER(:name)"))
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Message> messages;

    public Tag() {
    }

    public Tag(Long id) {
        setId(id);
    }

    public Tag(Long id, String name) {
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

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {

        if(this.messages==null){
            messages = new ArrayList<Message>();
        }
        this.messages.add(message);
    }
}
