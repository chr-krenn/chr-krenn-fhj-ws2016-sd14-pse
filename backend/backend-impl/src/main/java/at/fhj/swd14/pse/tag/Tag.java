package at.fhj.swd14.pse.tag;

import at.fhj.swd14.pse.message.Message;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "tag")
@NamedQueries(@NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = LOWER(:name)"))
public class Tag implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5190605103970326377L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Collection<Message> messages;

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

    public Collection<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
