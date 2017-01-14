package at.fhj.swd14.pse.message;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.community.CommunityDto;
import at.fhj.swd14.pse.tag.TagDto;
import at.fhj.swd14.pse.user.UserDto;

public class MessageDto implements Serializable, Comparable<MessageDto> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private List<CommentDto> childs = new LinkedList<>();
    private UserDto author;
    private UserDto recipient;

    private CommunityDto community;

    private List<TagDto> tags;

    private String title;
    private String content;
    private Instant created;
    private Instant modified;

    public MessageDto() {
    }

    public MessageDto(Long id) {
        this();
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public List<CommentDto> getChilds() {
        return childs;
    }

    public void setChilds(List<CommentDto> childs) {
        this.childs = childs;
    }

    public void addChild(CommentDto comment) {
        if (childs == null) {
            childs = new ArrayList<>();
        }
        comment.setParentMessage(this);
        childs.add(comment);
    }

    /**
     * returns the user who is the recipient from this private message. If it's
     * not a private message it will return NULL
     *
     * @return recipient
     */
    public UserDto getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDto recipient) {
        this.recipient = recipient;
    }

    public CommunityDto getCommunity() {
        return community;
    }

    public void setCommunity(CommunityDto community) {
        this.community = community;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreated() {
        return created;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public void addTag(TagDto tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }

    /**
     * do not use this setter on frontend site. It will be automatically set as
     * soon as it gets saved to the DB. It's only need to convert an entity to a
     * DTO
     *
     * @param created
     */
    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    /**
     * do not use this setter on frontend site. It will be automatically set as
     * soon as it gets updated in the DB. It's only need to convert an entity to
     * a DTO
     *
     * @param modified
     */
    public void setModified(Instant modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "MessageDto{" + "id=" + id + ", childs=" + childs + ", author=" + author + ", recipient=" + recipient
                + ", community=" + community + ", tags=" + tags + ", title='" + title + '\'' + ", content='" + content
                + '\'' + ", created=" + created + ", modified=" + modified + '}';
    }

    @Override
    public int compareTo(MessageDto o) {
        if (o.getCreated().isBefore(this.getCreated())) {
            return -1;
        } else if (o.getCreated().isAfter(this.getCreated())) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MessageDto))
            return false;
        MessageDto that = (MessageDto) o;
        boolean sameParameters = Objects.equals(id, that.id)
                && Objects.equals(childs, that.childs)
                && Objects.equals(recipient, that.recipient)
                && Objects.equals(community, that.community)
                && Objects.equals(tags, that.tags)
                && Objects.equals(title, that.title)
                && Objects.equals(content, that.content);

        // UserDto uses object equals therefore we have to check the author by id
        boolean sameAuthorNull = author == null && that.author == null;
        boolean sameAuthorNotNull = author != null && that.author != null;
        boolean sameAuthor = sameAuthorNull || (sameAuthorNotNull && author.getId().equals(that.author.getId()));
        return sameParameters && sameAuthor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, childs, author, recipient, community, tags, title, content, created, modified);
    }

}
