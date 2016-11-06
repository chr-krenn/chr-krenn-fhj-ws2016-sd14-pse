package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.news.News;

import javax.ejb.Local;
import javax.ejb.Singleton;

@Local
@Singleton
public class NewsRepository extends AbstractRepository<News> {
    public NewsRepository() {
        super(News.class);
    }
}
