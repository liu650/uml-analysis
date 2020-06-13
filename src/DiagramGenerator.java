import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class DiagramGenerator extends JPanel {
    //1 2 3
    //7 9 8
    //4 5 6
    public static Position UL= new Position(50,30);public static Position UM= new Position(350,30);public static Position UR= new Position(600,30);
    public static Position ML= new Position(50,300);public static Position MM= new Position(350,300);public static Position MR= new Position(600,300);
    public static Position DL= new Position(50,550);public static Position DM= new Position(350,550);public static Position DR= new Position(600,550);
    //Edge endPoint
    public static Position ULE= new Position(250,122);public static Position UME= new Position(450,214);public static Position URE= new Position(600,122);
    public static Position MLE= new Position(250,390);public static Position MME= new Position(450,392);public static Position MRE= new Position(600,392);
    public static Position DLE= new Position(250,642);public static Position DME= new Position(450,550);public static Position DRE= new Position(600,642);
    public List<Box> iboxes = new ArrayList<>();
    public List<Arrow> iarrows = new ArrayList<>();

     DiagramGenerator(List<Box> boxes, List<Arrow> arrows){
        this.iboxes = boxes;
        this. iarrows = arrows;
    }

    //1 2 3
    //7 9 8
    //4 5 6
    public void paint (Graphics gp) {
         Box currBox;//keep track of the current box
        //painter--set color before using
        BasicStroke bs = new BasicStroke( 10.1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);
        super.paint(gp);
        Graphics2D gp2d = (Graphics2D) gp;



        //draw a class(box)
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(UL.x,UL.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.1", UL.x+15,UL.y+15);
        gp2d.drawLine(UL.x,UL.y+20, UL.x+200, UL.y+20);
        gp2d.drawString("field", UL.x+15,UL.y+35);
        gp2d.drawLine(UL.x,UL.y+90, UL.x+200, UL.y+90);
        gp2d.drawString("method", UL.x+15,UL.y+100);
        //draw line/arrow
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawLine(MM.x+100,MM.y+94, ULE.x,ULE.y);



        //draw a class(box)
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(UM.x,UM.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.2", UM.x+15,UM.y+15);
        gp2d.drawLine(UM.x,UM.y+20, UM.x+200, UM.y+20);
        gp2d.drawString("field", UM.x+15,UM.y+35);
        gp2d.drawLine(UM.x,UM.y+90, UM.x+200, UM.y+90);
        gp2d.drawString("method", UM.x+15,UM.y+100);
        //draw line/arrow
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawLine(MM.x+100,MM.y+94,UME.x,UME.y);

        //draw a class(box)
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(UR.x,UR.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.3", UR.x+15,UR.y+15);
        gp2d.drawLine(UR.x,UR.y+20, UR.x+200, UR.y+20);
        gp2d.drawString("field", UR.x+15,UR.y+35);
        gp2d.drawLine(UR.x,UR.y+90, UR.x+200, UR.y+90);
        gp2d.drawString("method", UR.x+15,UR.y+100);
        //draw line/arrow
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawLine(MM.x+100,MM.y+94,URE.x,URE.y);


        //draw a class(box)
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(MR.x,MR.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.8", MR.x+15,MR.y+15);
        gp2d.drawLine(MR.x,MR.y+20, MR.x+200, MR.y+20);
        gp2d.drawString("field", MR.x+15,MR.y+35);
        gp2d.drawLine(MR.x,MR.y+90, MR.x+200, MR.y+90);
        gp2d.drawString("method", MR.x+15,MR.y+100);
        //draw line/arrow
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawLine(MM.x+100,MM.y+94,MRE.x,MRE.y);




        //draw a class(box)
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(DL.x,DL.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.4", DL.x+15,DL.y+15);
        gp2d.drawLine(DL.x,DL.y+20, DL.x+200, DL.y+20);
        gp2d.drawString("field", DL.x+15,DL.y+35);
        gp2d.drawLine(DL.x,DL.y+90, DL.x+200, DL.y+90);
        gp2d.drawString("method", DL.x+15,DL.y+100);
        //draw line/arrow
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawLine(MM.x+100,MM.y+94,DLE.x,DLE.y);


        //draw a class(box)
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(DM.x,DM.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.5", DM.x+15,DM.y+15);
        gp2d.drawLine(DM.x,DM.y+20, DM.x+200, DM.y+20);
        gp2d.drawString("field", DM.x+15,DM.y+35);
        gp2d.drawLine(DM.x,DM.y+90, DM.x+200, DM.y+90);
        gp2d.drawString("method", DM.x+15,DM.y+100);
        //draw line/arrow
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawLine(MM.x+100,MM.y+94,DME.x,DME.y);


        //draw a class(box)
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(DR.x,DR.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.6", DR.x+15,DR.y+15);
        gp2d.drawLine(DR.x,DR.y+20, DR.x+200, DR.y+20);
        gp2d.drawString("field", DR.x+15,DR.y+35);
        gp2d.drawLine(DR.x,DR.y+90, DR.x+200, DR.y+90);
        gp2d.drawString("method", DR.x+15,DR.y+100);
        //draw line/arrow
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawLine(MM.x+100,MM.y+94,DRE.x,DRE.y);


        //draw a class(box)
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.fillRoundRect(ML.x,ML.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.7", ML.x+15,ML.y+15);
        gp2d.drawLine(ML.x,ML.y+20, ML.x+200, ML.y+20);
        gp2d.drawString("field", ML.x+15,ML.y+35);
        gp2d.drawLine(ML.x,ML.y+90, ML.x+200, ML.y+90);
        gp2d.drawString("method", ML.x+15,ML.y+100);
        //draw line/arrow
        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawLine(MM.x+100,MM.y+94,MLE.x,MLE.y);

        gp2d.setColor(Color.DARK_GRAY);
        gp2d.drawRect(820,40,140,300);
        gp2d.drawString("annotaion", 835,55);
        //draw a class(box)
        gp2d.fillRoundRect(MM.x,MM.y, 200,184,20,20);
        gp2d.setColor(Color.WHITE);
        gp2d.drawString("Class.9", MM.x+15,MM.y+15);
        gp2d.drawLine(MM.x,MM.y+20, MM.x+200, MM.y+20);
        gp2d.drawString("field", MM.x+15,MM.y+35);
        gp2d.drawLine(MM.x,MM.y+90, MM.x+200, MM.y+90);
        gp2d.drawString("method", MM.x+15,MM.y+100);


    }


}
