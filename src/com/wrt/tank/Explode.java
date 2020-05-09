package com.wrt.tank;

import java.awt.*;

public class Explode {
    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    public static int HEIGHT = ResourceManager.explodes[0].getHeight();
    private int x, y;
    private TankFrame tf = null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++], x, y, null);
        if(step >= ResourceManager.explodes.length){
            tf.explodes.remove(this);
        }
    }


}
