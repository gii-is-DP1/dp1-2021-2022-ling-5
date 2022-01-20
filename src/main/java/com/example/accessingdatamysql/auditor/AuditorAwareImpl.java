package com.example.accessingdatamysql.auditor;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        HttpSession s = (HttpSession) RequestContextHolder
                .currentRequestAttributes()
                .resolveReference(RequestAttributes.REFERENCE_SESSION);
        System.out.println(((String) s.getAttribute("nickname")));
        return Optional.of((String) s.getAttribute("nickname"));
    }

}
