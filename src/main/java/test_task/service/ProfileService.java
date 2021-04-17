package test_task.service;

import org.springframework.stereotype.Service;
import test_task.api.request.EmailRequest;
import test_task.api.request.ProfileRequest;
import test_task.api.response.UserIdResponse;
import test_task.model.Profile;

import java.util.List;

@Service
public class ProfileService {
    public UserIdResponse addProfile(ProfileRequest profileRequest) {
        return null;
    }

    public List<Profile> getAllProfiles() {
        return null;
    }

    public Profile getLastProfile() {
        return null;
    }

    public Profile getById(long id) {
        return null;
    }

    public Profile getByEmail(EmailRequest emailRequest) {
        return null;
    }
}
