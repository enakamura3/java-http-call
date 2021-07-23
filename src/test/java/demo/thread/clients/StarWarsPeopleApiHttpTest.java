package demo.thread.clients;

import demo.thread.models.PeopleJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StarWarsPeopleApiHttpTest {

    @Autowired
    StarWarsPeopleApiHttp swpClient;

    @Test
    void shouldCallStarWarsPeopleApiAndGetValidResponse() {
        PeopleJson ps = swpClient.getById("3");
        assertEquals("R2-D2",ps.getName());
    }

}
