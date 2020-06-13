import javax.swing.*;
import java.awt.*;

public class DiagramGenerator extends Canvas {


    public static void main(String[] args) {

        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new DiagramGenerator();
        canvas.setSize(800, 800);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

}
