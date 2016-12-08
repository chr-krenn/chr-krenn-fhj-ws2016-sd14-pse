package at.fhj.swd14.pse.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class ConverterCoverageTest {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initantiate(Class clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		Constructor con = clazz.getDeclaredConstructor();
		con.setAccessible(true);
		con.newInstance();
	}
	
	@Test
	public void increateConverage() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		initantiate(StatusConverter.class);
		initantiate(DepartmentConverter.class);
		initantiate(HobbyConverter.class);
		initantiate(KnowledgeConverter.class);
		initantiate(MailaddressConverter.class);
		initantiate(PersonConverter.class);
		initantiate(PersonImageConverter.class);
		initantiate(PhonenumberConverter.class);
	}
}
