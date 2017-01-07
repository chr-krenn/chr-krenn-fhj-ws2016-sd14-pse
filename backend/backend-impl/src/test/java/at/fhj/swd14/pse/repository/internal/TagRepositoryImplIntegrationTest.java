package at.fhj.swd14.pse.repository.internal;


import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.tag.TagConverter;
import at.fhj.swd14.pse.tag.TagAssert;
import org.junit.Assert;
import org.junit.Test;

public class TagRepositoryImplIntegrationTest extends AbstractRepositoryIDIntegrationTest<Tag> {

    public TagRepositoryImplIntegrationTest() {
        super(Tag.class);
    }

    @Override
    protected long getDummyId(Tag dummy) {
        return dummy.getId();
    }

    @Override
    protected AbstractRepository<Tag> getRepository() {
        return new TagRepositoryImpl();
    }

    @Override
    protected Tag createDummyEntity() {
        final Tag tag = new Tag();
        tag.setName("dummy");
        return tag;
    }

    @Override
    protected void assertEquals(Tag expected, Tag actual) {
        TagAssert.assertEquals(TagConverter.convert(expected), TagConverter.convert(actual));
    }

    @Override
    protected Tag modifyDummy(Tag dummy) {
        dummy.setName("modified");
        return dummy;
    }

    @Override
    protected void copyDummyPK(Tag destination, Tag source) {
        destination.setId(source.getId());
    }

    @Test
    public void testFindByName() {
        Tag tag = createDummyEntity();
        manager.persist(tag);
        manager.flush();
        Tag otherTag = ((TagRepositoryImpl) repository).findByName(tag.getName());
        Assert.assertNotNull(otherTag);
        assertEquals(tag, otherTag);
    }

}
