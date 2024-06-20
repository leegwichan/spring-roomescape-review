package roomescape.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import roomescape.domain.ReservationTime;

public record TimeRequest(
        @JsonFormat(pattern = "HH:mm") LocalTime startAt) {

    public ReservationTime toDomain() {
        return new ReservationTime(startAt());
    }

}
