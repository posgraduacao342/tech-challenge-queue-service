package api.techchallenge.queue.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class UseCaseResponseNoData {
    private HttpStatus status = HttpStatus.OK;
    private String errorMessage;
}
