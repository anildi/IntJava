package ttl.larku.labs.exceptions.solved;


import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author whynot
 */
public class ExceptionsExtra {

    List<String> siteAddresses = List.of("https://google.com", "https://xyz.com", "https://zyx.com");

    //TODO - You need to have completed Exception Lab 3.
    // Copy your implementation of this function from your solution.
    public List<String> processAddressesWithVavr() {
        var result = siteAddresses.stream()
                .map(address -> Try.of(() -> connectAndGetResult(address)))
                .filter(Try::isSuccess)
                .map(Try::get)
                .collect(toList());
        return result;
    }

    //TODO - Part B.  How would you change the function above to
    // give you both the errors and the failures?
    // Uncomment the function below and implement it.
    public List<Try<String>> processAddressesForSuccessAndErrors() {
        var result = siteAddresses.stream()
                .map(address -> Try.of(() -> connectAndGetResult(address)))
                .collect(toList());
        return result;
    }

    class Pair<A, B>
    {
        public final A first;
        public final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    //TODO - Part C.  Change the implementation so you also
    // retain the address for all successes and failures.
    public List<Pair<String, Try<String>>> processAndRetainAddress() {
        var result = siteAddresses.stream().parallel()
                .map(address -> new Pair<String, Try<String>>(address, Try.of(() -> connectAndGetResult(address))))
                .collect(toList());
        return result;
    }

    public Map<String, Try<String>> processAndRetainAddressMap() {
        var result = siteAddresses.stream()
                .collect(toMap(address -> address, address -> Try.of(() -> connectAndGetResult(address))));

        return result;
    }

    //This is "bad" because we can have the stream make
    //the map for us, as in processAndRetainAddressMap above.
    public Map<String, Try<String>> processAndRetainAddressMapBad() {
        Map<String, Try<String>> result = new HashMap<>();
        siteAddresses.stream()
                .forEach(address -> {
                    Try<String> t1 = Try.of(() -> connectAndGetResult(address));
                    result.put(address, t1);
                });

        return result;
    }


    public String connectAndGetResult(String address) throws IOException {
        URL url = new URL(address);
        URLConnection connection = url.openConnection();
        connection.connect();

        InputStream iStream = connection.getInputStream();

        Scanner scanner = new Scanner(iStream);
        String firstLine = scanner.nextLine();

        iStream.close();

        return firstLine;

    }


    //TODO - Part B. Write a Test for your implementation
    // of processAddressForSuccessAndFailures.
    @Test
    public void testGetSuccessAndFailure() {
        List<Try<String>> results = processAddressesForSuccessAndErrors();
        results.stream()
                .forEach(t -> {
                    t.onSuccess(line -> System.out.println("Success: " + line))
                            .onFailure(ex -> System.out.println("Failure: " + ex));
                });
    }

    @Test
    public void testGetAndRetainAddress() {
//        List<Pair<String, Try<String>>> results = processAndRetainAddress();
        var results = processAndRetainAddress();
        results.stream()
                .forEach(pair -> {
                    pair.second.onSuccess(line -> System.out.println("Success: " + "Address: " + pair.first + ", line: "  + line))
                            .onFailure(ex -> System.out.println("Failure: " + "Address: " + pair.first + ", line: " + ex));
                });

        var results2 = processAndRetainAddressMap();
        results2.forEach((k, v) -> System.out.println(k + ", " + v));
    }

}
