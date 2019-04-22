package program.player;

import program.GameObject;
import program.Vector2D;
import program.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerRenderer extends Renderer {
    ArrayList<BufferedImage> images;
    int currentImageIndex;

    public PlayerRenderer(double x) {
        images = SpriteUtils.loadImages(this.renderIf(x));
        currentImageIndex = 0;
    }

    int count = 0;
    @Override
    public void render(Graphics g, GameObject master) {
        BufferedImage currentImage = images.get(currentImageIndex);
        g.drawImage(
                currentImage,
                (int) master.position.x,
                (int) master.position.y,
                null
        );
        count++;
        if (count > 15) {
            currentImageIndex++;
            if (currentImageIndex >= images.size()) {
                currentImageIndex = 0;
            }
            count = 0;
        }
    }

    public String renderIf (double x) {
        if (x == 0) {
            return "assets/images/players/straight";
        } else if (x > 0) {
            return "assets/images/players/right";
//            System.out.println("right");
        } else {
            return "assets/images/players/left";
//            System.out.println("left");
        }
    }
}
