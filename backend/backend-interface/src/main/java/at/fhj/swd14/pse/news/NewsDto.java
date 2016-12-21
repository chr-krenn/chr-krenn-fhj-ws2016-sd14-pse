package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.person.PersonDto;

import java.io.Serializable;
import java.time.Instant;

public class NewsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String message;
    private PersonDto author;
    private Instant created;
    private Instant modified;
    private Instant activation;
    private Instant termination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonDto getAuthor() {
        return author;
    }

    public void setAuthor(PersonDto author) {
        this.author = author;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Instant getActivation() {
        return activation;
    }

    public void setActivation(Instant activation) {
        this.activation = activation;
    }

    public Instant getTermination() {
        return termination;
    }

    public void setTermination(Instant termination) {
        this.termination = termination;
    }
}
