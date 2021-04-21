package test_task.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test_task.model.Error;
import test_task.service.ErrorService;

@Api(tags = "Обработка ошибок")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("error")
public class ErrorController {

    private final ErrorService errorService;

    @ApiOperation(value = "Сообщение о последней ошибке")
    @GetMapping("/last")
    public ResponseEntity<Error> getLastError() {
        return ResponseEntity.ok(errorService.getLastError());
    }
}
