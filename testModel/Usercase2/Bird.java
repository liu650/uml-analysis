package setting;

import model.BirdState;
import modelsetting.BirdObserver;
import modelsetting.Direction;
import modelsetting.HandleKey;
import modelsetting.PunchSkill;
import setting.exceptions.PressAtTheSameTimeException;
import setting.exceptions.UseWrongKeyException;

import java.awt.*;

@SingleElement("face.jpg")
public class Bird extends BasicElement {

    private int xpoint = 5;
    private int ypoint = 5;
    public  static final int windowHeight = 600;
    public  static final int windowWidth = 800;

    protected BirdState birdState;
    protected PunchSkill<Punch> punchSkill;

    public Bird() {
        super(200,100);
        this.birdState = new BirdState();
        this.punchSkill = new PunchSkill<>(HandleKey.ATTACK).setMaxEnergy(20f);
        addObserver(new BirdObserver());
    }

    public void beHurt() {                              // be attacked
        birdState.hurtState.max();
        birdState.hp.subtract();
    }

    private void beHurtHandle() {
        if (direction.left()) {
            this.x1 += 100;
            if (x1 >= Bird.windowWidth) {
                this.x1 = Bird.windowWidth;
            }
        } else {
            this.x1 -= 100;
            if (x1 <= 0) {
                this.x1 = 0;
            }
        }
        birdState.hurtState.subtract();
    }

    @Override
    public void action() {
        if (birdState.hurtState.health()) {
            this.beHurtHandle();
            return;
        }
        this.punchSkill.action(() -> new Punch(this));
        super.action();
    }

    public void wrongKeyPressed() throws UseWrongKeyException {
        if (!HandleKey.LEFT.keyUsed() || !HandleKey.RIGHT.keyUsed()
                || !HandleKey.UP.keyUsed() || !HandleKey.DOWN.keyUsed() || !HandleKey.ATTACK.keyUsed()) {
            throw new UseWrongKeyException();
        }
    }

    public void pressTwoAtSameTime() throws PressAtTheSameTimeException {
        if (HandleKey.LEFT.keyUsed() && HandleKey.RIGHT.keyUsed() || HandleKey.UP.keyUsed()
                && HandleKey.DOWN.keyUsed() || HandleKey.LEFT.keyUsed() && HandleKey.UP.keyUsed()
                || HandleKey.RIGHT.keyUsed() && HandleKey.UP.keyUsed()
                || HandleKey.LEFT.keyUsed() && HandleKey.DOWN.keyUsed()
                || HandleKey.RIGHT.keyUsed() && HandleKey.DOWN.keyUsed()) {
            throw new PressAtTheSameTimeException();
        }
    }

    @Override
    public void xmove() {         //set moving action
        if (HandleKey.LEFT.keyUsed()) {
            notifyObservers(direction);
            if (x1 > 0) {
                this.x1 -= xpoint;
            }
            direction = Direction.LEFT;
        } else if (HandleKey.RIGHT.keyUsed()) {
            notifyObservers(direction);
            if (x1 < windowWidth) {
                this.x1 += xpoint;
            }
            direction = Direction.RIGHT;
        }
    }

    @Override
    public void ymove() {
        if (HandleKey.UP.keyUsed()) {
            notifyObservers(direction);
            if (y1 > 0) {
                this.y1 -= ypoint;
            }
            direction = Direction.UP;
        } else if (HandleKey.DOWN.keyUsed()) {
            notifyObservers(direction);
            if (y1 < windowHeight) {
                this.y1 += ypoint;
            }
            direction = Direction.DOWN;
        }
    }

    @Override
    public void drawImage(Graphics g) {
        super.drawImage(g);
    }

    @Override
    protected Image getImage() {
        return this.birdState.getImage(this);
    }

    public BirdState getBirdState() {
        return birdState;
    }

    public int getXpoint() {
        return xpoint;
    }

    public int getYpoint() {
        return ypoint;
    }

}

