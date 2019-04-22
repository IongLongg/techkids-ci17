package program.player;

import program.GameObject;
import program.GameWindow;
import program.Settings;
import program.Vector2D;
import program.renderer.AnimationRenderer;
import program.renderer.SingleImageRenderer;
import tklibs.Mathx;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {

    public Player() {
//        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
//        renderer = new AnimationRenderer("assets/images/players/straight");
//        renderer = new PlayerRenderer(this.velocity.x);
//        this.render();
        position.set(Settings.PLAYER_START_X, Settings.PLAYER_START_Y);

    }

    private void render(double x) {
        if (x == 0) {
            System.out.println("straight");
            renderer = new AnimationRenderer("assets/images/players/straight");
        } else if (x < 0) {
            System.out.println("left");
            renderer = new AnimationRenderer("assets/images/players/left");
        } else {
            System.out.println("right");
            renderer = new AnimationRenderer("assets/images/players/right");
        }
    }

    public void run() {
//        super.run();
        this.move();
        this.limitPosition();
        this.fire();
    }

    int count = 0;
    public void fire() {
        count++;
        if(GameWindow.isFirePress && count > Settings.PLAYER_FIRE_DELAY) {
            double fromX = this.position.x + 10;
            double toX = this.position.x - 10;
            double fromAngle = -Math.PI / 3;
            double toAngle = -2 * Math.PI / 3;
            int numberBullet = 5;
            for (int i = 0; i < numberBullet; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);

                double x = fromX + i * (toX - fromX) / (numberBullet - 1);
                bullet.position.set(x, this.position.y);
                double angle = fromAngle + i * (toAngle - fromAngle) / (numberBullet - 1);
                bullet.velocity.setAngle(angle);

            }
            count = 0;
        }
    }

    public void move() {
        double playerSpeed = Settings.PLAYER_SPEED;
        double vx = 0;
        double vy = 0;

        if(GameWindow.isUpPress) {
            vy -= playerSpeed;
        }
        if(GameWindow.isRightPress) {
            vx += playerSpeed;
        }
        if(GameWindow.isDownPress) {
            vy += playerSpeed;
        }
        if(GameWindow.isLeftPress) {
            vx -= playerSpeed;
        }

        if(vx != 0 && vy != 0) {
            double v = playerSpeed / Math.sqrt(2);
            vx = Math.signum(vx) * v; // 1 * 5 * sqrt(2)
            vy = Math.signum(vy) * v; // -1 * 5 * sqrt(2)
        }

        velocity.set(vx, vy);
        position.add(velocity);

        render(this.velocity.x);
//        if (this.velocity.x == 0) {
//            System.out.println("straight");
//            renderer = new AnimationRenderer("assets/images/players/straight");
//        } else if (this.velocity.x < 0) {
//            System.out.println("left");
//            renderer = new AnimationRenderer("assets/images/players/left");
//        } else {
//            System.out.println("right");
//            renderer = new AnimationRenderer("assets/images/players/right");
//        }

    }

    public void limitPosition() {
        position.setX(Mathx.clamp(position.x, 0, Settings.BACKGROUND_WIDTH - Settings.PLAYER_WIDTH));
        position.setY(Mathx.clamp(position.y, 0, Settings.GAME_HEIGHT - Settings.PLAYER_HEIGHT));
    }
}
