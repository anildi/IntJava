package ttl.larku.solutions.lambdas;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ttl.larku.domain.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author whynot
 */
public class LamdasStreams {

    class Data implements Comparable<Data>{
        public final int id;
        public final String place;

        public Data(int id, String place) {
            this.id = id;
            this.place = place;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", place='" + place + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Data other) {
            return Integer.compare(this.id, other.id);
        }
    }

    @Test
    public void lab1() {
        List<Data> list = new ArrayList<>();
        list.add(new Data(1, "Atlanta"));
        list.add(new Data(10, "San Francisco"));
        list.add(new Data(5, "Quebec"));
        list.add(new Data(8, "Mumbai"));
        list.add(new Data(33, "Beijing"));

        Collections.sort(list);
        for(Data d : list) {
            System.out.println(d);
        }
    }

    //public static <T> void sort(List<T> list, Comparator<T> c) {}
    @Test
    public void lab2() {
        List<Data> list = new ArrayList<>();
        list.add(new Data(1, "Atlanta"));
        list.add(new Data(10, "San Francisco"));
        list.add(new Data(5, "Quebec"));
        list.add(new Data(8, "Mumbai"));
        list.add(new Data(33, "Beijing"));

        Comparator<Data> c1 = (data1, data2) -> data1.place.compareTo(data2.place);
        Collections.sort(list, c1);

        Collections.sort(list, (data1, data2) -> data1.place.compareTo(data2.place));
        for(Data d : list) {
            System.out.println(d);
        }
    }
}
