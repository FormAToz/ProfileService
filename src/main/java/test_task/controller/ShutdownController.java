package test_task.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ShutdownController implements ApplicationContextAware {

    private ApplicationContext context;

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
