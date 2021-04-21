package test_task.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Класс запроса email`а пользователя")
@Data
public class EmailRequest {

    @ApiModelProperty(notes = "Email пользователя", example = "7.danilov@gmail.com")
    private String email;
}
