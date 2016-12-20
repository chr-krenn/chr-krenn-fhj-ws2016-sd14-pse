package at.fhj.swd14.pse.department;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * This class serves as a department to text converter for the JSF frontend, so departments can be displayed
 * in lists and such
 * @author Patrick Kainz
 *
 */
@FacesConverter("DepartmentConverter")
public class DepartmentConverter implements Converter {

	//unfortunately we cannot inject things into converters...
	
	private static List<DepartmentDto> departments;
	
	/**
	 * We need to set the list of departments, as we cannot inject anything, thats what this method is for
	 * @param departmentList List of available departments
	 */
	public static void setDepartments(List<DepartmentDto> departmentList)
	{
		departments = departmentList;
	}
	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Long id = Long.parseLong(value);
		
		for(DepartmentDto dto : departments)
			if(dto.getId()!=null&&dto.getId().equals(id))
				return dto;
		return null;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value==null)
			return "";
		return ((DepartmentDto)value).getId().toString();
	}

}
