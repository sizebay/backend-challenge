package backend.challenge.modules.task.infra.http.handlers.exception;

import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.ExceptionHandler;
import kikaha.urouting.api.Response;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
@Singleton
public class HttpJsonExceptionHandler implements ExceptionHandler<Throwable> {

    @Override
    public Response handle(final Throwable exception) {
        return DefaultResponse.badRequest()
                .entity(new HttpExceptionResult(exception.getMessage()));
    }

}
