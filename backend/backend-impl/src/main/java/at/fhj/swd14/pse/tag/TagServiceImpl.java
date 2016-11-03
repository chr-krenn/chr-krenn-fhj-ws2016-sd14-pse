package at.fhj.swd14.pse.tag;

import javax.ejb.Stateless;

//Todo: implement methods
@Stateless
public class TagServiceImpl implements TagService{



    @Override
    public long save(TagDto tag) {
        return 0;
    }

    @Override
    public TagDto find(long id) {
        return null;
    }

    @Override
    public TagDto findByName(String name) {
        return null;
    }
}
