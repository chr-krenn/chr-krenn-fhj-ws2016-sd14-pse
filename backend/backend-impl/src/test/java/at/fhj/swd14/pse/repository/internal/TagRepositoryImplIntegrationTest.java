package at.fhj.swd14.pse.repository.internal;


import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.tag.TagConverter;
import at.fhj.swd14.pse.tag.TagDtoTester;
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
        Tag tag = new Tag(1L, "dummy");
        return tag;
    }

    @Override
    protected void assertEquals(Tag expected, Tag actual) {
        TagDtoTester.assertEquals(TagConverter.convert(expected), TagConverter.convert(actual));
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
