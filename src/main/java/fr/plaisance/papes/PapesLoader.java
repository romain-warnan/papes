package fr.plaisance.papes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PapesLoader {

    @Autowired
    private PapeBuilder builder;

    public List<Pape> loadAll() throws IOException {
        return Files.readAllLines(Paths.get("papes.txt"), Charset.forName("UTF-8"))
                .stream()
                .map(builder::buildOne)
                .collect(Collectors.toList());
    }
}
