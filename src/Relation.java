import java.util.ArrayList;

public class Relation {

    private PositionPair positionPair;
    private int classIndex1;
    private int classIndex2;
    private DependEnum dependEnum;
    private ArrowEnum arrowType;
    private boolean isDashed;
    private ArrayList<Position> localPositions = DiagramGenerator.findAllPosition(
            DiagramGenerator.UPPER_LEFT, DiagramGenerator.X_OFFSET, DiagramGenerator.Y_OFFSET);

    public Relation(int classIndex1, int classIndex2, DependEnum dependEnum){
        this.classIndex1 = classIndex1;
        this.classIndex2 = classIndex2;
        this.dependEnum = dependEnum;
        setArrowType();
    }

    private void setArrowType() {
        if (dependEnum.equals(DependEnum.REALIZATION)){
            arrowType = ArrowEnum.EMPTY_TRIANGLE;
            isDashed = true;
        } else if (dependEnum.equals(DependEnum.INHERITANCE)){
            arrowType = ArrowEnum.SOLID_TRIANGLE;
            isDashed = false;
        } else if (dependEnum.equals(DependEnum.IMPORT)){
            arrowType = ArrowEnum.DEFAULT;
            isDashed = true;
        } else{ // association
            arrowType = ArrowEnum.DEFAULT;
            isDashed = false;
        }
    }

    private void calculatePositionPair() {
        positionPair = new PositionPair(localPositions.get(classIndex1), localPositions.get(classIndex2));
    }

    public PositionPair getPositionPair() {
        calculatePositionPair();
        return positionPair;
    }

    public int getClassIndex1() {
        return classIndex1;
    }

    public int getClassIndex2(){
        return classIndex2;
    }

    public DependEnum getDependEnum() {
        return dependEnum;
    }

    public boolean getIsDashed(){
        return isDashed;
    }
    public ArrowEnum getArrowType(){
        return arrowType;
    }

}
