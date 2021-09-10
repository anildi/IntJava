package ttl.larku.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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


        Comparator<String> byValueComp = (a, b) -> {
            Long v1 = result.get(a);
            Long v2 = result.get(b);

            int ret = v1.compareTo(v2);

            return ret != 0 ? ret : a.compareTo(b);
            // return ret;
        };

        Comparator<String> byValueCompDesc = byValueComp.reversed();

        Map<String, Long> mapSortedByValue = new TreeMap<>(byValueCompDesc);
        mapSortedByValue.putAll(result);

        mapSortedByValue.forEach((k, v) -> System.out.println(k + ": " + v));

    }
}
