package roomescape.domain;

import java.util.List;
import java.util.Optional;

public interface TimeRepository {

    Optional<ReservationTime> findById(Long id);

    List<ReservationTime> findAll();

    ReservationTime create(ReservationTime reservationTime);

    void deleteById(Long id);

}
