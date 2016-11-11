package at.fhj.swd14.pse.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.converter.TagConverter;
import at.fhj.swd14.pse.repository.TagRepository;

//Todo: implement methods
@Stateless
public class TagServiceImpl implements TagService {

    @EJB
    private TagRepository tagRepository;

    @Override
    public long save(TagDto tag) {
        Tag doTag = TagConverter.convert(tag);
        tagRepository.save(doTag);
        return doTag.getId();
    }

    @Override
    public TagDto find(long id) {
        return TagConverter.convert(tagRepository.find(id));
    }

    @Override
    public TagDto findByName(String name) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("tagName", name);
        List<TagDto> tagList = executeNamedQuery("Tag.findByName", parameter);
        if (tagList.size() > 0)
            return tagList.get(0);
        else
            return null;
    }

	public List<TagDto> findAll() {
		return (List<TagDto>) TagConverter.convertToDtoList(tagRepository.findAll());
	}
    
    private List<TagDto> executeNamedQuery(String name, Map<String, Object> parameter) {
        return new ArrayList<>(TagConverter.convertToDtoList(tagRepository.executeNamedQuery(name, parameter)));
    }
}
