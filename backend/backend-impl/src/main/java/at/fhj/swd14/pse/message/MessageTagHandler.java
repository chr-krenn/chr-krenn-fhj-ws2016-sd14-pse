package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.tag.TagConverter;
import at.fhj.swd14.pse.tag.TagDto;
import at.fhj.swd14.pse.tag.TagService;
import at.fhj.swd14.pse.tag.TagServiceException;

import javax.ejb.EJB;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageTagHandler {

    @EJB
    private TagService tagService;

    private static final Logger LOGGER = LogManager.getLogger(MessageTagHandler.class);

    public void handleTags(MessageDto dto)  {
        if (dto != null) {

            Set<String> tagSet = new HashSet<>();

            // search for tags in title
            tagSet.addAll(findTags(dto.getTitle()));
            // search for tags in context
            tagSet.addAll(findTags(dto.getContent()));

            TagDto tagDto;

            for (String tag : tagSet) {

                tagDto = tagService.findByName(tag);

                if (tagDto == null) {
                	LOGGER.debug("Tag '" + tag + "' not found in DB, add it");
                    tagDto = new TagDto();
                    tagDto.setName(tag);
                    
                    saveTag(tagDto);
                    LOGGER.info("saved tag to db: " + tag);
                }

                dto.addTag(tagDto);
            }
        }
        else {
            throw new MessageTagHandlerException("message is null");
        }

    }

    private void saveTag(TagDto tagDto) {
        try {
            tagService.save(tagDto);
        }
        catch (VerificationException e) {
        	LOGGER.error(e.getMessage(), e);
        	throw new MessageTagHandlerException("Tag Service error: " + e.getMessage());
        	
        }
        catch (TagServiceException e) {
        	LOGGER.error(e.getMessage(), e);
        	throw new MessageTagHandlerException("Error saving tag to DB: " + e.getMessage());
        }
    }
    
    private Set<String> findTags(String text) {
        Set<String> tagSet = new HashSet<>();

        if (text != null) {

	        Pattern p = Pattern.compile("#(\\w+)");
	
	        Matcher matcher = p.matcher(text);
	
	        while (matcher.find()) {
	        	LOGGER.debug("Tag found: " + matcher.group(0).substring(1).toLowerCase());
	            tagSet.add(matcher.group(0).substring(1).toLowerCase());
	        }
        }
        return tagSet;
    }
}
