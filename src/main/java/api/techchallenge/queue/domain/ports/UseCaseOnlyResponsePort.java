package api.techchallenge.queue.domain.ports;

import api.techchallenge.queue.domain.entities.UseCaseResponse;

public interface UseCaseOnlyResponsePort<T> {
    UseCaseResponse<T> execute();
}