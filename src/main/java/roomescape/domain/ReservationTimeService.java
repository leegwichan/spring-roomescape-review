package roomescape.domain;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.controller.dto.TimeRequest;
import roomescape.controller.dto.TimeResponse;

@Service
public class ReservationTimeService {

    private final TimeRepository timeRepository;

    public ReservationTimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<TimeResponse> getAllReservationTimes() {
        return timeRepository.findAll().stream()
                .map(TimeResponse::from)
                .toList();
    }

    public TimeResponse createReservationTime(TimeRequest request) {
        ReservationTime time = request.toDomain();
        ReservationTime createdTime = timeRepository.create(time);
        return TimeResponse.from(createdTime);
    }

    public void deleteReservationTime(Long id) {
        ReservationTime foundReservationTime = timeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("해당 예약 시간을 찾을 수 없습니다."));
        timeRepository.deleteById(foundReservationTime.id());
    }

}
