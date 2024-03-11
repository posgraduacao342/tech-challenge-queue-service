package api.techchallenge.queue.domain.ports.out;

public interface EnviarEmailQueueOUTPort {
    void publish(String destinatario, String assunto, String texto);
}