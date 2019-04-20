package program.enemy;

import program.GameObject;
import program.Settings;
import program.physic.BoxCollider;
import program.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

public class Enemy extends GameObject {
    public int hp;

    public Enemy() {
//        image = SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png");
        renderer = new AnimationRenderer("assets/images/enemies/level0/black");
        position.set(200,150);
        hitBox = new BoxCollider(this, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
        hp = Settings.ENEMY_BLACK_HP;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }
}
