package ttl.larku.mat;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author whynot
 */
public class Garbager {
    private List<Data> stuff = new ArrayList<>();
    private JFrame frame = new JFrame("Garbager");

    public static void main(String[] args) {
        Garbager gb = new Garbager();
        gb.go();
    }

    public Garbager() {

        for (int i = 0; i < 10000; i++) {
            stuff.add(new Data("Name: " + i, i + 10, i + 20));
        }

        frame = new JFrame("Garbager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Press to Exit");
        button.addActionListener((e) -> {
            int index = ThreadLocalRandom.current().nextInt(10000);
            System.out.println(stuff.get(index).bytes[0]);
//            System.exit(0);
        });

        frame.add(button, BorderLayout.CENTER);

        frame.setSize(new Dimension(300, 300));
    }

    public void go() {
        frame.setVisible(true);

        frame.setVisible(true);
    }
}

class Data
{
    public String name;
    public int x, y;
    public byte [] bytes;

    public Data(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;

        bytes = new byte[10000];
    }
}
