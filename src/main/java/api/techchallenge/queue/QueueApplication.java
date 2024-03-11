package api.techchallenge.queue;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class QueueApplication {
	public static void main(String[] args) {
		SpringApplication.run(QueueApplication.class, args);
	}
}
