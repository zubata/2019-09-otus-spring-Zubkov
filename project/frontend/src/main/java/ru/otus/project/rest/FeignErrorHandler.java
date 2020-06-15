package ru.otus.project.rest;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;
import ru.otus.project.exception.*;

@Component
public class FeignErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        MyHttpExceptionBody httpException = MyHttpExceptionBody.getHttpException(response);
        switch (response.status()){
            case 401:
                return new UnauthorizedException();
            case 403:
                return new ForbiddenException();
            case 404:
                return new NotFoundException(response.request().url());
            case 500:
                return new ServerErrorException(httpException.getMessage());
            default:
                return FeignException.errorStatus(methodKey, response);
        }
    }
}
