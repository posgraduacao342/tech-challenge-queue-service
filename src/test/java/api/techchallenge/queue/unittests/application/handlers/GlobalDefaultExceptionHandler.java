package api.techchallenge.queue.unittests.application.handlers;

import api.techchallenge.queue.application.handlers.GlobalDefaultExceptionHandler;
import api.techchallenge.queue.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalDefaultExceptionHandlerTest {

    private final GlobalDefaultExceptionHandler exceptionHandler = new GlobalDefaultExceptionHandler();

    @Test
    void handleNotFoundException() {
        // Arrange
        NotFoundException exception = new NotFoundException("Recurso não encontrado");

        // Act
        ResponseEntity<?> responseEntity = exceptionHandler.handleNotFoundException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}