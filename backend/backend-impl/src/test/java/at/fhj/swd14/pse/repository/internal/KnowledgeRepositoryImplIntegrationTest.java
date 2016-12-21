package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Knowledge;
import at.fhj.swd14.pse.person.Person;

public class KnowledgeRepositoryImplIntegrationTest extends AbstractPersonInformationRepositoryIntegrationTest<Knowledge> {

    public KnowledgeRepositoryImplIntegrationTest() {
        super(Knowledge.class);
    }

    @Override
    protected AbstractPersonInformationRepositoryImpl<Knowledge> getInfoRepository() {
        return new KnowledgeRepositoryImpl();
    }

    @Override
    protected Knowledge createDummyEntity(Person person) {
        Knowledge knowledge = new Knowledge();
        knowledge.setPerson(person);
        knowledge.setValue("test");
        return knowledge;
    }

}
