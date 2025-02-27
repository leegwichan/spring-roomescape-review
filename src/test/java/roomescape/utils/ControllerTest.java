package roomescape.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

}
