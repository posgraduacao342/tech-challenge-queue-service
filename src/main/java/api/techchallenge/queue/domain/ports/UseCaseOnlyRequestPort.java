package api.techchallenge.queue.domain.ports;

import api.techchallenge.queue.domain.entities.UseCaseResponseNoData;

public interface UseCaseOnlyRequestPort<T> {
    UseCaseResponseNoData execute(T request);
}