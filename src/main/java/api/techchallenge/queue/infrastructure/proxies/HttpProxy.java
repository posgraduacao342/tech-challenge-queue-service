package api.techchallenge.queue.infrastructure.proxies;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class HttpProxy<T> {
    private final WebClient webClient;
    private final Class<T> type;

    public HttpProxy(Class<T> type, String baseUri) {
        this.webClient = WebClient.builder().baseUrl(baseUri).build();
        this.type = type;
    }

    public Mono<T> get(String path) {
        return this.webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(type);
    }
}
