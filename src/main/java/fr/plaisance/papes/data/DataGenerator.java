package fr.plaisance.papes.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.plaisance.papes.model.Pape;
import fr.plaisance.papes.model.Papes;
import fr.plaisance.papes.service.PapesLoader;
import fr.plaisance.papes.service.PapesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DataGenerator {

    @Autowired
    private PapesService service;

    @Autowired
    private PapesLoader loader;

    private static List<Pape> papes;
    private static ObjectMapper mapper;

    @PostConstruct
    private void postConstruct() throws IOException {
        papes = loader.loadAll();
        mapper = new ObjectMapper();
    }

    public void generateData() throws IOException {
        this.generatePapeParSaintete();
        this.generatePapeParDureeDeRegne();
    }

    private void generatePapeParDureeDeRegne() throws IOException {
        List<Item> items = Itemizer.itemize(service.papesParDureeDeRegne(papes), entry -> Item.of(String.format("%s\n%s", Papes.nomComplet(entry.getKey()), Papes.affichageDureeRegne(entry.getKey())), entry.getValue()));
        String json = mapper.writeValueAsString(items);
        Files.write(Paths.get("src/main/webapp/data/papes-par-duree-de-regne.json"), json.getBytes());
    }

    private void generatePapeParSaintete() throws IOException {
        List<Item> items = Itemizer.itemize(service.papesParSaintete(papes), entry -> Item.of(entry.getKey() ? "Canonisé" : "Pas canonisé", entry.getValue()));
        String json = mapper.writeValueAsString(items);
        Files.write(Paths.get("src/main/webapp/data/papes-par-saintete.json"), json.getBytes());
    }
}
