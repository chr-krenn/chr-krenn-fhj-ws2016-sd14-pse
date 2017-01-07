package at.fhj.swd14.pse;

import at.fhj.swd14.pse.department.DepartmentConverter;
import at.fhj.swd14.pse.message.MessageConverter;
import at.fhj.swd14.pse.person.*;
import at.fhj.swd14.pse.tag.TagConverter;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConverterCoverageTest {

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void initantiate(Class clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Constructor con = clazz.getDeclaredConstructor();
        con.setAccessible(true);
        con.newInstance();
    }

    @Test
    public void increaseCoverage() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        initantiate(StatusConverter.class);
        initantiate(DepartmentConverter.class);
        initantiate(HobbyConverter.class);
        initantiate(KnowledgeConverter.class);
        initantiate(MailaddressConverter.class);
        initantiate(PersonConverter.class);
        initantiate(PersonImageConverter.class);
        initantiate(PhonenumberConverter.class);
        initantiate(MessageConverter.class);
        initantiate(TagConverter.class);
    }
}
