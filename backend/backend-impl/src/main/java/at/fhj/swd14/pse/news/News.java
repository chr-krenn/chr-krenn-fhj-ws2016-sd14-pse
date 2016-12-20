package at.fhj.swd14.pse.news;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import at.fhj.swd14.pse.person.Person;

@Entity
@Table(name = "news")
@NamedQueries({
        @NamedQuery(name = "News.findSince", query = "SELECT n FROM News n WHERE n.created >= :sinceDate"),
        @NamedQuery(name = "News.findByAuthorId", query = "SELECT n FROM News n WHERE n.author.id = :sinceDate"),
        @NamedQuery(name = "News.findAllOnline", query = "SELECT n FROM News n WHERE :onlineDate >= n.activation" +
                " AND (n.termination IS NULL OR :onlineDate < n.termination)" +
                " ORDER BY n.created DESC")
})
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

    @Column
    private Instant activation;

    @Column
    private Instant termination;

    @NotNull
    @ManyToOne(optional = false)
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
