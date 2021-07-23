package demo.thread.services;

import demo.thread.clients.StarWarsPeopleApiHttp;
import demo.thread.models.PeopleJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StarWarsTest {

    @Autowired
    StarWars swService;

    @Autowired
    StarWarsPeopleApiHttp swpClient;

    @Test
    public void shouldCallStarWarsPeopleApiClient() {
        PeopleJson pj = swService.findById("2");
        assertEquals("C-3PO", pj.getName());
    }

    @Test
    public void shouldCallStarWarsPeopleApiClientAndHandleANotFoundPeople() {
        PeopleJson pj = swService.findById("17");
        assertEquals("NOT_FOUND", pj.getName());
    }

    @Test
    public void shouldGetNPeopleFromStarWarsPeopleApi() {
        int n = 30;
        var ids = Stream.iterate(1, i -> i+1).limit(n);
        var idsStr = ids.collect(Collectors.toSet()).stream().map(x -> Integer.toString(x)).collect(Collectors.toSet());
        var pjs = swService.findByIds(idsStr);
        assertEquals(n, pjs.size());
    }

    @Test
    public void shouldGetNPeopleFromStarWarsPeopleApiUsingThreadsFromCommonPool() {
        int n = 30;
        var ids = Stream.iterate(1, i -> i+1).limit(n);
        var idsStr = ids.collect(Collectors.toSet()).stream().map(x -> Integer.toString(x)).collect(Collectors.toSet());
        var pjs = swService.findByIdsThreadCommonPool(idsStr);
        assertEquals(n, pjs.size());
    }

    @Test
    public void shouldGetNPeopleFromStarWarsPeopleApiUsingCustomThreadPool() {
        int n = 30;
        var ids = Stream.iterate(1, i -> i+1).limit(n);
        var idsStr = ids.collect(Collectors.toSet()).stream().map(x -> Integer.toString(x)).collect(Collectors.toSet());
        var pjs = swService.findByIdsCustomThreadPool(idsStr);
        assertEquals(n, pjs.size());
    }
}
