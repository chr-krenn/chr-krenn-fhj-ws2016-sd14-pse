package at.fhj.swd14.pse.converter;

import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.tag.TagDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class TagConverter {

    private TagConverter(){};

    /**
     * Converts a Tag to a TagDto
     * @param tag
     * @return tagDto
     */
    public static TagDto convert(Tag tag) {
        if (tag == null) {
            return null;
        }
        return new TagDto(tag.getId(), tag.getName());
    }

    /**
     * Converts a TagDto to a Tag
     * @param tagDto
     * @return tag
     */
    public static Tag convert(TagDto tagDto) {
        if (tagDto == null) {
            return null;
        }
        return new Tag(tagDto.getId(), tagDto.getName());
    }

    /**
     * Converts a List of Tags to a List of TagDtos
     * @param tags
     * @return tagDtos
     */
    public static Collection<TagDto> convertToDtoList(Collection<Tag> tags) {
        if (tags == null) {
            return null;
        }
        return tags.stream().map(TagConverter::convert).collect(Collectors.toList());
    }

    /**
     * Converts a List of TagDtos to a List of Tags
     * @param tagDtos
     * @return tags
     */
    public static Collection<Tag> convertToList(Collection<TagDto> tagDtos) {
        if (tagDtos == null) {
            return null;
        }
        return tagDtos.stream().map(TagConverter::convert).collect(Collectors.toList());
    }
}
