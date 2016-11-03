package at.fhj.swd14.pse.tag;

import java.io.Serializable;

public class TagDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public TagDto(){}

    public TagDto(Long id){

        setId(id);
    }

    public TagDto(Long id, String name){

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
}
