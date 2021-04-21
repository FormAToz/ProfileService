package test_task.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel(description = "Класс ответа с id пользователя")
@Data
@AllArgsConstructor
public class UserIdResponse {

    @ApiModelProperty(notes = "id пользователя", example = "7")
    @JsonProperty("idUser")
    private long userId;
}
