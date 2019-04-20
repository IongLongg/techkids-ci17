package program.player;

import program.GameObject;
import program.Settings;
import program.enemy.Enemy;
import program.physic.BoxCollider;
import program.renderer.AnimationRenderer;
import program.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

public class PlayerBullet extends GameObject {
    public int damage;

    public PlayerBullet() {
//        image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        renderer = new AnimationRenderer("assets/images/player-bullets/a");
        velocity.set(0, -Settings.P_BULLET_SPEED);
        hitBox = new BoxCollider(this, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        damage = Settings.P_BULLET_DAMAGE;
    }

    @Override
    public void run() {
        super.run();
        this.deactiveIfNeeded();
        this.intersectsEnemy();
    }

    public void intersectsEnemy() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, this.hitBox);
        if (enemy != null) {
            this.deactive();
            enemy.takeDamage(this.damage);
        }
    }

    public void deactiveIfNeeded() {
        if(position.y < -30) {
            this.deactive();
        }
    }
}
