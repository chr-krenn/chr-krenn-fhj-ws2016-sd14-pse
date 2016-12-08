package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.department.DepartmentDto;

/**
 * Converter for Department 
 * @author Patrick Kainz
 *
 */
public class DepartmentConverter {

	private DepartmentConverter() {

    }

    public static DepartmentDto convert(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto dto = new DepartmentDto(department.getId());
        dto.setName(department.getName());
        return dto;
    }

    public static Department convert(DepartmentDto department) {
        if (department == null) {
            return null;
        }
        Department dep =  new Department(department.getId());
        dep.setName(department.getName());
        return dep;
    }

    public static Collection<DepartmentDto> convertToDtoList(Collection<Department> departments) {
        if (departments == null) {
            return null;
        }
        return departments.stream().map(DepartmentConverter::convert).collect(Collectors.toList());
    }

    public static Collection<Department> convertToDoList(Collection<DepartmentDto> departments) {
        if (departments == null) {
            return null;
        }
        return departments.stream().map(DepartmentConverter::convert).collect(Collectors.toList());
    }
	
}
