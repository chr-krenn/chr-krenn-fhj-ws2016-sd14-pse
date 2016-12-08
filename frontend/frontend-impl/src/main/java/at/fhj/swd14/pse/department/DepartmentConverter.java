package at.fhj.swd14.pse.department;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("DepartmentConverter")
public class DepartmentConverter implements Converter {

	//unfortunately we cannot inject things into converts...
	
	private static List<DepartmentDto> departments;
	
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
