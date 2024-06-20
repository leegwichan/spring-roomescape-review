package roomescape.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.controller.dto.ReservationRequest;
import roomescape.controller.dto.ReservationResponse;
import roomescape.controller.dto.TimeResponse;
import roomescape.utils.RestControllerTest;

class ReservationControllerTest extends RestControllerTest {

    private static final int COUNT_OF_RESERVATIONS = 5;
    private static final int CAN_DELETED_ID = 5;


    @DisplayName("전체 예약 조회")
    @Test
    void get_reservations() {
        RestAssured.given().log().all()
                .when().get("/reservations")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(COUNT_OF_RESERVATIONS));
    }

    @DisplayName("예약 생성")
    @Test
    void post_reservation() {
        long newId = COUNT_OF_RESERVATIONS + 1;
        ReservationRequest request = new ReservationRequest(LocalDate.of(2023, 8, 10), "브리", 1L);
        ReservationResponse expectedResponse = new ReservationResponse(
                newId, "브리", LocalDate.of(2023, 8, 10), new TimeResponse(1L, LocalTime.of(10, 00)));

        ReservationResponse actualResponse = RestAssured.given().log().all()
                .contentType(ContentType.JSON).body(request)
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201)
                .header("Location", "/reservations/" + newId)
                .extract().as(ReservationResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("예약 삭제")
    @Test
    void delete_reservation() {
        RestAssured.given().log().all()
                .when().delete("/reservations/" + CAN_DELETED_ID)
                .then().log().all()
                .statusCode(204);
    }

    @DisplayName("예약 삭제 : 해당 예약이 없을 경우")
    @Test
    void delete_reservation_whenNotExist() {
        long notExistId = COUNT_OF_RESERVATIONS + 1;
        RestAssured.given().log().all()
                .when().delete("/reservations/" + notExistId)
                .then().log().all()
                .statusCode(400)
                .body("message", is("해당 예약을 찾을 수 없습니다."));
    }

}
