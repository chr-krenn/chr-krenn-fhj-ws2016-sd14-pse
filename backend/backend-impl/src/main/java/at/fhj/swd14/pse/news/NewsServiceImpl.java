package at.fhj.swd14.pse.news;

import javax.ejb.Stateless;
import java.time.Instant;
import java.util.List;

@Stateless
public class NewsServiceImpl implements NewsService {
    @Override
    public long save(NewsDto news) {
        return 0;
    }

    @Override
    public NewsDto find(long id) {
        return null;
    }

    @Override
    public List<NewsDto> findAll() {
        return null;
    }

    @Override
    public List<NewsDto> findAllSince(Instant instant) {
        return null;
    }

    @Override
    public List<NewsDto> findByAuthorId(long author) {
        return null;
    }
}
