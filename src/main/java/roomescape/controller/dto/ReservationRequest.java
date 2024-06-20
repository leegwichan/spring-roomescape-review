package roomescape.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;

public record ReservationRequest(
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate date,
        String name,
        Long timeId) {

    public Reservation toDomain(ReservationTime reservationTime) {
        return new Reservation(name(), date(), reservationTime);
    }

}
