package at.fhj.swd14.pse.news;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Stateless
public class NewsServiceImpl implements NewsService {

    @EJB
    private NewsRepository newsRepository;

    @Override
    public long save(NewsDto news) {
        final News converted = NewsConverter.convert(Objects.requireNonNull(news));
        newsRepository.save(converted);
        return converted.getId();
    }

    @Override
    public long update(NewsDto news) {
        final News converted = NewsConverter.convert(Objects.requireNonNull(news));
        newsRepository.update(converted);
        return converted.getId();
    }

    @Override
    public NewsDto find(long id) {
        return NewsConverter.convert(newsRepository.find(id));
    }

    @Override
    public Collection<NewsDto> findAll() {
        return NewsConverter.convertToDtoList(newsRepository.findAll());
    }

    @Override
    public Collection<NewsDto> findAllOnline() {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("onlineDate", ZonedDateTime.now().toInstant());
        return NewsConverter.convertToDtoList(newsRepository.executeNamedQuery("News.findAllOnline", parameters));

    }

    @Override
    public Collection<NewsDto> findAllSince(Instant instant) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("dateSince", Objects.requireNonNull(instant));
        return NewsConverter.convertToDtoList(newsRepository.executeNamedQuery("News.findSince", parameters));
    }

    @Override
    public Collection<NewsDto> findByAuthorId(long author) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("authorId", author);
        return NewsConverter.convertToDtoList(newsRepository.executeNamedQuery("News.findByAuthorId", parameters));
    }
}
