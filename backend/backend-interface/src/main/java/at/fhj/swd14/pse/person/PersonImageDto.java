package at.fhj.swd14.pse.person;

import java.io.Serializable;

/**
 * Dto for person image
 *
 * @author Patrick Kainz
 */
public class PersonImageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private PersonDto person;
    private byte[] data;
    private String contentType;
    private Long id;

    public PersonImageDto() {
        //just here to instantiate
    }

    public PersonImageDto(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }


}
