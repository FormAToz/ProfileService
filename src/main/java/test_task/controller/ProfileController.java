package test_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("profiles")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/last")
    public ResponseEntity<Profile> getLast() {
        return ResponseEntity.ok(profileService.getLastProfile());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getById(@PathVariable long id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @PostMapping("/set")
    public ResponseEntity<UserIdResponse> createProfile(@RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.ok(profileService.addProfile(profileRequest));
    }

    @PostMapping("/get")
    public ResponseEntity<Profile> getProfileByEmail(@RequestBody EmailRequest emailRequest) {
        return ResponseEntity.ok(profileService.getByEmail(emailRequest));
    }
}
