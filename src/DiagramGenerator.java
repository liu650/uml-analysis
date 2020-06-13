import javax.swing.*;
import java.awt.*;

public class DiagramGenerator extends Canvas {


    public static void main(String[] args) {

        JFrame frame = new JFrame("My Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        Canvas canvas = new DiagramGenerator();
        Box box = new Box("string of box", new Position(100, 100));
        canvas.setSize(800, 800);
        // box.setSize(200, 200);
        frame.add(box);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

}
