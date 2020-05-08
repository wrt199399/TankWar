package com.wrt.tank;

import java.awt.*;

public class Tank {
    private int x=200,y=200,width=15,height=15;
    private Dir dir = Dir.LEFT;
    private static final int SPEED = 10;
    private boolean moving = false;
    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir , TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, width, height);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceManager.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceManager.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD,x,y,null);
                break;
        }

        move();

    }
    private void move (){
        if(isMoving()){
            switch (dir){
                case LEFT:
                    x-=SPEED;break;
                case RIGHT:
                    x+=SPEED;break;
                case UP:
                    y-=SPEED;break;
                case DOWN:
                    y+=SPEED;break;
            }
        }
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public void fire() {
      tf.bullets.add(new Bullet(this.x,this.y,this.dir,tf));
    }
}
