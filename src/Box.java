import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Box extends JPanel {
    private String classInfo;
    private List<String> fieldsInfo;
    private List<String> methodsInfo;
    private Position center;
    private int width = 20;
    private int length = 10;

    Box(String c, Position center) {
        setBorder(BorderFactory.createLineBorder(Color.BLUE));

        this.classInfo = c;
        this.center = center;
        this.fieldsInfo = new ArrayList<>();
        this.methodsInfo = new ArrayList<>();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawString(classInfo, 10, 20);
        graphics2D.drawRect(center.x, center.y, width, length);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(center.x, center.y, width, length);
    }
}
