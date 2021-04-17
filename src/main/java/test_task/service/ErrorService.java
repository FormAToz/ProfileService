package test_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test_task.model.Error;
import test_task.repository.ErrorRepository;

@RequiredArgsConstructor
@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

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
