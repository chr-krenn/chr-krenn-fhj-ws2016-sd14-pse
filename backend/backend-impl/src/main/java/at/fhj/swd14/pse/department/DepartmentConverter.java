package at.fhj.swd14.pse.department;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter for Department
 *
 * @author Patrick Kainz
 */
public final class DepartmentConverter {

    private DepartmentConverter() {

    }

    /**
     * Convertes the given Department object to a DepartmentDto Object
     *
     * @param department Department object to convert
     * @return DepartmentDto object
     */
    public static DepartmentDto convert(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto dto = new DepartmentDto(department.getId());
        dto.setName(department.getName());
        return dto;
    }

    /**
     * Converts the given DepartmentDto Object to a Department Object
     * No Database Operations are performed
     *
     * @param department DepartmentDto Object
     * @return Department Object
     */
    public static Department convert(DepartmentDto department) {
        if (department == null) {
            return null;
        }
        Department dep = new Department(department.getId());
        dep.setName(department.getName());
        return dep;
    }

    /**
     * Converts the given collection of Department Objects to a Collection of DepartmentDto Objects
     *
     * @param departments Collection of Department Objects
     * @return Collection (internal type: List) of DepartmentDto Objects
     */
    public static List<DepartmentDto> convertToDtoList(Collection<Department> departments) {
        if (departments == null) {
            return null;
        }
        return departments.stream().map(DepartmentConverter::convert).collect(Collectors.toList());
    }

    /**
     * Converts the given collection of DepartmentDto objects to a Collection of Department Objects
     * No Database Operations are performed
     *
     * @param departments Collection of DepartmentDto Objects
     * @return Collection (internal type: List) of Department Objects
     */
    public static List<Department> convertToDoList(Collection<DepartmentDto> departments) {
        if (departments == null) {
            return null;
        }
        return departments.stream().map(DepartmentConverter::convert).collect(Collectors.toList());
    }

}
