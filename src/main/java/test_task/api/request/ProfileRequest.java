package test_task.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Класс запроса регистрации профиля")
@Data
public class ProfileRequest {

    @ApiModelProperty(notes = "Имя пользователя", example = "Андрей", position = 0)
    private String name;

    @ApiModelProperty(notes = "Email пользователя", example = "7.danilov@gmail.com", position = 1)
    private String email;

    @ApiModelProperty(notes = "Возраст пользователя", example = "34", position = 2)
    private int age;
}
