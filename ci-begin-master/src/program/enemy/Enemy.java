package program.enemy;

import program.GameObject;
import program.Settings;
import program.physic.BoxCollider;
import program.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class Enemy extends GameObject {
    public int hp;

    public Enemy() {
//        image = SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png");
        renderer = new AnimationRenderer("assets/images/enemies/level0/black");
        position.set(-30, -30);
        hitBox = new BoxCollider(this, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
        hp = Settings.ENEMY_BLACK_HP;
        velocity.set(0, 3);
        velocity.setAngle(Math.PI / 12);
    }

    @Override
    public void run() {
        super.run();
        this.move();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.drawString(
                hp + "",
                (int) this.position.x - 15,
                (int) this.position.y - 15
        );
    }

    @Override
    public void reset() {
        super.reset(); // active = true
        position.set(-30, -30);
        velocity.set(0, 3);
        velocity.setAngle(Math.PI / 12);
        hp = Settings.ENEMY_BLACK_HP;
    }

    public void move() {
        if (this.outBoundRight() && this.onGoingRight()) {
            this.reverseVelocityX();
        }
        if (this.outBoundLeft() && this.onGoingLeft()) {
            this.reverseVelocityX();
        }
    }

    private boolean outBoundRight() {
        return this.position.x >= Settings.BACKGROUND_WIDTH - this.anchor.x * Settings.ENEMY_WIDTH;
    }
    private boolean outBoundLeft() {
        return this.position.x <= this.anchor.x * Settings.ENEMY_WIDTH;
    }
    private boolean onGoingRight() {
        return this.velocity.x > 0;
    }
    private boolean onGoingLeft() {
        return this.velocity.x < 0;
    }

    private void reverseVelocityX() {
        this.velocity.setX(-this.velocity.x);
    }


    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }
}
