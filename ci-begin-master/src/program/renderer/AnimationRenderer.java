package program.renderer;

import program.GameObject;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationRenderer extends Renderer{
    ArrayList<BufferedImage> images;
    int currentImageIndex;

    public AnimationRenderer(String directoryPath) {
        images = SpriteUtils.loadImages(directoryPath);
        currentImageIndex = 0;
    }

    int count = 0;
    @Override
    public void render(Graphics g, GameObject master) {
        BufferedImage currentImage = images.get(currentImageIndex);
        g.drawImage(
                currentImage,
                (int) (master.position.x - master.anchor.x * currentImage.getWidth()),
                (int) (master.position.y - master.anchor.y * currentImage.getHeight()),
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
        if (master.hitBox != null) {
            this.drawHitBox(g, master);
        }
    }

    private void drawHitBox(Graphics g, GameObject master) {
        g.setColor(Color.cyan);
        g.drawRect(
                (int) master.hitBox.left(),
                (int) master.hitBox.top(),
                master.hitBox.width,
                master.hitBox.height
        );
    }
}
