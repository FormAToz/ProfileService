package test_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test_task.api.response.ErrorResponse;
import test_task.model.Error;
import test_task.repository.ErrorRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

    /**
     * Метод добавления новой ошибки
     * @param message сообщение об ошибке
     * @return объект ErrorResponse
     */
    public ErrorResponse addError(String message) {
        errorRepository.save(new Error(message, LocalDateTime.now()));
        return new ErrorResponse(message);
    }

    /**
     * Метод получения последней ошибки
     * @return объект класса Error
     * @throws IllegalStateException в случае, если ни одной ошибки не найдено в базе данных
     */
    public Error getLastError() {
        return errorRepository.findFirstByOrderByCreatedDesc()
                .orElseThrow(() -> new IllegalStateException("Последняя ошибка не найдена"));
    }
}
