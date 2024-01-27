package api.techchallenge.queue.domain.ports;

import api.techchallenge.queue.domain.entities.UseCaseResponse;

public interface UseCasePort<T1, T2> {
    UseCaseResponse<T1> execute(T2 request);
}