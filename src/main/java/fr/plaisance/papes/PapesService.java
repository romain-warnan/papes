package fr.plaisance.papes;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PapesService {

    public Map<String, Long> papesParNationalite(Collection<Pape> papes) {
        return papes.stream()
                .collect(Collectors.groupingBy(Pape::getNationalite, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<Pape, Integer> papesParDureeDeRegne(Collection<Pape> papes) {
        return papes.stream()
                .sorted(Comparator.comparingInt(Papes::dureeRegne).reversed())
                .collect(Collectors.toMap(Function.identity(), Papes::dureeRegne, (a, b) -> a, LinkedHashMap::new));
    }
}
