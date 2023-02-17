package backend.challenge.modules.task.infra.http.handlers.exception;

import lombok.Getter;

public class HttpExceptionResult {

    @Getter
    private String error;

    public HttpExceptionResult(String message) {
        this.error = message;
    }

}
