package com.interface21.webmvc.servlet.mvc.tobe;

import static org.assertj.core.api.Assertions.assertThat;

import com.interface21.webmvc.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import samples.TestController;

class HandlerExecutionTest {

    @DisplayName("인자로 넘겨준 메소드를 실행한다")
    @Test
    void handle() throws Exception {
        Method method = TestController.class.getDeclaredMethod(
                "test",
                HttpServletRequest.class,
                HttpServletResponse.class
        );
        HandlerExecution handlerExecution = new HandlerExecution(method);
        ModelAndView modelAndView = handlerExecution.handle(null, null);

        assertThat(modelAndView.getObject("test")).isEqualTo("test");
    }
}
