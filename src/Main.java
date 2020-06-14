import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
//    public List<Box> boxes = new ArrayList<>();
//    public List<Arrow> arrows = new ArrayList<>();
    //sudo position

    public static void main(String[] args) {
        initialize();
        try{
            cleanUpJavaCache();
        } catch (Exception e){

        }
    }

    private static void initialize(){
        final int numberOfBox = 9; // should not be modified
        ArrayList<Position> classPositions = DiagramGenerator.findAllPosition(new Position(250, 170), 200, 210);

        //TODO: Box missing  !!!field and !!!method information
        List<Box> boxes = getBoxes(classPositions, numberOfBox);

        List<Arrow> arrows = new ArrayList<>();
        JFrame jFrame = new JFrame(); //initialize
        jFrame.setSize(1000, 770);// set size of window
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set open-close model
        jFrame.setContentPane(new DiagramGenerator(boxes, arrows));// set content
        jFrame.setTitle("Sample Graph");//set title
        jFrame.setVisible(true);// framework is visible
        jFrame.setLocationRelativeTo(null);// the window at middle of the screen
    }

    private static List<Box> getBoxes(ArrayList<Position> classPositions, int numberOfBox) {
        List<Box> boxes = new ArrayList<>();
        for (int i = classPositions.size() - numberOfBox; i < classPositions.size(); i++){
            int boxIndex =  i + numberOfBox - classPositions.size();
            boxes.add(new Box("Class " + Integer.toString(boxIndex), classPositions.get(i)));
        }
        return boxes;
    }

    private static void cleanUpJavaCache() throws IOException {
        String productionPath =  "./out/production";
        if (new File(productionPath).exists()) {
            Runtime.getRuntime().exec("rm -r " + productionPath);
            System.out.println("Java cache is removed successfully.");
        }
    }
}
