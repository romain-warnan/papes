package fr.plaisance.papes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PapesRetriever {

    public void retrieve(String url) throws IOException {
        List<String> lines = new ArrayList<>(300);
        Jsoup.connect(url)
                .execute()
                .charset("windows-1252")
                .parse()
                .getElementsByAttributeValue("border", "1")
                .first()
                .getElementsByTag("tr")
                .forEach(element -> {
                    lines.add(this.line(element, 1));
                    lines.add(this.line(element, 2));
                    lines.add(this.line(element, 3));
                });
        List<String> filteredLines = lines.stream()
                .filter(line -> !line.startsWith("N°") && !line.startsWith("?"))
                .collect(Collectors.toList());
        Files.write(Paths.get("papes.csv"), filteredLines, Charset.forName("UTF-8"));
    }

    private String line(Element element, int colonne) {
        String ordre = element.child(4 * (colonne - 1)).text();
        String election = element.child(4 * (colonne - 1) + 1).text();
        String nom = element.child(4 * (colonne - 1) + 2).text();
        String nationalite = element.child(4 * (colonne - 1) + 3).text();
        return String.format("%s;%s;%s;%s;", ordre, election, nom, nationalite);
    }

}
