package policewarrior;

import processing.core.PImage;

public class Enemy extends Entity {
    public Enemy(PImage[] idle, int x, int y, int HP, int ATK, int DEF) {
        super(HP, ATK, DEF, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 1;
        super.reset_frame = -1;
    }

    public Enemy(PImage[] idle, int x, int y) {
        super(0, 0, 0, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 1;
        super.reset_frame = -1;
    }
}
