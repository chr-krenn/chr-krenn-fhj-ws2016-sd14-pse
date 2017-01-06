package at.fhj.swd14.pse.repository.internal;

import org.junit.Test;

/**
 * This class just exists to increase coverage
 *
 * @author Patrick Kainz
 */
public class RepositoryCoverageTest {

    @Test
    public void increaseCoverage() {
        new DepartmentRepositoryImpl();
        new KnowledgeRepositoryImpl();
        new MailaddressRepositoryImpl();
        new PersonImageRepositoryImpl();
        new PersonRepositoryImpl();
        new PersonStatusRepositoryImpl();
        new PhonenumberRepositoryImpl();
        new HobbyRepositoryImpl();
        new MessageRepositoryImpl();
        new NewsRepositoryImpl();
        new CommentRepositoryImpl();
        new CommunityRepositoryImpl();
    }

}
