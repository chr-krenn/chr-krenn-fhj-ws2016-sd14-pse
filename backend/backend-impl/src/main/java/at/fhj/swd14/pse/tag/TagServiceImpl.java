package at.fhj.swd14.pse.tag;

import at.fhj.swd14.pse.exception.VerificationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Stateless
public class TagServiceImpl implements TagService {

    @EJB
    private TagRepository tagRepository;

    private static final Logger LOGGER = LogManager.getLogger(TagServiceImpl.class);

    @Override
    public long save(TagDto tag) {
    	

        try {
            if (tag == null){

                throw new VerificationException("TagDto was null");
            }
            
            Tag find = TagConverter.convert(findByName(tag.getName()));
            
            if (find == null) {
	            Tag doTag = TagConverter.convert(tag);
	            tagRepository.save(doTag);
	            LOGGER.info("Tag saved to DataBase.");
	            return doTag.getId();
            }
            else {
            	return find.getId();
            }
        }
        catch(VerificationException e){

            LOGGER.error("Invalid Input: " + e.getMessage(), e);
            throw new TagServiceException("Invalid Input: " + e.getMessage());
        }
        catch(PersistenceException e){

            LOGGER.error("Error saving Tag to Database: " + e.getMessage(), e);
            throw new TagServiceException("Error saving Tag to Database: " + e.getMessage());
        }
        catch(Exception e) {

            LOGGER.error("An unexpected error occured while saving Tag to DB: " + e.getMessage(), e);
            throw new TagServiceException("An unexpected error occured while saving Tag to DB: " + e.getMessage());
        }
    }

    @Override
    public TagDto find(long id) {

        try {
            LOGGER.debug("Find Tag with ID: "+id);
            return TagConverter.convert(tagRepository.find(id));
        }
        catch(EntityNotFoundException e){

            LOGGER.info("Tag with ID "+id+" not found: "+e.getMessage(), e);
            throw new TagServiceException("Tag with ID "+id+" not found: "+e.getMessage());
        }
        catch(Exception e){

            LOGGER.error("Unexpected error finding Tag with ID "+id+":"+e.getMessage(), e);
            throw new TagServiceException("Unexpected error finding Tag with ID "+id+":"+e.getMessage());
        }
    }

    @Override
    public TagDto findByName(String name) {
        try {

            if(name == null || name.isEmpty()){

                throw new VerificationException("Tag-Name darf nicht leer sein.");
            }

            Map<String, Object> parameter = new HashMap<>();
            parameter.put("name", name);
            List<TagDto> tagList = executeNamedQuery("Tag.findByName", parameter);
            if (!tagList.isEmpty()) {
                return tagList.get(0);
            } else {
                return null;
            }
        }
        catch(NoResultException e){

            LOGGER.info("No Tag was found with name '"+name+"': "+e.getMessage(), e);
            throw new TagServiceException("No Tag was found with name '"+name+"': "+e.getMessage());
        }
        catch(QueryTimeoutException e){

            LOGGER.warn("Query tag.findByName timeouted: "+e.getMessage(), e);
            throw new TagServiceException("Zeitüberschreitung der Anfrage: "+ e.getMessage());
        }
        catch(Exception e){

            LOGGER.error("Unexpected error finding Tag with Name '"+name+"': "+ e.getMessage(), e);
            throw new TagServiceException("Unexpected eroor finding Tag with Name '"+name+"': "+ e.getMessage());
        }
    }

      
    public List<TagDto> findAll() {

        try{

            LOGGER.debug("Find all Tags");
            return TagConverter.convertToDtoList(tagRepository.findAll());
        }
        catch(Exception e){

            LOGGER.error("Unexpected error finding all Tags: "+ e.getMessage(), e);
            throw new TagServiceException("Unexpected error finding all Tags: "+ e.getMessage());
        }
    }

    private List<TagDto> executeNamedQuery(String name, Map<String, Object> parameter) {
        return new ArrayList<>(TagConverter.convertToDtoList(tagRepository.executeNamedQuery(name, parameter)));
    }
}
