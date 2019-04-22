package program.enemy;

import program.GameObject;

public class EnemySummoner extends GameObject {
    public EnemySummoner() {

    }

    @Override
    public void run() {
        super.run();
        this.summonEnemy();
    }

    int count = 0;
    private void summonEnemy() {
        count++;
        if (count > 120) {
            GameObject.recycle(Enemy.class); // (-30, -30)
            count = 0;
        }
    }
}
