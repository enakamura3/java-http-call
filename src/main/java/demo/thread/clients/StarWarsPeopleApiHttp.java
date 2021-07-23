package demo.thread.clients;

import demo.thread.models.PeopleJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "star-wars-people-api", url = "${star-wars-people-api.url}")
public interface StarWarsPeopleApiHttp {

    @GetMapping("/people/{id}")
    PeopleJson getById(@PathVariable String id);
}
