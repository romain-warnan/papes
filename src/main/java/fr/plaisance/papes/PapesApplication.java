package fr.plaisance.papes;

import fr.plaisance.papes.data.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PapesApplication implements CommandLineRunner {

    @Autowired
    private DataGenerator generator;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PapesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        generator.generateData();
    }

}
