package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

public class Explosion extends Entity implements SpriteAnimation {
    private boolean finished;
    public Explosion(PImage[] idle, int x, int y, int res) {
        super(0, 0, 0, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 8;
        super.reset_frame = -1;
        super.res = res;
        this.finished = false;
    }

    public void drawIdle(PApplet app, int frame_ctr){
        if(frame_ctr == reset_frame){
            frame = 0;
            frame_ctr = 0;
        }
        if(frame_ctr % timing == 0){
            frame++;
        }
        if(frame >= total_frame - 1){
            finished = true;
        }
        app.image(this.idle[frame], x - (res / 2), y - (res / 2), res, res);
    }

    public boolean isFinished(){
        return finished;
    }
}
