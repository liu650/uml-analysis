import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DiagramGenerator extends JPanel {
    private static int x_offset = 300, y_offset = 260;
    private static int class_width = 200, class_height = 184;
    static Position upperLeft = new Position(30, 30);

    private ArrayList<Position> localPositions = findAllPosition(upperLeft, x_offset, y_offset);
    public static Position MM= new Position(upperLeft.x + 1 * x_offset, upperLeft.y + 1 * y_offset);

    public List<Box> iboxes = new ArrayList<>();
    public List<Arrow> iarrows = new ArrayList<>();

     DiagramGenerator(List<Box> boxes, List<Arrow> arrows){
        this.iboxes = boxes;
        this. iarrows = arrows;
    }

    public void paint (Graphics gp) {
        int numberOfBox = 8;

        Box currBox;//keep track of the current box
        //painter--set color before using
        BasicStroke bs = new BasicStroke( 10.1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);
        super.paint(gp);
        Graphics2D gp2d = (Graphics2D) gp;

        // draw line from all the boxes to center
        // use positionIndex as below
        //1 2 3
        //7 9 8
        //4 5 6
        for (int i = localPositions.size() - numberOfBox; i< numberOfBox -1; i++){
            drawLineToCenter(gp2d, localPositions, i);
        }

        // draw all the classes
        for (int i = localPositions.size() - numberOfBox; i < localPositions.size(); i++){
            int boxIndex =  i + numberOfBox - localPositions.size();
            drawClass(gp2d, localPositions.get(i), "Class." + Integer.toString(boxIndex));
        }

        drawAnnotation(gp2d);
    }

    // draw a line from the given index position to center position
    // Assume the center position should the last item in the list
    private void drawLineToCenter(Graphics2D gp2d, ArrayList<Position> localPositions, int positionIndex){
         Position center = localPositions.get(localPositions.size() - 1);
         Position givenPosition = localPositions.get(positionIndex);
         gp2d.setColor(Color.DARK_GRAY);
         gp2d.drawLine(givenPosition.x + class_width/2, givenPosition.y + class_height/2 ,
                 center.x + class_width/2, center.y + class_height/2);
    }

    private void drawClass(Graphics2D gp2d, Position classPosition, String className) {
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(classPosition.x, classPosition.y, class_width, class_height, 20, 20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString(className, classPosition.x + 15, classPosition.y + 15);
        gp2d.drawLine(classPosition.x, classPosition.y + 20, classPosition.x + 200, classPosition.y + 20);
        gp2d.drawString("field", classPosition.x + 15, classPosition.y + 35);
        gp2d.drawLine(classPosition.x, classPosition.y + 90, classPosition.x + 200, classPosition.y + 90);
        gp2d.drawString("method", classPosition.x + 15, classPosition.y + 100);
    }

    private void drawAnnotation(Graphics2D gp2d) {
        int annotation_x_offset = -80; //The value is intentionally Negative
        Position annotaionBox = new Position(upperLeft.x + x_offset * 3 + annotation_x_offset, 40);
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawRect(annotaionBox.x,annotaionBox.y,140,300);
        gp2d.drawString("annotation", annotaionBox.x + 30,annotaionBox.y + 15);
    }

    //1 2 3
    //7 9 8
    //4 5 6
    protected static ArrayList<Position> findAllPosition(Position upperLeft, int x_offset, int y_offset){
        ArrayList<Position> positions= new ArrayList<>();

        //process row 1 and row 3 with give order
        for (int j = 0; j<3; j+=2){
            for (int i = 0; i<3; i++){
                positions.add(new Position(upperLeft.x + i * x_offset, upperLeft.y + j * y_offset));
            }
        }

        //process row 2
        positions.add(new Position(upperLeft.x + 0 * x_offset, upperLeft.y + 1 * y_offset));
        positions.add(new Position(upperLeft.x + 2 * x_offset, upperLeft.y + 1 * y_offset));
        positions.add(new Position(upperLeft.x + 1 * x_offset, upperLeft.y + 1 * y_offset));

        return positions;
    }

}
