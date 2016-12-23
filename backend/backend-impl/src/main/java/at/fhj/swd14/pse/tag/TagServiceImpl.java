package at.fhj.swd14.pse.tag;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (!tagList.isEmpty()) {
            return tagList.get(0);
        } else {
            return null;
        }
    }

    public List<TagDto> findAll() {
        return (List<TagDto>) TagConverter.convertToDtoList(tagRepository.findAll());
    }

    private List<TagDto> executeNamedQuery(String name, Map<String, Object> parameter) {
        return new ArrayList<>(TagConverter.convertToDtoList(tagRepository.executeNamedQuery(name, parameter)));
    }
}
