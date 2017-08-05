package fr.plaisance.papes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PapesApplication implements CommandLineRunner {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PapesApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Recherche du meilleur prénom pour un garçon…");
		Executors
			.newScheduledThreadPool(1)
			.schedule(() -> System.out.println("Pierre"), 5, TimeUnit.SECONDS)
			.get();
	}
}
