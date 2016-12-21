package at.fhj.swd14.pse.person;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter between Status and StatusDto
 *
 * @author Patrick Kainz
 */
public final class StatusConverter {

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

    public static List<StatusDto> convertToDtoList(Collection<Status> status) {
        if (status == null) {
            return null;
        }
        return status.stream().map(StatusConverter::convert).collect(Collectors.toList());
    }

    public static List<Status> convertToDoList(Collection<StatusDto> status) {
        if (status == null) {
            return null;
        }
        return status.stream().map(StatusConverter::convert).collect(Collectors.toList());
    }
}
