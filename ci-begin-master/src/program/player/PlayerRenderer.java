package program.player;

import program.GameObject;
import program.Vector2D;
import program.renderer.AnimationRenderer;
import program.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerRenderer extends Renderer {
    AnimationRenderer leftAnimation;
    AnimationRenderer rightAnimation;
    AnimationRenderer straightAnimation;


    public PlayerRenderer() {
        leftAnimation = new AnimationRenderer("assets/images/players/left");
        rightAnimation = new AnimationRenderer("assets/images/players/right");
        straightAnimation = new AnimationRenderer("assets/images/players/straight");

    }

    @Override
    public void render(Graphics g, GameObject master) {
        // master = player
        // master.velocity.x >> direction
        if (master.velocity.x < 0) {
            leftAnimation.render(g, master);
        } else if (master.velocity.x > 0) {
            rightAnimation.render(g, master);
        } else {
            straightAnimation.render(g, master);
        }
    }
}
