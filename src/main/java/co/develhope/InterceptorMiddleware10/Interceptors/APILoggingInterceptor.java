package co.develhope.InterceptorMiddleware10.Interceptors;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class APILoggingInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("User-Agent: " + request.getHeader("User-Agent"));
        long startTime = System.currentTimeMillis();
        System.out.println("Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("Request URL::" + request.getRequestURL().toString() + "Sent to Handler :: Current Time=" + System.currentTimeMillis());
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        System.out.println("Request URL::" + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
        System.out.println("Request URL::" + request.getRequestURL().toString() + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }
}
