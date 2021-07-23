package demo.thread.services;

import demo.thread.clients.StarWarsPeopleApiHttp;
import demo.thread.models.PeopleJson;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StarWars {

    private final Executor threadPool;
    private final StarWarsPeopleApiHttp swpClient;

    public PeopleJson findById(String id) {
        try {
            return swpClient.getById(id);
        } catch (FeignException e) {
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                return PeopleJson.builder().name("NOT_FOUND").build();
            }
            return new PeopleJson();
        }
    }

    public List<PeopleJson> findByIds(Set<String> ids) {
        return ids.stream().map(id -> findById(id)).collect(Collectors.toList());
    }

    public List<PeopleJson> findByIdsThreadCommonPool(Set<String> ids) {
        var lcf = ids.stream().map(id -> CompletableFuture.supplyAsync(() -> findById(id))).collect(Collectors.toList());
        List<PeopleJson> lpj = new ArrayList<>();
        for (var cf : lcf) {
            var pj = cf.join();
            lpj.add(pj);
        }
        return lpj;
    }

    public List<PeopleJson> findByIdsCustomThreadPool(Set<String> ids) {
        var lcf = ids.stream().map(id -> CompletableFuture.supplyAsync(() -> findById(id), threadPool)).collect(Collectors.toList());
        List<PeopleJson> lpj = new ArrayList<>();
        for (var cf : lcf) {
            var pj = cf.join();
            lpj.add(pj);
        }
        return lpj;
    }

}
