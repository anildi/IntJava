package ttl.larku.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BookApp {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // countWordsTheOldWay("PrideAndPrejudice.txt");
        Map<String, Long> cw = countWordsTheOldWayToo("PrideAndPrejudice.txt");
        //Map<String, Long> cw = countWords("PrideAndPrejudice.txt");
        cw.forEach(BookApp::printEntry);
    }

    private static <K, V> void printEntry(K key, V value) {
        System.out.println(key + " = " + value);
    }

    public static Map<String, Long> countWordsTheOldWay(String fileName) throws IOException {
        // List<String> lines = Files.readAllLines(Paths.get(fileName));
        //try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        try (InputStream is = BookApp.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            Map<String, Long> result = new TreeMap<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W");

                for (String word : words) {
                    if (!word.matches("\\s*")) {
                        long count = result.computeIfAbsent(word, (s -> 0L));
                        count++;
                        result.put(word, count);
                    }
                }
            }
            return result;
        }
    }

    public static Map<String, Long> countWordsTheOldWayToo(String fileName) throws IOException, URISyntaxException {
        URI uri = Objects.requireNonNull(BookApp.class.getClassLoader().getResource(fileName)).toURI();
        List<String> lines = Files.readAllLines(Paths.get(uri));

        //Map<String, Long> result = new HashMap<>();
        Map<String, Long> result = new TreeMap<>();
        for (String line : lines) {
            String[] words = line.split("\\W");

            for (String word : words) {
                if (!word.matches("\\s*")) {
                    long count = result.computeIfAbsent(word, (s -> 0L));
                    count++;
                    result.put(word, count);
                }
            }
        }
        return result;
    }


    public static Map<String, Long> countWords(String fileName) throws IOException, URISyntaxException {
        URI uri = Objects.requireNonNull(BookApp.class.getClassLoader().getResource(fileName)).toURI();
        Map<String, Long> result = Files.lines(Paths.get(uri))
                .flatMap(s -> Arrays.stream(s.split("\\W")))
                .filter(s -> !s.matches("\\s*"))
                .collect(Collectors.groupingBy(s -> s, TreeMap::new, Collectors.counting()));

        return result;
    }
}
