package test_task.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserIdResponse {
    @JsonProperty("idUser")
    private long userId;
}
