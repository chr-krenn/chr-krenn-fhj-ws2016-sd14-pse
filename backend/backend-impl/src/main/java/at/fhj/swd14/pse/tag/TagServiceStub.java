package at.fhj.swd14.pse.tag;


import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class TagServiceStub implements TagService {

	private Long id = 1L;
	
	private List<TagDto> tags = new ArrayList<>();
	
	public List<TagDto> getTags() {
		return tags;
	}

    @Override
    public long save(TagDto tag) {
    	tag.setId(id++);
        tags.add(tag);
        return tag.getId();
    }

    @Override
    public TagDto find(long id) {
        return null;
    }

    @Override
    public TagDto findByName(String name) {
    	return null;
    }

}
