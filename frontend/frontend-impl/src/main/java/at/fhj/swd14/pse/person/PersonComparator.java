package at.fhj.swd14.pse.person;

import java.util.Comparator;

public class PersonComparator implements Comparator<PersonDto> {

	@Override
	public int compare(PersonDto o1, PersonDto o2) {
		return o1.getLastname().compareTo(o2.getLastname());
	}

}
