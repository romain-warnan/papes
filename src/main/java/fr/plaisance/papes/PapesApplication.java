package fr.plaisance.papes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PapesApplication implements CommandLineRunner {

    @Autowired
    private PapesRetriever papesRetriever;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PapesApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        papesRetriever.retrieve("http://www.histoirdefrance.fr/question/papes.htm");
    }

}
