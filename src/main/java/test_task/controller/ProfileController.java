package test_task.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test_task.api.request.EmailRequest;
import test_task.api.request.ProfileRequest;
import test_task.api.response.UserIdResponse;
import test_task.model.Profile;
import test_task.service.ProfileService;

import java.util.List;

@Api(tags = "Работа с профилем")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("profiles")
public class ProfileController {

    private final ProfileService profileService;

    @ApiOperation(value = "Получить все созданные профили")
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @ApiOperation(value = "Получить последний созданный профиль")
    @GetMapping("/last")
    public ResponseEntity<Profile> getLast() {
        return ResponseEntity.ok(profileService.getLastProfile());
    }

    @ApiOperation(value = "Найти профиль по id")
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getById(@PathVariable long id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @ApiOperation(value = "Создать новый профиль")
    @PostMapping("/set")
    public ResponseEntity<UserIdResponse> createProfile(@RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.ok(profileService.addProfile(profileRequest));
    }

    @ApiOperation(value = "Найти профиль по email")
    @PostMapping("/get")
    public ResponseEntity<Profile> getProfileByEmail(@RequestBody EmailRequest emailRequest) {
        return ResponseEntity.ok(profileService.getByEmail(emailRequest));
    }
}
