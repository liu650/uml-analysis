import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DiagramGenerator extends JPanel {
    final static int X_OFFSET = 300, Y_OFFSET = 260;
    final static int BOX_WIDTH = 200, BOX_HEIGHT = 184;
    final static Position UPPER_LEFT = new Position(30, 30);
    private static int arrow_length = 20;

    public ArrayList<MyClass> allClasses;

    Stroke solidLine = new BasicStroke( 1.1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);
    Stroke dashedLine = new BasicStroke(1.1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, new float[]{9}, 0);

    DiagramGenerator(ArrayList<MyClass> givenClasses){
        this.allClasses = classSelector(givenClasses);
    }

    // find the class with most relations and set as target, and select classes that has relation with target
    private ArrayList<MyClass> classSelector(ArrayList<MyClass> givenClasses) {
        givenClasses.sort(new Comparator<MyClass>() {
            @Override
            public int compare(MyClass o1, MyClass o2) {
                int relationCount1 = (o1.getExtendedList().size() +o1.getImplementedList().size()) * 10
                        + o1.getImportList().size();
                int relationCount2 = (o2.getExtendedList().size() +o2.getImplementedList().size()) * 10
                        + o2.getImportList().size();
                return relationCount2 - relationCount1;
            }
        });

        MyClass target = givenClasses.get(0);
        ArrayList<MyClass> res = new ArrayList<>(), backup = new ArrayList<>();
        int cur = 1;
        while(res.size() < 8 && cur < givenClasses.size()){
            if (target.hasRelation(givenClasses.get(cur).getClassName())){
                res.add(givenClasses.get(cur++));
            } else {
                backup.add(givenClasses.get(cur++));
            }
        }
        cur = 0; //reset counter
        while (res.size() < Math.min(givenClasses.size() - 1, 9 - 1) && cur < backup.size()){
            res.add(backup.get(cur++));
        }

        res.add(target);

        return res;
    }

    public void paint (Graphics gp) {
        int numberOfBox = Math.min(allClasses.size(), 9); // support up to 9 classes
        ArrayList<Position> localPositions = findAllPosition(UPPER_LEFT, X_OFFSET, Y_OFFSET);

        super.paint(gp);
        Graphics2D gp2d = (Graphics2D) gp;
        gp2d.setStroke(solidLine);

        // draw all the classes
        for (int i = localPositions.size() - numberOfBox; i < localPositions.size(); i++){
            int boxIndex =  i + numberOfBox - localPositions.size();
            MyClass currentClass = allClasses.get(boxIndex);
            drawClass(gp2d, localPositions.get(i), currentClass);
        }

        // draw all relations for target class, which is always the class in the center
        MyClass target = allClasses.get(Math.min(numberOfBox-1, 8));
        ArrayList<Relation> relations = generateRelationList(numberOfBox, target);

        for (Relation relation: relations){
            drawRelation(gp2d, relation);
        }

//        testRelations(gp2d, localPositions, numberOfBox);

        drawAnnotation(gp2d);
    }

    private void drawRelation(Graphics2D gp2d, Relation relation) {
        if (!relation.getArrowType().equals(ArrowEnum.NULL)){
            drawArrow(gp2d, relation.getPositionPair(), relation.getArrowType());
        }
        drawLine(gp2d, relation.getPositionPair(), relation.getIsDashed());
    }

    private ArrayList<Relation> generateRelationList(int numberOfBox, MyClass target) {
        HashMap<DependEnum, ArrayList<String>> rawRelationList = new HashMap<>();
        rawRelationList.put(DependEnum.REALIZATION, target.getImplementedList());
        rawRelationList.put(DependEnum.IMPORT, target.getImportList());
        rawRelationList.put(DependEnum.INHERITANCE, target.getExtendedList());

        ArrayList<Relation> relationList = new ArrayList<>();
        for (Map.Entry<DependEnum, ArrayList<String>> entry: rawRelationList.entrySet()){
            DependEnum enumKey = entry.getKey();
            for (String className2: entry.getValue()){
                int c2Index = allClasses.indexOf(new MyClass(className2));
                if (c2Index >= 0 && c2Index < Math.min(numberOfBox, allClasses.size())){
                    // only consider classes in the range
                    relationList.add(new Relation(Math.min(numberOfBox, allClasses.size()) - 1,
                            c2Index, enumKey));
                }
            }
        }
        return relationList;
    }

    private void testRelations(Graphics2D gp2d, ArrayList<Position> localPositions, int numberOfBox) {
        for (int i = localPositions.size() - numberOfBox; i < localPositions.size() - 1; i++){
            drawArrow(gp2d, new PositionPair(localPositions.get(8), localPositions.get(i)),
                    i%3 == 0 ? ArrowEnum.SOLID_TRIANGLE : (i%3 == 1 ? ArrowEnum.EMPTY_TRIANGLE : ArrowEnum.DEFAULT));
            drawLine(gp2d, new PositionPair(localPositions.get(8), localPositions.get(i)), i%2 == 0 ? true : false);
        }
    }

    protected void drawArrow(Graphics2D gp2d, PositionPair pair, ArrowEnum arrowEnum){
        Position p1 = pair.getEdgeP1(), p2 = pair.getEdgeP2();
        int xSign = p1.x > p2.x? 1: -1;
        int ySign = p1.y > p2.y? 1: -1;
        int[] xPoints = new int[3], yPoints = new int[3];
        xPoints[0] = p2.x;
        yPoints[0] = p2.y;

        // calculate the position for triangle
        if (p1.x == p2.x && p1.y == p2.y){
            //ignore if a position is pointed to itself
        } else if (p1.y == p2.y){
            // handle divide by zero
            xPoints[1] = p2.x + xSign * (int)(arrow_length * Math.cos(Math.toRadians(30)));
            xPoints[2] = p2.x + xSign * (int)(arrow_length * Math.cos(Math.toRadians(30)));
            yPoints[1] = p2.y - (int)(arrow_length * Math.sin(Math.toRadians(30)));
            yPoints[2] = p2.y + (int)(arrow_length * Math.sin(Math.toRadians(30)));
        } else {
            double angle1 = Math.atan(Math.abs(p2.x-p1.x)/Math.abs(p2.y-p1.y)) - Math.toRadians(30);
            double angle2 = Math.toRadians(120) - angle1;
            xPoints[1] = p2.x + xSign * (int)(arrow_length * Math.sin(angle1));
            xPoints[2] = p2.x + xSign * (int)(arrow_length * Math.sin(angle2));
            yPoints[1] = p2.y + ySign * (int)(arrow_length * Math.cos(angle1));
            yPoints[2] = p2.y - ySign * (int)(arrow_length * Math.cos(angle2));
        }

        // draw a small triangle near the end of p2
        gp2d.setColor(Color.DARK_GRAY);
        if (arrowEnum.equals(ArrowEnum.SOLID_TRIANGLE)){
            gp2d.fillPolygon(xPoints, yPoints, 3);
        } else if (arrowEnum.equals(ArrowEnum.EMPTY_TRIANGLE)){
            gp2d.drawPolygon(xPoints, yPoints, 3);
        } else {            //ArrowEnum.DEFAULT
            gp2d.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
            gp2d.drawLine(xPoints[0], yPoints[0], xPoints[2], yPoints[2]);
        }
    }

    protected void drawLine(Graphics2D gp2d, PositionPair pair, boolean isDashed){
        Position p1 = pair.getEdgeP1(), p2 = pair.getEdgeP2();
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.setStroke(isDashed ? dashedLine : solidLine);
        gp2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        gp2d.setStroke(solidLine); // revert any possible change to stroke, to avoid further influence to code
    }

    protected void drawClass(Graphics2D gp2d, Position classPosition, MyClass currentClass) {
        int maxFields = 6;
        int maxMethods = 8;
        int lineHeight = 11;

        // handle class name
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(classPosition.x, classPosition.y, BOX_WIDTH, BOX_HEIGHT, 20, 20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString(currentClass.getClassName(), classPosition.x + 15, classPosition.y + 15);
        gp2d.drawLine(classPosition.x, classPosition.y + 20, classPosition.x + 200, classPosition.y + 20);

        // handle class fields
        ArrayList<Field> fields = currentClass.getFields();
        for (int i = 0; i < Math.min(maxFields, fields.size()); i++){
            gp2d.drawString(fields.get(i).toString(),
                    classPosition.x + 15, classPosition.y + 35 + i * lineHeight);
        }

        // handle class methods
        ArrayList<Method> methods = currentClass.getMethods();
        gp2d.drawLine(classPosition.x, classPosition.y + 90, classPosition.x + 200, classPosition.y + 93);
        for (int i = 0; i < Math.min(maxMethods, methods.size()); i++){
            gp2d.drawString(methods.get(i).toString(),
                    classPosition.x + 15, classPosition.y + 104 + i * lineHeight);
        }
    }

    protected void drawAnnotation(Graphics2D gp2d) {
        int annotation_x_offset = -80; //The value is intentionally Negative
        Position annotaionBox = new Position(UPPER_LEFT.x + X_OFFSET * 3 + annotation_x_offset, 40);
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawRect(annotaionBox.x,annotaionBox.y,140,350);
        gp2d.drawString("annotation", annotaionBox.x + 30,annotaionBox.y + 15);

        Position p11 = new Position(670, 25);
        Position p12= new Position(950, 25);
        PositionPair anno1 = new PositionPair(p11,p12);
        PositionPair anno2 = new PositionPair(new Position(p11.x , p11.y+70), new Position(p12.x, p12.y+70));
        PositionPair anno3 = new PositionPair(new Position(p11.x , p11.y+150), new Position(p12.x, p12.y+150));
        PositionPair anno4 = new PositionPair(new Position(p11.x , p11.y+150 + 80), new Position(p12.x, p12.y+150+80));

        gp2d.drawString("Inheritance", 870, 95);
        drawArrow(gp2d, anno1, ArrowEnum.SOLID_TRIANGLE);
        drawLine(gp2d, anno1, false);
        gp2d.drawString("Realization", 870, 95 + 75*1);
        drawArrow(gp2d, anno2, ArrowEnum.EMPTY_TRIANGLE);
        drawLine(gp2d, anno2, true);
        gp2d.drawString("Import", 870, 95 + 75*2);
        drawArrow(gp2d, anno3, ArrowEnum.DEFAULT);
        drawLine(gp2d, anno3, true);
        gp2d.drawString("Association", 870, 95 + 75*3);
        drawLine(gp2d, anno4, false);

    }

    //0 1 2
    //6 8 7
    //3 4 5
    //Find all the position using the order above
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
