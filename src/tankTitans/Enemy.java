package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy extends Entity implements SpriteAnimation {

    public Enemy(PImage[] idle, int x, int y, int res, int HP, int ATK, int DEF) {
        super(HP, ATK, DEF, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 4;
        super.reset_frame = -1;
        super.res = res;
        System.out.println("at " + x + ", " + y);
    }

    public Enemy(PImage[] idle, int x, int y, int res) {
        super(3, 1, 0, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 4;
        super.reset_frame = -1;
        super.res = res;
        System.out.println("at " + x + ", " + y);
    }

    @Override
    public void drawIdle(PApplet app, int frame_ctr) {
        if(frame_ctr == reset_frame){
            frame = 0;
            frame_ctr = 0;
        }
        if(frame_ctr % timing == 0){
            frame++;
        }
        if(frame > total_frame - 1){
            frame = 0;
        }
        app.image(this.idle[frame], x - (res / 2), y - (res / 2), res, res);
    }
}
