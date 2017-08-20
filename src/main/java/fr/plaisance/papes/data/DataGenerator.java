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
import java.util.Map;

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
        this.generatePlusLongsRegnes();
        this.generatePlusCourtsRegnes();
    }

    private void generatePlusLongsRegnes() throws IOException {
        List<Item> plusLongsPontificats = Itemizer.itemize(service.plusLongsPontificats(papes), this::itemForDureeRegne);
        String json = mapper.writeValueAsString(plusLongsPontificats);
        Files.write(Paths.get("src/main/webapp/data/plus-longs-regnes.json"), json.getBytes());
    }

    private void generatePlusCourtsRegnes() throws IOException {
        List<Item> plusLongsPontificats = Itemizer.itemize(service.plusCourtsPontificats(papes), this::itemForDureeRegne);
        String json = mapper.writeValueAsString(plusLongsPontificats);
        Files.write(Paths.get("src/main/webapp/data/plus-courts-regnes.json"), json.getBytes());
    }

    private Item itemForDureeRegne(Map.Entry<Pape, Long> entry) {
        return Item.of(String.format("%s : %s", Papes.nomComplet(entry.getKey()), Papes.affichageDureeRegne(entry.getKey())), entry.getValue());
    }

    private void generatePapeParSaintete() throws IOException {
        List<Item> items = Itemizer.itemize(service.papesParSaintete(papes), this::itemForSaintete);
        String json = mapper.writeValueAsString(items);
        Files.write(Paths.get("src/main/webapp/data/papes-par-saintete.json"), json.getBytes());
    }

    private Item itemForSaintete(Map.Entry<Boolean, Long> entry) {
        return Item.of(entry.getKey() ? "Canonisé" : "Pas canonisé", entry.getValue());
    }
}
