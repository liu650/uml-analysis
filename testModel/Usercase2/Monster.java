package setting;


import modelsetting.Direction;
import picsetting.ImagePath;
import setting.BasicElement;
import setting.Bird;
import setting.Construction;
import setting.SingleElement;

import java.awt.*;


public abstract class Monster extends BasicElement {

    protected Construction hp;
    protected Construction armor;
    protected Construction death = new Construction(0, 100);
    protected Image deathImage = ImagePath.getImage("DIE.gif");

    public Monster(int x, int y) {
        super(x,y);
        SingleElement se = this.getClass().getAnnotation(SingleElement.class);
        hp = new Construction(se.hp());
        armor = new Construction(se.armor());
    }

    public void beHurt(int attack, int hurtBack) {      //be attacked
        int hurtValue = attack - armor.getInitialValue();
        if (hurtValue <= 0) {
            return;
        }
        this.x1 += hurtBack;
        this.hp.subtract(hurtValue);
        if (!this.hp.health()) {
            this.death.max();
        }
    }

    @Override
    public boolean beforeActionJudge() {   //before action
        if (death.health()) {
            death.subtract();
            return false;
        }
        return true;
    }

    @Override
    public void xmove() {                //x-Moving
        if (direction.left()) {
            this.x1 -= 2;
            if (x1 <= 0) {
                this.direction = Direction.RIGHT;
            }

        } else if (direction.right()) {
            this.x1 += 2;
            if (x1 >= Bird.windowWidth) {
                this.direction = Direction.LEFT;
            }
        }
    }

    @Override
    public boolean attackBird(Bird bird) {       //encounter player
        if (this.intersects(bird) && this.beforeActionJudge()) {
            bird.beHurt();
        }
        return super.attackBird(bird);
    }

    @Override
    public boolean remove(Bird bird) {
        if (!hp.health() && !death.health()) {
            return true;
        }
        return false;
    }

    @Override
    protected Image getImage() {
        if (!hp.health() && !death.health()) {
            return deathImage;
        }
        return super.getImage();
    }
}
