package test_task.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@ApiModel(description = "Класс cущности профиля")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {

    @ApiModelProperty(notes = "Уникальный id", example = "7", position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(notes = "Имя пользователя", example = "Андрей", position = 1)
    private String name;

    @ApiModelProperty(notes = "Email пользователя", example = "7.danilov@gmail.com", position = 2)
    private String email;

    @ApiModelProperty(notes = "Возраст пользователя", example = "34", position = 3)
    private int age;

    @ApiModelProperty(notes = "Время регистрации", example = "2021-04-21T11:53:50", position = 4)
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime created;
}
