package test_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test_task.model.Error;
import test_task.service.ErrorService;

@RequiredArgsConstructor
@RestController
@RequestMapping("error")
public class ErrorController {

    private final ErrorService errorService;

    @GetMapping("/last")
    public ResponseEntity<Error> getLastError() {
        return ResponseEntity.ok(errorService.getLastError());
    }
}
