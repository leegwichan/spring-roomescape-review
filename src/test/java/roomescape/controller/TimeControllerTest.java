package roomescape.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.controller.dto.TimeRequest;
import roomescape.controller.dto.TimeResponse;
import roomescape.utils.RestControllerTest;

class TimeControllerTest extends RestControllerTest {

    private static final int COUNT_OF_TIME = 3;
    private static final int CAN_DELETE_ID = 3;

    @DisplayName("예약 시간 전체 조회")
    @Test
    void get_reservationTimes() {
        RestAssured.given().log().all()
                .when().get("/times")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(COUNT_OF_TIME));
    }

    @DisplayName("예약 시간 생성")
    @Test
    void post_reservationTime() {
        long newId = COUNT_OF_TIME + 1;
        TimeRequest request = new TimeRequest(LocalTime.of(16, 00));
        TimeResponse expectedResponse = new TimeResponse(newId, LocalTime.of(16, 00));

        TimeResponse actualResponse = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/times")
                .then().log().all()
                .statusCode(201)
                .header("Location", "/times/" + newId)
                .extract().as(TimeResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("예약 시간 삭제")
    @Test
    void delete_reservationTime() {
        RestAssured.given().log().all()
                .when().delete("/times/" + CAN_DELETE_ID)
                .then().log().all()
                .statusCode(204);
    }

    @DisplayName("예약 시간 삭제 : 해당 예약시 없을 경우")
    @Test
    void delete_reservationTime_whenNotExist() {
        long notExistId = COUNT_OF_TIME + 1;
        RestAssured.given().log().all()
                .when().delete("/times/" + notExistId)
                .then().log().all()
                .statusCode(400)
                .body("message", is("해당 예약 시간을 찾을 수 없습니다."));
    }

}
