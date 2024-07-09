package ttl.larku.challenge.solutions;

/**
 * @author whynot
 */

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Here's a list of seven-letter words. For each one, change the
 * first and last letters — but only the first and last letters — to make a
 * new, uncapitalized seven-letter word. Both the first and last letters have to change.
 * Program your solution, and use Java functional programming and
 * not imperative programming style. You may need to download an external word
 * list, for example, https://gist.github.com/wchargin/8927565 , since /usr/share/dict/words
 * doesn't have a comprehensive list. As a hint, you may need to create your own
 * Pair or Tuple2. Be sure to print the answer in a nice format.
 * Ex. TOURISM --> NOURISH
 * 1. PARTOOK
 * 2. TERSELY
 * 3. GUNROOM
 * 4. HELLISH
 * 5. LORELEI
 * 6. CARCASS
 * 7. GORDIAN
 * 8. LIGNITE
 * 9. PARTING
 */
public class Words {
    static String[] input = {"PARTOOK",
            "TERSELY",
            "GUNROOM",
            "HELLISH",
            "LORELEI",
            "CARCASS",
            "GORDIAN",
            "LIGNITE",
            "PARTING"
    };

    public static void main(String[] args) throws URISyntaxException, IOException {
        mapOfWordAndListOfMatches();
        System.out.println("***********");
        mapOfWordAndListOfMatchesWithCaching();
        System.out.println("***********");
        mapOfWordAndFirstMatchtWithCaching();
        System.out.println("***********");
        mapOfWordAndListOfMatchesFromWordsFirst();
    }

    static class Pair<K, V> {
        public K first;
        public V second;


        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }


    /**
     *
     * @param word
     * @return
     */
    private static boolean isOk(String word) {
        return word.length() == 7 && !word.contains("'");

    }
    /**
     * We iterate over the main word list and look for matches by
     * streaming our input list each time looking for a match.
     * Seems kind of wasteful, but see below.
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    public static Map<String, Set<String>> mapOfWordAndListOfMatches() throws URISyntaxException, IOException {
        URL url = Words.class.getClassLoader().getResource("words");
        Map<String, Set<String>> wordMap = Files.lines(Paths.get(url.toURI()))
                //No apostrophes and 7 letters only
                .filter(word -> word.length() == 7 && !word.contains("'"))
                .map(word -> {
                    String foundWord = Arrays.stream(input)
                            //.map(s1 -> s1.substring(1, 5))
                            .filter(s2 -> {
                                String tmp1 = s2.substring(1, 6);
                                String tmp2 = word.substring(1, 6);
                                return tmp1.equalsIgnoreCase(tmp2);
                            })
                            .findFirst().orElse("Not Found");

                    return new Pair<>(word, foundWord);
                }).filter(pair -> !pair.second.equals("Not Found")
                        && !pair.first.equalsIgnoreCase(pair.second))
                //.collect(Collectors.toSet());
                .collect(Collectors.groupingBy((Pair<String, String> pair) -> pair.second,
                        Collectors.mapping(p -> p.first, Collectors.toSet())));

        wordMap.forEach((k, v) -> System.out.println(k + ": " + v));

        return wordMap;
    }


    /**
     * Make a cache of substring => List of the input words.  We then
     * iterate over the main word list and look for matches in our cached map.
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    public static Map<String, List<String>> mapOfWordAndListOfMatchesWithCaching() throws URISyntaxException, IOException {
        //cache substring => List of matching words (could be more than one)
        Map<String, List<String>> subStringtoListOfWords = Arrays.stream(input)
                .collect(Collectors.groupingBy(w -> w.substring(1, 6).toLowerCase()));

        URL url = Words.class.getClassLoader().getResource("words");
        Map<String, List<String>> wordMap = Files.lines(Paths.get(url.toURI()))
                .filter(Words::isOk)
                .flatMap(word -> {
                    String tmp2 = word.substring(1, 6).toLowerCase();
                    if (subStringtoListOfWords.containsKey(tmp2)) {
                        List<String> orig = subStringtoListOfWords.get(tmp2);
                        return orig.stream().map(o -> new Pair<>(word, o));
                    } else {
                        return Stream.of(new Pair<>(word, "Not Found"));
                    }
                }).filter(pair -> !pair.second.equals("Not Found") &&
                        !pair.first.equalsIgnoreCase(pair.second))
                .collect(Collectors.groupingBy((Pair<String, String> pair) -> pair.second,
                        Collectors.mapping(p -> p.first, Collectors.toList())));

        wordMap.forEach((k, v) -> System.out.println(k + ": " + v));

        return wordMap;
    }

    /**
     * Return only the first match, if any.
     * Make a cache of substring => List of the input words.  We then
     * iterate over the main word list and look for matches in our cached map.
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    public static Map<String, String> mapOfWordAndFirstMatchtWithCaching() throws URISyntaxException, IOException {
        //cache substring => List of matching words (could be more than one)
        Map<String, List<String>> subStringtoListOfWords = Arrays.stream(input)
                .collect(Collectors.groupingBy(w -> w.substring(1, 6).toLowerCase()));

        URL url = Words.class.getClassLoader().getResource("words");
        Map<String, String> wordMap = Files.lines(Paths.get(url.toURI()))
                .filter(Words::isOk)
                .flatMap(word -> {
                    String tmp2 = word.substring(1, 6).toLowerCase();
                    if (subStringtoListOfWords.containsKey(tmp2)) {
                        List<String> orig = subStringtoListOfWords.get(tmp2);
                        return orig.stream().map(o -> new Pair<>(word, o));
                    } else {
                        return Stream.of(new Pair<>(word, "Not Found"));
                    }
                }).filter(pair -> !pair.second.equals("Not Found") &&
                        !pair.first.equalsIgnoreCase(pair.second))
                .collect(Collectors.toMap(pair -> pair.second,
                        (pair -> pair.first), (pair1, pair2) -> pair1));

        wordMap.forEach((k, v) -> System.out.println(k + ": " + v));

        return wordMap;
    }

    /**
     * Here we make a map of substring to List of words in the dictionary, i.e.
     * we are working "backwards" from the previous approach.
     * <p>
     * We then iterate over our input to do the work.
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    public static Map<String, List<String>> mapOfWordAndListOfMatchesFromWordsFirst() throws URISyntaxException, IOException {
        //cache words and substrings
        URL url = Words.class.getClassLoader().getResource("words");
        Map<String, List<String>> subStrings = Files.lines(Paths.get(url.toURI()))
                .filter(Words::isOk)
                .collect(Collectors.groupingBy(w -> w.substring(1, 6),
                        TreeMap::new,
                        Collectors.toList()));

        Map<String, List<String>> wordMap = Arrays.stream(input)
                .flatMap(word -> {
                    String tmp2 = word.substring(1, 6).toLowerCase();

                    if (subStrings.containsKey(tmp2)) {
                        List<String> orig = subStrings.get(tmp2);
                        return orig.stream().map(o -> new Pair<>(o, word));
                    } else {
                        return Stream.of(new Pair<>("Not Found", word));
                    }
                }).filter(pair -> !pair.second.equals("Not Found") &&
                        !pair.first.equalsIgnoreCase(pair.second))
                .collect(Collectors.groupingBy((Pair<String, String> pair) -> pair.second,
                        Collectors.mapping(p -> p.first, Collectors.toList())));

        wordMap.forEach((k, v) -> System.out.println(k + ": " + v));

        return wordMap;
    }
}
