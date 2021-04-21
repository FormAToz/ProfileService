package test_task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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

@ApiModel(description = "Класс cущности ошибки")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "errors")
public class Error {

    @ApiModelProperty(notes = "Уникальный id", example = "7", position = 0)
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(notes = "Сообщение", example = "Пользователь не найден", position = 1)
    @JsonProperty("msg")
    private String message;

    @ApiModelProperty(notes = "Время регистрации ошибки", example = "2021-04-21T11:53:50", position = 2)
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime created;

    public Error(String message, LocalDateTime created) {
        this.message = message;
        this.created = created;
    }
}
