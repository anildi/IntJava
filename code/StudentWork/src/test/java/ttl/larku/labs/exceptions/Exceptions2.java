package ttl.larku.labs.exceptions;


import org.junit.jupiter.api.Test;

import java.net.URL;
import java.net.URLConnection;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author whynot
 */
public class Exceptions2 {

    List<String> siteAddresses = List.of("https://google.com", "https://zxy.com", "https://thethirdlane.com");

    //TODO.  This method should process a list of address and
    // return a List of results.  We have to turn each
    // String address into a URI, and then "connect" to that
    // address. We are using the 'connectAndGetResult' method
    // to make a connection to a given URL.
    // For this use case, we don't care about the errors, we
    // just want to throw them away.
    // Add code to this method to do the needful.  Use Streams
    // and the ExWrapper class to make it work without try/catch.
    public List<String> processAddresses() {
        return null;
    }

    //TODO.  Make a version of the function above where you
    // still return a List of results, but also process any
    // Exceptions that get thrown.
    public List<String> processAddressesAndExceptions() {
        return null;
    }

    /**
     * Ping the given address.  If the connection succeeds we
     * return an ExWrapper with a value.  If we can't connect
     * If we can't connect we return an ExWrapper with an
     * Exception.
     * @param address
     * @return ExWrapper with value on success
     *         ExWrapper with Exception on failure
     */
    public ExWrapper<String> connectAndGetResult(String address) {
        try {
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            connection.connect();

            //make network call and get result;
            String result = "Address: " + address + ", at: " + LocalTime.now();
            return ExWrapper.ofValue(result);
        }catch(Exception e) {
            return ExWrapper.ofError(e);
        }
    }

    //TODO - Make the test run successfully.
    @Test
    public void testGetOnlySuccesses() {
       List<String> results = processAddresses();

        System.out.println("Results.size: " + results.size());
        results.forEach(System.out::println);

        assertEquals(2, results.size());
    }

    //TODO - Make the test run successfully.
    @Test
    public void testGetSuccessAndExceptions() {
        List<String> results = processAddressesAndExceptions();

        System.out.println("Results.size: " + results.size());
        results.forEach(System.out::println);

        assertEquals(2, results.size());
    }
}
