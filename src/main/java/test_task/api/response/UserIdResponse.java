package test_task.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserIdResponse {
    @JsonProperty("idUser")
    private int userId;
}
