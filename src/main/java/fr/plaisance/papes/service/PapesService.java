package fr.plaisance.papes.service;

import fr.plaisance.papes.model.Pape;
import fr.plaisance.papes.model.Papes;
import fr.plaisance.papes.model.Succession;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public Map<String, Long> papesParNomDeRegne(Collection<Pape> papes) {
        return papes.stream()
                .collect(Collectors.groupingBy(Pape::getNom, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 4)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<Pape, Long> plusCourtsPontificats(Collection<Pape> papes) {
        return papes.stream()
                .sorted(Comparator.comparingLong(Papes::dureeRegne))
                .limit(10)
                .collect(Collectors.toMap(Function.identity(), Papes::dureeRegne, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<Pape, Long> plusLongsPontificats(Collection<Pape> papes) {
        return papes.stream()
                .sorted(Comparator.comparingLong(Papes::dureeRegne).reversed())
                .limit(10)
                .collect(Collectors.toMap(Function.identity(), Papes::dureeRegne, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<Succession, Long> vacancesDuTrone(List<Pape> papes) {
        return IntStream.range(1, papes.size())
                .mapToObj(n -> new Succession(papes.get(n - 1), papes.get(n)))
                .sorted(Comparator.comparingLong(Papes::dureeVacanceTrone).reversed())
                .collect(Collectors.toMap(Function.identity(), Papes::dureeVacanceTrone, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<Boolean, Long> papesParSaintete(Collection<Pape> papes) {
        return papes.stream()
                .collect(Collectors.groupingBy(Pape::getSaint, Collectors.counting()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }
}
