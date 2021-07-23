package demo.thread.models;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleJson {
    String name;
    String height;
    String mass;
    String homeworld;
    List<String> films;
    String created;
    String edited;
    String url;
}
