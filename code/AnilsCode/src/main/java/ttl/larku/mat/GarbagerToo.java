package ttl.larku.mat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author whynot
 */
public class GarbagerToo {

    private static Set<GeeToo> stuff = new HashSet<>();

    public static void main(String[] args) {
        GeeToo g1 = new GeeToo("Joe");
        for(int i = 0; i < 1000; i++) {
            addit(g1);
        }
    }

    public static void addit(GeeToo g) {
        if(!stuff.contains(g)) {
            stuff.add(g);
            System.out.println("added: " + g);
        }
        else {
            System.out.println("obj already in map: " + g);
        }
    }

}

class GeeToo
{
    private int [] data = new int[10000];
    private String name;
    private int accessCount = 0;

    public GeeToo(String name) {
        this.name = name;
    }

    public int[] getData() {
        accessCount++;
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String getName() {
        accessCount++;
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GeeToo{" +
                "Name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeeToo geeToo = (GeeToo) o;
        return
                //accessCount == geeToo.accessCount &&
                Arrays.equals(data, geeToo.data) &&
                Objects.equals(name, geeToo.name);
    }

    @Override
    public int hashCode() {
        //int result = Objects.hash(name, accessCount);
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
