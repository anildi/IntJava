package ttl.larku.app;

import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author whynot
 */
public class ExceptionsLamdas {
    private static Logger logger = LoggerFactory.getLogger(ExceptionsLamdas.class);

    public static void main(String[] args) {
        List<String> fileNames = Arrays.asList(".gitignore", "doesNotExist", "pom.xml");
        //callFileTheOldWay(fileNames);
        callFileWithWrapperToo(fileNames);
    }

    public static void callFileTheOldWay(List<String> fileNames) {
        try {
            //List<String> firstLines = filesTheOldWay(fileNames);
            List<String> firstLines = filesWithLambdaTheBadWay(fileNames);
            firstLines.stream().map(String::toUpperCase).forEach(ch -> {
                //put into DB
                System.out.println(ch);
            });
        } catch (IOException e) {
            //log exception
            logger.error(e.toString());
            System.out.println(e);
        }
    }

    public static List<String> filesTheOldWay(List<String> fileNames) throws IOException {
        List<String> firstLines = new ArrayList<>();
        for (String fileName : fileNames) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String ch = reader.readLine();
                firstLines.add(ch);
            }
        }
        return firstLines;
    }


    public static List<String> filesWithLambdaTheBadWay(List<String> fileNames) throws IOException {
        List<String> firstLines = fileNames.stream()
                .map(fileName -> {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        String line = reader.readLine();
                        return line;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(toList());

        return firstLines;
    }

    public static void callFileWithLambdaBetterWay(List<String> fileNames) {
        List<Optional<String>> firstLines = filesWithLambdaABetterWay(fileNames);
        firstLines.stream()
                .map(Optional::get)
                .forEach(ch -> {
                    //put into DB
                    System.out.println(ch);
                });
    }

    public static List<Optional<String>> filesWithLambdaABetterWay(List<String> fileNames) {
        List<Optional<String>> firstLines = fileNames.stream()
                .map(fileName -> {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        String line = reader.readLine();
                        return Optional.of(line);
                    } catch (IOException e) {
                        logger.error(e.toString());
                    }
                    return Optional.<String>empty();
                }).filter(Optional::isPresent)
                .collect(toList());

        return firstLines;
    }

    public static void callFileWithWrapper(List<String> fileNames) {
        List<Wrapper> firstLines = filesWithWrapper(fileNames);
        firstLines.stream()
                .forEach(wrapper -> {
                    if (wrapper.isValue()) {
                        String value = wrapper.value;
                        //put into DB
                        System.out.println(value);
                    } else {
                        logger.error("Error: " + wrapper.exception);
                    }
                });
    }

    public static void callFileWithWrapperToo(List<String> fileNames) {
        List<Wrapper> firstLines = filesWithWrapper(fileNames);
        List<String> transformedLines = firstLines.stream()
                .peek(wr -> System.out.println("Peek: " + wr))
                .map(wr -> wr.map(s -> s.toUpperCase()))
                .filter(Wrapper::isValue)
                .map(Wrapper::get)
                .collect(toList());

        transformedLines.forEach(System.out::println);

    }

    public static List<Wrapper> filesWithWrapper(List<String> fileNames) {
        List<Wrapper> firstLines = fileNames.stream()
                .map(fileName -> {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        String line = reader.readLine();
                        return Wrapper.ofValue(line);
                    } catch (IOException e) {
                        return Wrapper.ofError(e);
                    }
                }) //.filter(Wrapper::isValue)
                .collect(toList());

        return firstLines;
    }

    public static List<Try<String>> filesWithVavr(List<String> fileNames) {
        List<Try<String>> firstLines = fileNames.stream()
                .map(fileName ->
                        Try.of(() -> {
                            BufferedReader reader = new BufferedReader(new FileReader(fileName));
                            String line = reader.readLine();
                            return line;
                        })
                ) //.filter(Wrapper::isValue)
                .collect(toList());

        return firstLines;
    }


    static class Wrapper {
        private String value;
        private Exception exception;

        public static Wrapper ofValue(String line) {
            return new Wrapper(line, null);
        }

        public static Wrapper ofError(Exception e) {
            return new Wrapper(null, e);
        }

        public String get() {
            if (isValue()) {
                return value;
            }
            throw new NoSuchElementException("Wrapper does not have a value");
        }

        public Wrapper map(Function<String, String> function) {
            if (isValue()) {
                value = function.apply(value);
            }
            return this;
        }

        public boolean isValue() {
            return value != null;
        }

        public boolean isError() {
            return exception != null;
        }


        private Wrapper(String value, Exception exception) {
            this.value = value;
            this.exception = exception;
        }

        @Override
        public String toString() {
            return "Wrapper{" +
                    "value='" + value + '\'' +
                    ", exception=" + exception +
                    '}';
        }
    }

}
