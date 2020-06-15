package ru.otus.project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.feign.FeignForAuth;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final FeignForAuth feignForAuth;

    @Override
    public void revokeToken() {
        feignForAuth.revokeToken();
    }
}
