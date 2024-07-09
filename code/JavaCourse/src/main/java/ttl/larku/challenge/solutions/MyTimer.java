package ttl.larku.challenge.solutions;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

/**
 * @author whynot
 */
public class MyTimer {

    public static long measureTime(Supplier<Integer> supplier) {
        Instant start = Instant.now();
        int throwThisAway = supplier.get();
        Instant end = Instant.now();
        return start.until(end, ChronoUnit.MILLIS);
    }

    public static void main(String[] args) {
        long result = measureTime(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 99999;
        });

        System.out.println("Lambda took(ms): " + result);
    }
}
