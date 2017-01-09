package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.department.DepartmentRepository;
import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.repository.AbstractPersonInformationRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import java.util.List;

/**
 * Verification class for persondtos
 *
 * @author Patrick Kainz
 */
@Local
@Singleton
public class PersonVerifier {

    @EJB
    private PersonRepository repository;
    @EJB
    private UserRepository userRepo;
    @EJB
    private PersonStatusRepository statusRepo;
    @EJB
    private DepartmentRepository departmentRepo;
    @EJB
    private PhonenumberRepository phonenumberRepo;
    @EJB
    private MailaddressRepository mailaddressRepo;
    @EJB
    private HobbyRepository hobbyRepo;
    @EJB
    private KnowledgeRepository knowledgeRepo;

    /**
     * Verifies the user of the person, which has to be not null, have an id and exist in the database
     *
     * @param person PersonDto for which to verify the user
     */
    public void verifyUser(PersonDto person) {
        if (person.getUser() == null || person.getUser().getId() == null) {
            throw new VerificationException("No user provided");
        }
        User user = userRepo.find(person.getUser().getId());
        if (user == null) {
            throw new VerificationException("Given user not found in the database");
        }
    }

    /**
     * Verifies the status for the given person, which has to be not null, have a name and exist
     *
     * @param person PersonDto for which to verify the status
     */
    public void verifyStatus(PersonDto person) {
        if (person.getStatus() == null || person.getStatus().getName() == null) {
            throw new VerificationException("No status provided");
        }
        Status status = statusRepo.findByName(person.getStatus().getName());
        if (status == null) {
            throw new VerificationException("Status invalid");
        }
    }

    /**
     * Verifies the name of a person, which has to be neither null nor empty
     *
     * @param person PersonDto for which to verify the name
     */
    public void verifyNotNull(PersonDto person) {
        if (person.getFirstname() == null || person.getFirstname().isEmpty()
                || person.getLastname() == null || person.getLastname().isEmpty()) {
            throw new VerificationException("No first and lastname given");
        }
    }

    /**
     * Verifies the Department of a person which has to be not null, have an id and exist
     *
     * @param person PersonDto for which to verify the department
     */
    public void verifyDepartment(PersonDto person) {
        //department may be null
        if (person.getDepartment() != null && person.getDepartment().getId() != null) {
            final Department department = departmentRepo.find(person.getDepartment().getId());
            if (department == null) {
                throw new VerificationException("Department not found in database");
            }
        }
    }

    /**
     * This utility function can verify a list of AbstractPersonInformationDto of a certain class
     * by checking:
     * - Does the client pass an id already? if so it has to exist in the database
     * - If not we check if the same value exists in the database and add the id to the dto object
     *
     * @param person PersonDto to verify these infos for
     * @param infos  List of K Objects to verify
     * @param repo   Repository to use for database queries
     */
    public <T extends AbstractPersonInformation, K extends AbstractPersonInformationDto>
    void correlatePersonInformation(PersonDto person, List<K> infos,
                                    AbstractPersonInformationRepository<T> repo) {
        for (final AbstractPersonInformationDto info : infos) {
            verifyPersonInformation(person, repo, info);
        }
    }

    public <T extends AbstractPersonInformation> void verifyPersonInformation(PersonDto person, AbstractPersonInformationRepository<T> repo, AbstractPersonInformationDto info) {
        //check if the id is set, if so we just need to check if it exists in the database
        if (info.getId() != null) {
            verifyExistence(repo, info);
        } else {
            //if we are adding a new person, there is no need to check this
            if (person.getId() != null) {
                verifyExistenceByValue(person, repo, info);
            }
        }
    }

    private <T extends AbstractPersonInformation> void verifyExistenceByValue(PersonDto person, AbstractPersonInformationRepository<T> repo, AbstractPersonInformationDto info) {
        //we need to check if that entry already exists
        AbstractPersonInformation real = repo.findByValue(person.getId(), info.getValue());
        if (real != null) {
            info.setId(real.getId()); //if it already exists just use the existing id
        }
    }

    private <T extends AbstractPersonInformation> void verifyExistence(AbstractPersonInformationRepository<T> repo, AbstractPersonInformationDto info) {
        AbstractPersonInformation real = repo.find(info.getId());
        if (real == null) {
            throw new VerificationException("The PersonInformation object with the id " + info.getId() + " does not exist");
        }
    }

    /**
     * Correlate (assign ids, if they are not yet set for info already in the database) and verify (if id is set
     * check if the info exist) any PhoenumberDto objects on a PersonDto
     *
     * @param person PersonDto to do the check for
     */
    public void correlateNumbers(PersonDto person) {
        this.correlatePersonInformation(person, person.getPhonenumbers(), phonenumberRepo);
    }

    /**
     * Correlate (assign ids, if they are not yet set for info already in the database) and verify (if id is set
     * check if the info exist) any MailaddressDto objects on a PersonDto
     *
     * @param person PersonDto to do the check for
     */
    public void correlateMails(PersonDto person) {
        this.correlatePersonInformation(person, person.getAdditionalMails(), mailaddressRepo);
    }

    /**
     * Correlate (assign ids, if they are not yet set for info already in the database) and verify (if id is set
     * check if the info exist) any KnowledgeDto objects on a PersonDto
     *
     * @param person PersonDto to do the check for
     */
    public void correlateKnowledges(PersonDto person) {
        this.correlatePersonInformation(person, person.getKnowledges(), knowledgeRepo);
    }

    /**
     * Correlate (assign ids, if they are not yet set for info already in the database) and verify (if id is set
     * check if the info exist) any HobbyDto objects on a PersonDto
     *
     * @param person PersonDto to do the check for
     */
    public void correlateHobbies(PersonDto person) {
        this.correlatePersonInformation(person, person.getHobbies(), hobbyRepo);
    }


}
