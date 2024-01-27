package api.techchallenge.queue.application.mappers;

import api.techchallenge.queue.domain.entities.UseCaseResponse;
import org.springframework.http.ResponseEntity;

public class ResponseEntityMapper<T> {
    public ResponseEntity<UseCaseResponse<T>> toResponseEntity(UseCaseResponse<T> response){
        return new ResponseEntity<UseCaseResponse<T>>(response, response.getStatus());
    }
}
