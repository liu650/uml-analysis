import java.util.ArrayList;

public class PositionPair {
    private Position p1;
    private Position p2;
    private Position edgeP1 = null;
    private Position edgeP2 = null;
    private int width = DiagramGenerator.BOX_WIDTH;
    private int height = DiagramGenerator.BOX_HEIGHT;


    PositionPair(Position p1, Position p2){
        this.p1 = p1;
        this.p2 = p2;
        calculateEdge();
    }

    public Position getEdgeP1(){
        return edgeP1;
    }

    public Position getEdgeP2(){
        return edgeP2;
    }

    private void calculateEdge(){
        Position p1Center = new Position(p1.x + 1* width/2, p1.y + 1 * height/2);
        double minSquareDistance = getSquareDistance(p1, p2);
        Position bestP1 = p1, bestP2 = p2;
        for (Position edge1: getAllEdge(p1)){
            for (Position edge2: getAllEdge(p2)){
                Double curSquareDistance = getSquareDistance(edge1, edge2);
                if (curSquareDistance < minSquareDistance || (curSquareDistance == minSquareDistance
                        && getSquareDistance(edge1, p1Center) < getSquareDistance(bestP1, p1Center))){
                    //if tie, prefer the middle edge than others
                    bestP1 = edge1;
                    bestP2 = edge2;
                    minSquareDistance = curSquareDistance;
                }
            }
        }
        edgeP1 = bestP1;
        edgeP2 = bestP2;
    }

    private ArrayList<Position> getAllEdge(Position position){
        ArrayList<Position> res = new ArrayList<>();
        for (int xMultiplier = 0; xMultiplier < 3; xMultiplier++){
            for (int yMultiplier = 0; yMultiplier < 3; yMultiplier++){
                res.add(new Position(position.x + xMultiplier * width/2, position.y + yMultiplier * height/2));
            }
        }
        return res;
    }

    // get the shortest distance between two positions
    private double getSquareDistance(Position point1, Position point2){
        return Math.pow(point1.x-point2.x, 2) +  Math.pow(point1.y-point2.y, 2);
    }
}
