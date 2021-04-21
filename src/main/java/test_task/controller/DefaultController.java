package test_task.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "Отображение swaggerAPI и выход из приложения")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class DefaultController implements ApplicationContextAware {

    private ApplicationContext context;

    @Value("${swagger-page}")
    private String swaggerPage;

    @GetMapping
    public String getSwaggerPage() {
        return "redirect:" + swaggerPage;
    }

    @ApiOperation(value = "Выход из приложения")
    @GetMapping("/exit")
    public String exit() {
        return "redirect:exit-success";
    }

    @GetMapping("/exit-success")
    public void exitSuccess(HttpServletResponse response) throws IOException {
        System.out.println("Выход из приложения выполнен");
        ((ConfigurableApplicationContext) context).close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
