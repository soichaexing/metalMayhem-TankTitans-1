package policewarrior;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends Entity {
    protected String nama;
    protected PImage[] idle;
    protected int t = 0;
    protected int timing = 3;
    protected int total_frame = 18;
    protected int reset_frame = -1;

    public Player(PImage[] idle, int x, int y) {
        this.idle = idle;
        this.x = x;
        this.y = y;
    }

    public void drawIdle(PApplet app, int f){
        if(f == reset_frame){
            t = 0;
            f = 0;
        }
        if(f % timing == 0){
            t++;
        }
        if(t > total_frame - 1){
            t = 0;
        }
        app.image(idle[t], x, y);
    }
}
