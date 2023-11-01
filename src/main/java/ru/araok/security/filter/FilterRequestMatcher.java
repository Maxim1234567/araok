package ru.araok.security.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import ru.araok.service.PropertyProvider;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class FilterRequestMatcher implements RequestMatcher {
    private final PropertyProvider propertyProvider;

    @Override
    public boolean matches(HttpServletRequest request) {
        String pathUri = String.valueOf(request.getRequestURL());
        String method = request.getMethod();

        boolean result = matches(pathUri, method);

        System.out.println("pathUri: " + pathUri);
        System.out.println("method: " + method);
        System.out.println("matches: " + result);

        return result;
    }


    private boolean matches(String pathUri, String method) {
        return propertyProvider.getRequests()
                .stream()
                .map(r -> r.getMethod().equals(method) && Pattern.matches(r.getRegexpUrl(), pathUri))
                .filter(b -> b == Boolean.TRUE)
                .findFirst()
                .orElse(Boolean.FALSE);
    }
}
