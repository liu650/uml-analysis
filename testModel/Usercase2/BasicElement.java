package setting;

import modelsetting.Direction;
import picsetting.DrawingPic;
import picsetting.ImagePath;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Observable;

public abstract class BasicElement extends Observable implements DrawingPic {

    protected Image rightImage;
    protected Image leftImage;
    protected int x1;
    protected  int y1;
    protected  int width;
    protected int height;
    protected Direction direction;
    protected float yspeed;
    protected float xspeed;

    public BasicElement(int x, int y) {
        SingleElement se = getClass().getAnnotation(SingleElement.class);
        this.x1 = x;
        this.y1 = y;
        this.width = 60;
        this.height = 60;
        this.direction = se.direction();
        String imageName = se.value().equals(SingleElement.NOTHING) ? se.leftImage() : se.value();
        leftImage = ImagePath.getImage(imageName);
        rightImage = se.rightImage().equals(SingleElement.NOTHING) ? leftImage : ImagePath.getImage(se.rightImage());
    }

    public void action() {
        xmove();
        ymove();
    }

    public boolean beforeActionJudge() {
        return true;
    }

    protected void xmove() {
        x1 += direction.right() ? xspeed : -xspeed;
    }

    protected void ymove() {
        y1 += direction.down() ? yspeed : - yspeed;
    }

    public boolean attackBird(Bird bird) {
        return false;
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.x1,this.y1,this.width,this.height);
    }

    public <E extends BasicElement> boolean intersects(E character) {

        return this.getRectangle().intersects(character.getRectangle());
    }

    @Override
    public void drawImage(Graphics g) {

        g.drawImage(this.getImage(),this.x1,this.y1,this.width,this.height,null);
    }

    protected Image getImage() {

        return direction.right() ? rightImage : leftImage;
    }

    public boolean remove(Bird bird) {
        return false;
    }

    protected void loading() {
        try {
            BufferedReader b = new BufferedReader(new FileReader("saveFile.txt"));
            x1 = Integer.parseInt(b.readLine());
            y1 = Integer.parseInt(b.readLine());
            b.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("saveFile.txt"));
            bw.write(x1);
            bw.newLine();
            bw.write(y1);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public int getX() {
        return x1;
    }

    public int getY() {
        return y1;
    }

    public float getXspeed() {
        return xspeed;
    }

    public Direction getDirection() {
        return direction;
    }
}
