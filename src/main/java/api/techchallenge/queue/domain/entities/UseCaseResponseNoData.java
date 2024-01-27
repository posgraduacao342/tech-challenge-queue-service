package api.techchallenge.queue.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UseCaseResponseNoData {
    private HttpStatus status = HttpStatus.OK;
    private String errorMessage;
}
