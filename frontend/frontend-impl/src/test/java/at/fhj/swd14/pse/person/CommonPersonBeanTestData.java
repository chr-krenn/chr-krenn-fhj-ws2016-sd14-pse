package at.fhj.swd14.pse.person;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import at.fhj.swd14.pse.department.DepartmentDto;

public class CommonPersonBeanTestData {

	private PersonDto person;
	private FacesContext context;
	private ExternalContext extContext;
	private List<StatusDto> stati;
	private List<DepartmentDto> deps;
	public PersonDto getPerson() {
		return person;
	}
	public void setPerson(PersonDto person) {
		this.person = person;
	}
	public FacesContext getContext() {
		return context;
	}
	public void setContext(FacesContext context) {
		this.context = context;
	}
	public ExternalContext getExtContext() {
		return extContext;
	}
	public void setExtContext(ExternalContext extContext) {
		this.extContext = extContext;
	}
	public List<StatusDto> getStati() {
		return stati;
	}
	public void setStati(List<StatusDto> stati) {
		this.stati = stati;
	}
	public List<DepartmentDto> getDeps() {
		return deps;
	}
	public void setDeps(List<DepartmentDto> deps) {
		this.deps = deps;
	}
	
	
	
}
