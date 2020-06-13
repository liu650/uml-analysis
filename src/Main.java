import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Main {
//    public List<Box> boxes = new ArrayList<>();
//    public List<Arrow> arrows = new ArrayList<>();
    //sudo position
    public static Position UL= new Position(250,170);public static Position UM= new Position(450,170);public static Position UR= new Position(650,170);
    public static Position ML= new Position(250,380);public static Position MM= new Position(450,380);public static Position MR= new Position(650,380);
    public static Position DL= new Position(250,590);public static Position DM= new Position(450,590);public static Position DR= new Position(650,590);



    private static void initialize(){
        //Sodo Boxes 3*3
         Box box1 = new Box("Class1", UL);  Box box2 = new Box("Class2", UM); Box box3 = new Box("Class3", UR);
         Box box4 = new Box("Class4", ML);  Box box5 = new Box("Class5", MM); Box box6 = new Box("Class6", MR);
         Box box7 = new Box("Class7", DL); Box box8 = new Box("Class8", DM); Box box9 = new Box("Class9", DR);
         //TODO: Box missing  !!!field and !!!method information
         List<Box> boxes = new ArrayList<>();
         boxes.add(box1); boxes.add(box2); boxes.add(box3);
         boxes.add(box4); boxes.add(box5); boxes.add(box6);
         boxes.add(box7); boxes.add(box7); boxes.add(box9);
         List<Arrow> arrows = new ArrayList<>();
        JFrame jFrame = new JFrame(); //initialize
        jFrame.setSize(990, 770);// set size of window
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set open-close model
        jFrame.setContentPane(new DiagramGenerator(boxes, arrows));// set content
        jFrame.setTitle("Sample Graph");//set title
        jFrame.setVisible(true);// framework is visible
        jFrame.setLocationRelativeTo(null);// the window at middle of the screen
    }

    public static void main(String[] args) {
        initialize();

    }



}
