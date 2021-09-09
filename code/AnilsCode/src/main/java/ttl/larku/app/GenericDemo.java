package ttl.larku.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author whynot
 */


public class GenericDemo {

    public static void main(String[] args) {
        GenericDemo st = new GenericDemo();
//        st.generics101();
        st.go();
    }

    public void generics101() {
        List<String> ls = new ArrayList<>();
        ls.add("abc");
//        ls.add(10);

        List badList = ls;
        badList.add(10);

        for(String s : ls) {
            System.out.println(s);
        }


    }
    /*   Object                                      List<Object>

     *   Number                                      List<Number>
     *
     * Integer,  Double                              List<Integer>
     */

    public void go() {
        List<Number> lNum = new ArrayList<>();
        lNum.add(10);
        lNum.add(22.33333);
        lNum.add(45);

        double sum = sum(lNum);
        System.out.println("sum: " + sum);

        List<Integer> lint = new ArrayList<>();
        lint.add(10);
        lint.add(20);

        sum(lint);

        Integer [] iarr = {39, 389};
        addTo(lint, iarr);

        System.out.println("lint: " + lint);


        addTo(lNum, iarr);

        frequency(lNum, 10);
        frequency(lint, 10);

        List<String> str = new ArrayList<>();
        frequency(str, "xyz");
    }

    //PECS   -  Producer Extends, Consumer Super

    public double sum(List<? extends Number> nums) {
        double sum = 0;
        for(Number n : nums) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public static int frequency(Collection<?> c, Object o) { return 10;}

    public <T> void addTo(List< ? super T> ints, T [] arr) {
        for(T it : arr) {
            ints.add(it);
        }
    }

//    public static <T> void copy(List<? super T> dest, List<? extends T> src)

}
