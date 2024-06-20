package roomescape.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import roomescape.domain.ReservationTime;

public record TimeResponse(
        Long id,
        @JsonFormat(pattern = "HH:mm") LocalTime startAt) {

    public static TimeResponse from(ReservationTime time) {
        return new TimeResponse(time.id(), time.startAt());
    }

}
