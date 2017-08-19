package fr.plaisance.papes.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.plaisance.papes.model.Pape;
import fr.plaisance.papes.service.PapesLoader;
import fr.plaisance.papes.service.PapesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void generateData() throws IOException {
        List<Pape> papes = loader.loadAll();
        ObjectMapper mapper = new ObjectMapper();

        List<Item> items = Itemizer.itemize(service.papesParSaintete(papes), entry -> Item.of(entry.getKey() ? "Canonisé" : "Pas canonisé", entry.getValue()));
        String json = mapper.writeValueAsString(items);
        Files.write(Paths.get("docs/data/papes-par-saintete.json"), json.getBytes());
    }
}
