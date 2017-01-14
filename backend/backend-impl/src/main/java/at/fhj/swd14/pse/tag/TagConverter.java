package at.fhj.swd14.pse.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class TagConverter {

    private TagConverter() {
    }

    /**
     * Converts a Tag to a TagDto
     *
     * @param tag
     * @return tagDto
     */
    public static TagDto convert(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagDto dto = new TagDto(tag.getId(), tag.getName());
        return dto;
    }

    /**
     * Converts a TagDto to a Tag
     *
     * @param tagDto
     * @return tag
     */
    public static Tag convert(TagDto tagDto) {
        if (tagDto == null) {
            return null;
        }
        Tag tag = new Tag(tagDto.getId(), tagDto.getName());
        return tag;
    }

    /**
     * Converts a List of Tags to a List of TagDtos
     *
     * @param tags
     * @return tagDtos
     */
    public static List<TagDto> convertToDtoList(Collection<Tag> tags) {
        if (tags == null) {
            return new ArrayList<>();
        }
        return tags.stream().map(TagConverter::convert).collect(Collectors.toList());
    }

    /**
     * Converts a List of TagDtos to a List of Tags
     *
     * @param tagDtos
     * @return tags
     */
    public static List<Tag> convertToList(Collection<TagDto> tagDtos) {
        if (tagDtos == null) {
            return new ArrayList<>();
        }
        return tagDtos.stream().map(TagConverter::convert).collect(Collectors.toList());
    }
}
