package at.fhj.swd14.pse.person.tools;

import java.util.Comparator;

import at.fhj.swd14.pse.person.PersonDto;

public class PersonComparator implements Comparator<PersonDto> {

	@Override
	public int compare(PersonDto o1, PersonDto o2) {
		return o1.getLastname().compareTo(o2.getLastname());
	}

}
