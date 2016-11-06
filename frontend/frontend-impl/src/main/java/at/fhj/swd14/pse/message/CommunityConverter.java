package at.fhj.swd14.pse.message;

//TODO: Change CommunityDtoStub to CommunityDto in this file
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.community.CommunityDtoStub;

@FacesConverter("communityConverter")
public class CommunityConverter implements Converter {
	/**
	 * The LOGGER to use
	 */
	private static final Logger LOGGER = LogManager.getLogger(CommunityConverter.class);

	// @EJB
	// CommunityService communityService;

	/**
	 * Converter Method to get the right Community for the Selectbox
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CommunityConverter.LOGGER.debug("getAsObject for Value: "+value);
		//TODO: Change to use real communityService and real CommunityDto
		if (value == null || Long.parseLong(value) <= 0) {
			return null;
		}
		Long id = Long.parseLong(value);
		CommunityDtoStub c = new CommunityDtoStub(id);
		c.setName("Community_"+c.getId());

		CommunityConverter.LOGGER.debug("getAsObject returns: "+c);
		return c;
//TODO:		return communityService.findById(id);
	}

	/**
	 * Converter Method to get the right string of a Community for the Selectbox
	 */
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		CommunityConverter.LOGGER.debug("getAsString for Value: "+value);
		//TODO: Change to use real communityService and real CommunityDto
		return value instanceof CommunityDtoStub ? ((CommunityDtoStub) value).getId().toString() : "";
	}
}