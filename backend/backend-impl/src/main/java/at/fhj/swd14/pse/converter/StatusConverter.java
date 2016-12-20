package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.person.Status;
import at.fhj.swd14.pse.person.StatusDto;

/**
 * Converter between Status and StatusDto
 * @author Patrick Kainz
 *
 */
public class StatusConverter {

	private StatusConverter() {

    }

    public static StatusDto convert(Status status) {
        if (status == null) {
            return null;
        }
        return new StatusDto(status.getName());
    }

    public static Status convert(StatusDto status) {
        if (status == null) {
            return null;
        }
        return new Status(status.getName());
    }

    public static Collection<StatusDto> convertToDtoList(Collection<Status> status) {
        if (status == null) {
            return null;
        }
        return status.stream().map(StatusConverter::convert).collect(Collectors.toList());
    }

    public static Collection<Status> convertToDoList(Collection<StatusDto> status) {
        if (status == null) {
            return null;
        }
        return status.stream().map(StatusConverter::convert).collect(Collectors.toList());
    }
}
