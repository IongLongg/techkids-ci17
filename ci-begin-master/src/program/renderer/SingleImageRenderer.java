package program.renderer;

import program.GameObject;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleImageRenderer extends Renderer{
    BufferedImage image;

    public SingleImageRenderer(BufferedImage image) {
        this.image = image;
    }

    public SingleImageRenderer(String filePath) {
        this.image = SpriteUtils.loadImage(filePath);
    }

    @Override
    public void render(Graphics g, GameObject master) {
        g.drawImage(
                image,
                (int) master.position.x,
                (int) master.position.y,
                null
        );
    }
}
