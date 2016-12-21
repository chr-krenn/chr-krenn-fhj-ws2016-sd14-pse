package at.fhj.swd14.pse.person;

import java.io.Serializable;

/**
 * Dto for person status
 *
 * @author Patrick Kainz
 */
public class StatusDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public StatusDto(String name) {
        this.name = name;
    }

    public StatusDto() {
        //just here for instantiation
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
