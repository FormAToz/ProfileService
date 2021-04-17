package test_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test_task.api.request.EmailRequest;
import test_task.api.request.ProfileRequest;
import test_task.api.response.UserIdResponse;
import test_task.model.Profile;
import test_task.repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final ProfileRepository profileRepository;

    /**
     * Метод добавления нового профиля
     * @param profileRequest объект запроса при добавлении профиля
     * @return UserIdResponse объект ответа, содержащий id добавленного профиля
     */
    public UserIdResponse addProfile(ProfileRequest profileRequest) {
        checkEmailForCorrect(profileRequest.getEmail());
        checkExistingProfileByEmail(profileRequest.getEmail());

        Profile savedProfile = createAndSaveFromProfileRequest(profileRequest);

        return new UserIdResponse(savedProfile.getId());
    }

    /**
     * Метод проверки e-mail на корректность
     * @param email строка с e-mail
     * @throws IllegalArgumentException в случае, если введен некорректный e-mail
     */
    private void checkEmailForCorrect(String email) {
        if (!email.matches(EMAIL_PATTERN))
            throw new IllegalArgumentException("Введите корректный e-mail");
    }

    /**
     * Метод проверки существования профиля по e-mail в базе данных
     * @param email строка с e-mail
     * @throws UnsupportedOperationException в случае, если профиль с таким e-mail уже существует в базе данных
     */
    private void checkExistingProfileByEmail(String email) {
        if (profileRepository.existsByEmailIgnoreCase(email))
            throw new UnsupportedOperationException(String.format("Профиль с e-mail = %s уже существует", email));
    }

    /**
     *
     * @param profileRequest объект запроса при добавлении профиля
     * @return Profile объект сохраненного профиля в базу данных
     */
    private Profile createAndSaveFromProfileRequest(ProfileRequest profileRequest) {
        return profileRepository.save(
                Profile
                        .builder()
                        .name(profileRequest.getName())
                        .email(profileRequest.getEmail())
                        .age(profileRequest.getAge())
                        .created(LocalDateTime.now())
                        .build());
    }

    /**
     * Метод возвращает все сохраненные профили из базы данных
     * @return список всех сохраненных профилей
     */
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    /**
     * Метод получения последнего сохраненного профиля из базы данных
     * @return объект Profile
     * @throws IllegalStateException в случае, если последний сохраненный профиль не найден
     */
    public Profile getLastProfile() {
        return profileRepository.findFirstByOrderByCreatedDesc()
                .orElseThrow(() -> new IllegalStateException("Последний сохраненный профиль не найден"));
    }

    /**
     * Метод получения профиля по id
     * @param id идентификатор профиля
     * @return объект Profile
     * @throws IllegalStateException в случае, если последний сохраненный профиль не найден
     */
    public Profile getById(long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Профиль с id = %d не найден", id)));
    }

    /**
     * Метод получения профиля по e-mail из базы данных
     * @param emailRequest объект запроса при поиске профиля по e-mail
     * @return объект Profile
     * @throws IllegalStateException в случае, если профиль по e-mail не найден
     */
    public Profile getByEmail(EmailRequest emailRequest) {
        return profileRepository.findByEmailIgnoreCase(emailRequest.getEmail())
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Профиль с e-mail = %s не найден", emailRequest.getEmail())));
    }
}
