package ttl.larku.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author whynot
 */
public class WordCounter {

    public static void main(String[] args) throws IOException {
        WordCounter wc = new WordCounter();
        wc.countWords();
    }

    public void countWords() throws IOException {
        var result = Files.lines(Paths.get("AliceInWonderland.txt"))
                .flatMap(line -> Arrays.stream(line.split("\\W")))
                .filter(word -> !word.matches("^\\s*$"))
                //.collect(groupingBy(word -> word, () -> new TreeMap<>(), Collectors.counting()));
                .collect(groupingBy(word -> word, TreeMap::new, Collectors.counting()));

        result.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
