import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DiagramGenerator extends JPanel {
    private static int x_offset = 300, y_offset = 260;
    protected final static int BOX_WIDTH = 200, BOX_HEIGHT = 184;
    private static int arrow_length = 20;
    static Position upperLeft = new Position(30, 30);

    public List<Box> iboxes = new ArrayList<>();
    public List<Arrow> iarrows = new ArrayList<>();

    Stroke solidLine = new BasicStroke( 1.1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);
    Stroke dashedLine = new BasicStroke(1.1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, new float[]{9}, 0);

    DiagramGenerator(List<Box> boxes, List<Arrow> arrows){
        this.iboxes = boxes;
        this. iarrows = arrows;
    }

    public void paint (Graphics gp) {
        int numberOfBox = 7;
        ArrayList<Position> localPositions = findAllPosition(upperLeft, x_offset, y_offset);

        Box currBox;//keep track of the current box
        //painter--set color before using
        super.paint(gp);
        Graphics2D gp2d = (Graphics2D) gp;
        gp2d.setStroke(solidLine);

        // draw line from all the boxes to center
        // use positionIndex as below
        //1 2 3
        //7 9 8
        //4 5 6
        for (int i = localPositions.size() - numberOfBox; i < localPositions.size() - 1; i++){
            // drawLineToCenter(gp2d, localPositions, i);
            drawArrow(gp2d, new PositionPair(localPositions.get(8), localPositions.get(i)),
                    i%2 == 0 ? true : false);
            drawLine(gp2d, new PositionPair(localPositions.get(8), localPositions.get(i)), i%2 == 0 ? true : false);
        }

        // draw all the classes
        for (int i = localPositions.size() - numberOfBox; i < localPositions.size(); i++){
            int boxIndex =  i + numberOfBox - localPositions.size();
            drawClass(gp2d, localPositions.get(i), "Class." + Integer.toString(boxIndex));
        }

        drawAnnotation(gp2d);
    }

    protected void drawArrow(Graphics2D gp2d, PositionPair pair, Boolean isSolidArrow){
        Position p1 = pair.getEdgeP1(), p2 = pair.getEdgeP2();
        int xSign = p1.x > p2.x? 1: -1;
        int ySign = p1.y > p2.y? 1: -1;
        // drawLine(gp2d, new PositionPair(p1, p2), true);

        // draw a small triangle at the end of p2
        if (p1.x == p2.x && p1.y == p2.y){
            //ignore if a position is pointed to itself
        } else if (p1.y == p2.y){
            // handle divide by zero
            int[] xPoints = {p2.x, p2.x + xSign * (int)(arrow_length * Math.cos(Math.toRadians(30))),
                    p2.x + xSign * (int)(arrow_length * Math.cos(Math.toRadians(30)))};
            int[] yPoints = {p2.y, p2.y - (int)(arrow_length * Math.sin(Math.toRadians(30))),
                    p2.y + (int)(arrow_length * Math.sin(Math.toRadians(30)))};
            gp2d.setColor(Color.DARK_GRAY);
            if (isSolidArrow){
                gp2d.fillPolygon(xPoints, yPoints, 3);
            } else {
                gp2d.drawPolygon(xPoints, yPoints, 3);
            }
        } else {
            double angle1 = Math.atan(Math.abs(p2.x-p1.x)/Math.abs(p2.y-p1.y)) - Math.toRadians(30);
            double angle2 = Math.toRadians(120) - angle1;
            int[] xPoints = {p2.x, p2.x + xSign * (int)(arrow_length * Math.sin(angle1)),
                    p2.x + xSign * (int)(arrow_length * Math.sin(angle2))};
            int[] yPoints = {p2.y, p2.y + ySign * (int)(arrow_length * Math.cos(angle1)),
                    p2.y - ySign * (int)(arrow_length * Math.cos(angle2))};
            if (isSolidArrow){
                gp2d.fillPolygon(xPoints, yPoints, 3);
            } else {
                gp2d.drawPolygon(xPoints, yPoints, 3);
            }
        }
    }

    // draw a line from the given index position to center position
    // Assume the center position should the last item in the list
    protected void drawLineToCenter(Graphics2D gp2d, ArrayList<Position> localPositions, int positionIndex){
        Position center = localPositions.get(localPositions.size() - 1);
        Position givenPosition = localPositions.get(positionIndex);
        drawLine(gp2d, new PositionPair(givenPosition, center), false);
    }

    protected void drawLine(Graphics2D gp2d, PositionPair pair, boolean isDashed){
        Position p1 = pair.getEdgeP1(), p2 = pair.getEdgeP2();
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.setStroke(isDashed ? dashedLine : solidLine);
        gp2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        gp2d.setStroke(solidLine); // revert any possible change to stroke, to avoid further influence to code
    }

    protected void drawClass(Graphics2D gp2d, Position classPosition, String className) {
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(classPosition.x, classPosition.y, BOX_WIDTH, BOX_HEIGHT, 20, 20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString(className, classPosition.x + 15, classPosition.y + 15);
        gp2d.drawLine(classPosition.x, classPosition.y + 20, classPosition.x + 200, classPosition.y + 20);
        gp2d.drawString("field", classPosition.x + 15, classPosition.y + 35);
        gp2d.drawLine(classPosition.x, classPosition.y + 90, classPosition.x + 200, classPosition.y + 90);
        gp2d.drawString("method", classPosition.x + 15, classPosition.y + 100);
    }

    protected void drawAnnotation(Graphics2D gp2d) {
        int annotation_x_offset = -80; //The value is intentionally Negative
        int annotation_y_offset = -40;
        Position annotaionBox = new Position(upperLeft.x + x_offset * 3 + annotation_x_offset, 40);
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawRect(annotaionBox.x,annotaionBox.y,140,300);
        gp2d.drawString("annotation", annotaionBox.x + 30,annotaionBox.y + 15);
        Position p11 = new Position(670, 25);
        Position p12= new Position(950, 25);
        PositionPair anno1 = new PositionPair(p11,p12);
        PositionPair anno2 = new PositionPair(new Position(p11.x , p11.y+70), new Position(p12.x, p12.y+70));
        PositionPair anno3 = new PositionPair(new Position(p11.x , p11.y+150), new Position(p12.x, p12.y+150));

        gp2d.drawString("Inheritance", 870, 95);
        drawArrow(gp2d, anno1, true);
        drawLine(gp2d, anno1, false);
        gp2d.drawString("Realization", 870, 170);
        drawArrow(gp2d, anno2, false);
        drawLine(gp2d, anno2, true);
        gp2d.drawString("Association", 870, 245);
//        drawArrow(gp2d, anno2, false);
        drawLine(gp2d, anno3, false);
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
