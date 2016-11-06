package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.person.Person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "news")
@NamedQueries(
        @NamedQuery(name = "News.findSince", query = "SELECT n FROM News n WHERE n.created >= :sinceDate"),
        @NamedQuery(name = "News.findByAuthorId", query = "SELECT n FROM News n WHERE n.author.id = :sinceDate")
)
public class News implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 256)
    @NotNull
    @Column(nullable = false)
    private String title;

    @Size(max = 1024)
    @NotNull
    @Column(nullable = false)
    private String message;

    @Column(insertable = false, updatable = false)
    private Instant created;

    @Column(insertable = false, updatable = false)
    private Instant modified;

    /*@NotNull
    @ManyToOne
    TODO @rraumberger uncomment when person gets introduced
    */
    @Column(nullable = true)
    private Person author;

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

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getModified() {
        return modified;
    }
}
