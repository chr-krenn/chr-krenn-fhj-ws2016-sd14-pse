package at.fhj.swd14.pse.tag;

import at.fhj.swd14.pse.message.MessageDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class TagDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Collection<MessageDto> messages;

    public TagDto() {
    }

    public TagDto(Long id) {

        setId(id);
    }

    public TagDto(Long id, String name) {

        setId(id);
        setName(name);
    }

    @Override
    public String toString() {
        return "TagDto{" +
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

    public Collection<MessageDto> getMessages() {
        return this.messages;
    }

    public void setMessages(Collection<MessageDto> messages) {
        this.messages = messages;
    }

    public void addMessage(MessageDto message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        this.messages.add(message);
    }

}
