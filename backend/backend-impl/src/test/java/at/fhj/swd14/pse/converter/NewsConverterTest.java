package at.fhj.swd14.pse.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

import at.fhj.swd14.pse.news.News;
import at.fhj.swd14.pse.news.NewsDto;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.Status;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

public class NewsConverterTest {
    private static final Random RANDOM = new Random();

    @Test
    public void shouldConvertDtoToEntityWithoutConvertingCreationAndModificationDate() {
        final NewsDto dto = buildDto();
        final News entity = NewsConverter.convert(dto);

        assertDtoConversion(dto, entity);
    }

    @Test
    public void shouldConvertEntityToDto() {
        final News entity = buildEntity();
        final NewsDto dto = NewsConverter.convert(entity);

        assertEntityConversion(entity, dto);
    }

    @Test
    public void shouldConvertNullEntity() {
        assertNull(NewsConverter.convert((News) null));
    }

    @Test
    public void shouldConvertNullDto() {
        assertNull(NewsConverter.convert((NewsDto) null));
    }

    @Test
    public void shouldConvertDtoCollection() {
        final Collection<NewsDto> dtoCollection = Arrays.asList(buildDto(), buildDto());
        final Collection<News> entityCollection = NewsConverter.convertToDoList(dtoCollection);

        assertEquals(dtoCollection.size(), entityCollection.size());
        final Iterator<NewsDto> dtoIterator = dtoCollection.iterator();
        final Iterator<News> entityIterator = entityCollection.iterator();

        while (dtoIterator.hasNext()) {
            assertDtoConversion(dtoIterator.next(), entityIterator.next());
        }
    }

    @Test
    public void shouldConvertEntityCollection() {
        final Collection<News> entityCollection = Arrays.asList(buildEntity(), buildEntity());
        final Collection<NewsDto> dtoCollection = NewsConverter.convertToDtoList(entityCollection);

        assertEquals(dtoCollection.size(), entityCollection.size());
        final Iterator<NewsDto> dtoIterator = dtoCollection.iterator();
        final Iterator<News> entityIterator = entityCollection.iterator();

        while (dtoIterator.hasNext()) {
            assertEntityConversion(entityIterator.next(), dtoIterator.next());
        }
    }

    @Test
    public void shouldConvertNullEntityList() {
        assertNull(NewsConverter.convertToDtoList(null));
    }

    @Test
    public void shouldConvertNullDtoList() {
        assertNull(NewsConverter.convertToDoList(null));
    }

    private News buildEntity() {
        final News news = new News();
        final User user = new User();
        final Person author = new Person(RANDOM.nextLong(), user);
        author.setStatus(new Status("status"));
        news.setAuthor(author);
        news.setId(RANDOM.nextLong());
        news.setTitle("title");
        news.setMessage("message");
        news.setActivation(Instant.now());
        news.setTermination(Instant.now());

        return news;
    }

    private NewsDto buildDto() {
        final NewsDto dto = new NewsDto();
        dto.setMessage("message");
        dto.setModified(Instant.now());
        dto.setCreated(Instant.now());
        final PersonDto author = new PersonDto(RANDOM.nextLong());
        author.setUser(new UserDto());
        dto.setAuthor(author);
        dto.setTitle("title");
        dto.setId(RANDOM.nextLong());
        dto.setActivation(Instant.now());
        dto.setTermination(Instant.now());
        return dto;
    }

    private void assertDtoConversion(NewsDto dto, News entity) {
        assertEquals(dto.getMessage(), entity.getMessage());
        assertEquals(dto.getTitle(), entity.getTitle());
        assertNotNull(entity.getAuthor());
        assertEquals(dto.getAuthor().getId(), entity.getAuthor().getId());
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getActivation(), entity.getActivation());
        assertEquals(dto.getTermination(), entity.getTermination());
        assertNull(entity.getCreated());
        assertNull(entity.getModified());
    }

    private void assertEntityConversion(News entity, NewsDto dto) {
        assertEquals(entity.getMessage(), dto.getMessage());
        assertEquals(entity.getTitle(), dto.getTitle());
        assertNotNull(dto.getAuthor());
        assertEquals(entity.getAuthor().getId(), dto.getAuthor().getId());
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getActivation(), dto.getActivation());
        assertEquals(entity.getTermination(), dto.getTermination());
        assertEquals(entity.getCreated(), dto.getCreated());
        assertEquals(entity.getModified(), dto.getModified());
    }

}