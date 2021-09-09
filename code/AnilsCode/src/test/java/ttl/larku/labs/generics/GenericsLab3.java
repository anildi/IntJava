package ttl.larku.labs.generics;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * TODO - Generics Lab 3.  Instructions are in TODO comments below.
 *
 * @author whynot
 */
public class GenericsLab3 {


    //TODO - Analyze the code in the Event class and the
    // awkwardWayOfReturningThreeThings function and
    // the test below it.  The functions job is to
    // retrieve an Event from somewhere (DB, Web Service, etc.)
    // and return three pieces of information from it.
    // Right now it is doing it by returning an Object array.
    // Your Task instructions are after the test
    class Event {
        public final int id;
        public final String place;
        public final LocalDate date;
        public List<String> details = new ArrayList<>();

        public Event(int id, String place, LocalDate date) {
            this.id = id;
            this.place = place;
            this.date = date;
        }
    }

    //TODO - this is an awkward way of returning 3 things.
    // Awkward to write, and awkward to get at the
    // information, as in the test below.
    public Object[] awkwardWayOfReturningThreeThings(int id) {
        //find event in DB, or from Web service, etc.
        //We are faking that here and hard coding
        Event event = new Event(id, "Atlanta", LocalDate.of(2000, 10, 10));

        Object[] arr = new Object[3];
        arr[0] = event.id;
        arr[1] = event.place;
        arr[2] = event.date;

        return arr;
    }

    //TODO - Lots of casting etc. involved to read the data
    // Your task instructions are below.
    @Test
    public void testAwkwardWayOfReturningThreeThings() {
        Object[] arr = awkwardWayOfReturningThreeThings(10);
        int id = (int) arr[0];
        String place = (String) arr[1];
        LocalDate date = (LocalDate) arr[2];

        System.out.println("Result: id: " + id + ", place: " + place + ", date: " + date);
        assertEquals(place, "Atlanta");
    }

    //TODO - Write a class to make it easier to return 3 things
    // in a type safe way.
    // Set it up so that your class can be used for different types
    // and does not require the user to do any casting to retrieve
    // the data.
    // Uncomment the code below and implement.  You will also have to
    // implement the typeSafeWayOfReturningThreeThings function below.
//    class XXXX {
//    }

//    public XXXX typeSafeWayOfReturningThreeThings(int id) {
//        //find event in DB, or from Web service, etc.
//        //We are faking that here and hard coding
//        Event event = new Event(id, "Atlanta", LocalDate.of(2000, 10, 10));
//
//    }


    @Test
    public void testTypeSafeWayOfReturningThreeThings() {
//        XXXX = awkwardWayOfReturningThreeThings(10);



//        assertEquals(place, "Atlanta");
    }
}
