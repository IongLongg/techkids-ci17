package program.physic;

import program.GameObject;
import program.Vector2D;

public class BoxCollider extends Rectangle {
    Vector2D anchor;

    public BoxCollider(GameObject object, int width, int height) {
        super(object.position, width, height);
        this.anchor = object.anchor;
    }

    @Override
    public double top() {
        return this.position.y - this.anchor.y * this.height;
    }
    @Override
    public double left() {
        return this.position.x - this.anchor.x * this.width;
    }

}
