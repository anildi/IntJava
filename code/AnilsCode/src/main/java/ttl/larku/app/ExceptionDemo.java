package ttl.larku.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author whynot
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        ExceptionDemo ed = new ExceptionDemo();
        ed.tryWithResources();
    }

    public void go() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("pom.xml");
            int c = fis.read();

            System.out.println("Got char: " + c);
            //Something here blows up
            String s = null;
            s.length();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void tryWithResources() {
        try(MyClass mc = new MyClass(10); FileInputStream fis = new FileInputStream("pom.xml");) {
            int c = fis.read();

            System.out.println("Got char: " + c);
            //Something here blows up
            String s = null;
            s.length();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyClass implements AutoCloseable
{
    public MyClass(int x) {
        //Aquire something with X
    }
    @Override
    public void close() {
        System.out.println("Giving Back The resource");
    }
}
