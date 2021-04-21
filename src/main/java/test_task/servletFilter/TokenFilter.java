package test_task.servletFilter;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс сервлетного фильтра.
 */
@Component
public class TokenFilter implements Filter {

    @Value("${token}")
    private String token;

    /**
     * Метод определяет по какому пути запроса следует проверять правильность ввода токена
     */
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();

        if (pathNeedToSecure(path)) { // если путь отличается от /exit* или */swagger-ui.html
            if (!tokenIsValid(request)) {   // если токен введен неправильно
                sendError(response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Метод проверки путей запросов.
     * @param path строковое представление пути запроса
     * @return true, если путь запроса начинается с "/exit" или "/profiles". false, если не содержит эти префиксы
     */
    private boolean pathNeedToSecure(String path) {
        return path.startsWith("/profiles") | path.startsWith("/error");
    }

    /**
     * Метод проверки ввода и валидности токена
     * @param request объект запроса HttpServletRequest
     * @return true, если токен введен и верный. false, если токен не введен или неверный
     */
    private boolean tokenIsValid(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");

        if (StringUtils.hasText(authToken) && authToken.startsWith("Bearer ")) {
            return authToken.substring(7).equals(token);
        }
        return false;
    }

    /**
     * Метод установки кода и отправки ошибки
     */
    private HttpServletResponse sendError(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return response;
    }
}
