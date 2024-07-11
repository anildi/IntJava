package ttl.larku.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author whynot
 */
public class GenericsDemo {

    public static void main(String[] args) {
        GenericsDemo gcd = new GenericsDemo();
//        gcd.go();
        gcd.wildCards();
    }


    /*
            Object                                             List<Object>  => Integer, Double, String, Student, etc.

            Number                                             List<Number>  => Integer, Double, Float etc.

         Integer,  Double, etc.                                List<Integer> => only Integer
     */

    public void wildCards() {

        List<Number> lNumber = new ArrayList<>();
        lNumber.add(10.543);
        lNumber.add(22.456);
        lNumber.add(22);
        lNumber.add(54.3837);

        double numberSum = sum(lNumber);

        System.out.println("numberSum: " + numberSum);

        List<Integer> lInts = new ArrayList<>();
        lInts.add(10);
//        lInts.add(23.456);
        lInts.add(20);
        lInts.add(30);
        lInts.add(500);

        double intSum = sum(lInts);

        Integer[] iarr = {100, 200, 300};
        addAll(lInts, iarr);

        addAll(lNumber, iarr);

        List<String> lStr = new ArrayList<>();
        String [] sarr = {"one", "two", "three"};

        addAll(lStr, sarr);

        List<Double> dList = new ArrayList<>();
        Double [] darr = {100.0, 200.0};

        addAll(dList, darr);

        addAll(lNumber, darr);

        lInts.forEach(System.out::println);
    }

    //PECS  -> Producer Extends, Consumer Super

    public double sum(List<? extends Number> input) {
//       input.add(23.456);

        double result = 0.0;
        for (Number n : input) {
            result += n.doubleValue();
        }

        return result;
    }

    public <T> void addAll(List<? super T> input, T[] arr) {
//        Integer i = input.get(0);
        Object o = input.get(0);

        for (T it : arr) {
            input.add(it);
        }
    }


    static int frequency(Collection<?> c, Object o) {
        int result = 0;
        for(Object obj : c) {
            if(obj.equals(o)) {
                result++;
            }
        }

        return result;
    }

    public static <T> void copy(List<? super T> dest,
                                List<? extends T> src) {

    }

    public void go() {

        List<String> lString = new ArrayList<>();
        lString.add("hello");

//        lString.add(0);

        List badList = lString;

        badList.add(0);

        for (Object o : lString) {
            String s = (String) o;
            System.out.println("s: " + s);
        }
    }
}
