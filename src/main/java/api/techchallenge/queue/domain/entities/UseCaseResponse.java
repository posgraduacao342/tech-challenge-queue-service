package api.techchallenge.queue.domain.entities;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UseCaseResponse<T>  extends UseCaseResponseNoData{
    private T data;

    public UseCaseResponse(T data){
        this.data = data;
    }

    public UseCaseResponse(HttpStatus status, String errorMessage){
        super.setStatus(status);
        super.setErrorMessage(errorMessage);
    }
}
