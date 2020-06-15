package setting;

import setting.BasicElement;
import setting.Bird;
import setting.SingleElement;

@SingleElement(leftImage = "rightPunch.jpg",
               rightImage = "punch.jpg")
public class Punch extends BasicElement {
    private Bird bird;
    private int count;

    public Punch(Bird bird) {

        super(bird.getX(), bird.getY());
        direction = bird.getDirection();
        this.direction = bird.getDirection();
        float energy = bird.punchSkill.getEnergy();
        this.xspeed = energy > 5 ? 8 : energy;
        this.bird = bird;
    }

    @Override
    public void action() {
        this.y1 = bird.getY();
        if (count == 0) {
            this.x1 = bird.getX();
        } else if (count < 10) {
            this.x1 += direction.right() ? xspeed + bird.getXspeed() : -xspeed - bird.getXspeed();
        } else {
            this.x1 += direction.right() ? -xspeed - bird.getXspeed() : xspeed + bird.getXspeed();
        }
        count++;
    }

    @Override
    public boolean remove(Bird bird) {
        if (count > 18) {
            return true;
        }
        if (!direction.equals(bird.getDirection())) {
            return true;
        }
        if (direction.right()) {
            return this.x1 < bird.getX() - bird.getXspeed();
        } else {
            return this.x1 > bird.getX() + bird.getXspeed();
        }
    }

    public int getAttack() {
        return bird.birdState.attackDamage.getInitialValue();
    }
}
