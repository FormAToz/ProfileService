package test_task.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Класс ответа с описанием ошибки")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @ApiModelProperty(notes = "Описание ошибки", example = "Пользователь не найден")
    @JsonProperty("msg")
    private String message;
}
