package at.fhj.swd14.pse.person.tools;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import at.fhj.swd14.pse.department.DepartmentConverter;
import at.fhj.swd14.pse.department.DepartmentService;
import at.fhj.swd14.pse.person.HobbyDto;
import at.fhj.swd14.pse.person.KnowledgeDto;
import at.fhj.swd14.pse.person.MailaddressDto;
import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.person.PhonenumberDto;
import at.fhj.swd14.pse.person.StatusDto;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

/**
 * This class performs handling for the loggedinpersonpage
 *
 * @author Patrick Kainz
 */
public class LoggedInPersonPageHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ERR_INCORRECT_INPUT = "Incorrect Input";
    private static final String MSG_ALREADY_EXISTS = " already exists";
    private static final String PARAM_VALUE = "value";

    private static final Logger LOGGER = LogManager.getLogger(LoggedInPersonPageHandler.class);

    private PersonBean bean;
    private UserService userService;
    private PersonService personService;
    private DepartmentService departmentService;
    private PersonVerifier verifier;

    public LoggedInPersonPageHandler(PersonBean bean) {
        if (bean == null)
            throw new IllegalArgumentException("bean may not be null");
        //as we are taking over responsibilities of the bean, we must use it as the sole datasource
        this.bean = bean;
        userService = bean.getUserService();
        personService = bean.getPersonService();
        departmentService = bean.getDepartmentService();
        verifier = bean.getVerifier();
    }

    /**
     * Load all stati to the bean
     */
    private void loadStati() {
        bean.setStati(new ArrayList<>(personService.findAllStati()));
        LOGGER.trace("Status values loaded, count=" + bean.getStati().size());
    }

    /**
     * Load all departments to the bean
     */
    private void loadDepartments() {
        bean.setDepartments(new ArrayList<>(departmentService.findAll()));
        DepartmentConverter.setDepartments(bean.getDepartments());
        LOGGER.trace("Department values loaded, count=" + bean.getDepartments().size());
    }

    /**
     * Loads the logged in person to the bean
     *
     * @return next page to navigate to
     */
    public String showLoggedInPerson() {
        Long loggedInUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
        LOGGER.trace("Showing person for logged in user " + loggedInUserId);

        if (shouldReloadUser(loggedInUserId)) {
            UserDto loggedInUser = userService.find(loggedInUserId);
            PersonDto person = personService.findByUser(loggedInUser);
            if (person == null) {
                //set empty person if nothing is found
                person = new PersonDto();
                LOGGER.trace("No person for logged in user " + loggedInUserId + " found");
            } else
                LOGGER.trace("Person for logged in user " + loggedInUserId + " found: " + person.getId());
            bean.setPerson(person);
        } else
            LOGGER.trace("Logged in person already loaded");
        //load all stati and departments to bean
        loadStati();
        loadDepartments();
        return "/user";
    }

    private boolean shouldReloadUser(Long loggedInUserId) {
        final PersonDto person = bean.getPerson();
        return person == null || !person.matchesUserId(loggedInUserId);
    }

    /**
     * This creates a person object to the service, with name only and for the logged in user
     *
     * @return next page to navigate to
     */
    public String createLoggedInPerson() {
        if (verifier.verifyName()) {
            //retrieve the logged in user
            Long loggedInUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
            UserDto loggedInUser = userService.find(loggedInUserId);
            bean.getPerson().setUser(loggedInUser);
            //status may not be null, so might as well set it to online
            bean.getPerson().setStatus(new StatusDto("online"));
            //store it
            personService.saveLoggedInPerson(bean.getPerson());
            LOGGER.debug("Person created for logged in user: " + loggedInUserId);
        } else
            LOGGER.debug("Name could not be verified, person not created");
        return showLoggedInPerson();
    }

    /**
     * This is just for sync purposes, we don't do anything here
     */
    public void saveData() {
        //we actually need to do nothing here, as long as the value is synchronized to the server
        LOGGER.trace("Person data saved to bean");
    }

    /**
     * Resets the image url of the current person to null
     */
    public void clearImgUrl() {
        bean.getPerson().setImageUrl(null);
        saveData();
        LOGGER.trace("Img URL cleared");
    }

    /**
     * Verifies and stores a person
     *
     * @return next page to navigate to
     */
    public String savePerson() {
        if (verifier.verifyPerson()) {
            personService.saveLoggedInPerson(bean.getPerson());
            LOGGER.debug("Person saved to backend");
        } else
            LOGGER.debug("Person could not be verified");
        return "/user";
    }

    /**
     * Adds a new mail to the list of mails on the person
     * Only on client side, call savePerson to save to backend
     */
    public void addMail() {
        //check if the mail already exists
        for (MailaddressDto existing : bean.getPerson().getAdditionalMails()) {
            if (existing.getValue().equals(bean.getNewMail())) {
                bean.growl(ERR_INCORRECT_INPUT, "Mail " + bean.getNewMail() + MSG_ALREADY_EXISTS);
                bean.setNewMail(null);
                return;
            }
        }
        MailaddressDto mail = new MailaddressDto();
        mail.setValue(bean.getNewMail());
        mail.setPerson(bean.getPerson());
        //verify the mail
        if (verifier.verifyMail(mail)) {
            //store it
            bean.getPerson().getAdditionalMails().add(mail);
            LOGGER.trace("Mail added: " + bean.getNewMail());
            //empty the field
            bean.setNewMail(null);
        } else
            LOGGER.trace("Mail could not be verified: " + bean.getNewMail());
    }

    /**
     * Adds a new knowledge to the list of Knowledges on the person
     * Only on client side, call savePerson to save to backend
     */
    public void addKnowledge() {
        //same process as addMail
        for (KnowledgeDto existing : bean.getPerson().getKnowledges()) {
            if (existing.getValue().equals(bean.getNewKnowledge())) {
                bean.growl(ERR_INCORRECT_INPUT, "Knowledge " + bean.getNewKnowledge() + MSG_ALREADY_EXISTS);
                bean.setNewKnowledge(null);
                return;
            }
        }
        KnowledgeDto knowledge = new KnowledgeDto();
        knowledge.setValue(bean.getNewKnowledge());
        knowledge.setPerson(bean.getPerson());
        if (verifier.verifyKnowledge(knowledge)) {
            bean.getPerson().getKnowledges().add(knowledge);
            LOGGER.trace("Knowledge saved: " + bean.getNewKnowledge());
            bean.setNewKnowledge(null);
        } else
            LOGGER.trace("Knowledge could not be verified: " + bean.getNewKnowledge());
    }

    /**
     * Adds a new hobby to the list of hobbies on the person
     * Only on client side, call savePerson to save to backend
     */
    public void addHobby() {
        //same process as addMail
        for (HobbyDto existing : bean.getPerson().getHobbies()) {
            if (existing.getValue().equals(bean.getNewHobby())) {
                bean.growl(ERR_INCORRECT_INPUT, "Hobby " + bean.getNewHobby() + MSG_ALREADY_EXISTS);
                bean.setNewHobby(null);
                return;
            }
        }
        HobbyDto hobby = new HobbyDto();
        hobby.setValue(bean.getNewHobby());
        hobby.setPerson(bean.getPerson());
        if (verifier.verifyHobby(hobby)) {
            bean.getPerson().getHobbies().add(hobby);
            LOGGER.trace("Hobby saved: " + bean.getNewHobby());
            bean.setNewHobby(null);
        } else
            LOGGER.trace("Hobby could not be verified: " + bean.getNewHobby());
    }

    /**
     * Adds a new number to the list of numbers on the person
     * Only on client side, call savePerson to save to backend
     */
    public void addNumber() {
        //same process as add mail
        for (PhonenumberDto existing : bean.getPerson().getPhonenumbers()) {
            if (existing.getValue().equals(bean.getNewNumber())) {
                bean.growl(ERR_INCORRECT_INPUT, "Number " + bean.getNewNumber() + MSG_ALREADY_EXISTS);
                bean.setNewNumber(null);
                return;
            }
        }
        PhonenumberDto number = new PhonenumberDto();
        number.setValue(bean.getNewNumber());
        number.setPerson(bean.getPerson());
        if (verifier.verifyNumber(number)) {
            bean.getPerson().getPhonenumbers().add(number);
            LOGGER.trace("Number saved: " + bean.getNewNumber());
            bean.setNewNumber(null);
        } else
            LOGGER.trace("Number could not be verified: " + bean.getNewNumber());
    }

    /**
     * Removes the given mail (GET/POST Parameter "value") from the person
     */
    public void removeMail() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String value = params.get(PARAM_VALUE);
        LOGGER.trace("Removing mail: " + value);
        for (MailaddressDto address : bean.getPerson().getAdditionalMails()) {
            if (value.equals(address.getValue())) {
                bean.getPerson().getAdditionalMails().remove(address);
                LOGGER.trace("Mail removed: " + value);
            }
        }
    }

    /**
     * Removes the given knowledge (GET/POST Parameter "value") from the person
     */
    public void removeKnowledge() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String value = params.get(PARAM_VALUE);
        LOGGER.trace("Removing Knowledge: " + value);
        for (KnowledgeDto knowledge : bean.getPerson().getKnowledges()) {
            if (value.equals(knowledge.getValue())) {
                bean.getPerson().getKnowledges().remove(knowledge);
                LOGGER.trace("Removed Knowledge: " + value);
            }
        }
    }

    /**
     * Removes the given hobby (GET/POST Parameter "value") from the person
     */
    public void removeHobby() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String value = params.get(PARAM_VALUE);
        LOGGER.trace("Removing Hobby: " + value);
        for (HobbyDto hobby : bean.getPerson().getHobbies()) {
            if (value.equals(hobby.getValue())) {
                bean.getPerson().getHobbies().remove(hobby);
                LOGGER.trace("Removed Hobby: " + value);
            }
        }
    }

    /**
     * Removes the given number (GET/POST Parameter "value") from the person
     */
    public void removeNumber() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String value = params.get(PARAM_VALUE);
        LOGGER.trace("Removing Number: " + value);
        for (PhonenumberDto number : bean.getPerson().getPhonenumbers()) {
            if (value.equals(number.getValue())) {
                bean.getPerson().getPhonenumbers().remove(number);
                LOGGER.trace("Removed Number: " + value);
            }
        }
    }

    /**
     * This handles a file upload from the html page
     *
     * @param event event containing file data and type
     */
    public void handleFileUpload(FileUploadEvent event) {
        LOGGER.trace("Uploading image");
        //load the person again just to be save
        showLoggedInPerson();
        //save the image
        personService.savePersonImage(bean.getPerson(), event.getFile().getContents(), event.getFile().getContentType());
        //set the url on the person to our image retrieval servlet so we can show the image we just uploaded
        bean.getPerson().setImageUrl("/swd14-fe/personImage?id=" + bean.getPerson().getId());
        //redirect to the page we came from, in order to reload the page
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            LOGGER.trace("Image upload complete");
        } catch (IOException ex) {
            bean.growl("Internal Error", "Error during page refresh");
            LOGGER.error("Error during page refresh", ex);
        }
    }

}
