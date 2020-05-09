package com.wrt.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x=200,y=300;
    public static int WIDTH = ResourceManager.tankU.getWidth();
    public static int HEIGHT = ResourceManager.tankU.getHeight();
    private Dir dir = Dir.LEFT;
    private static final int SPEED = 5;
    private boolean moving = true;
    private TankFrame tf = null;
    private boolean living = true;
    private Random random = new Random();
    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir ,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, width, height);
        if(!living){
            tf.tanks.remove(this);
        }
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

        if(this.group==Group.BAD && random.nextInt(10)>8){
            this.fire();
        }
        if(this.group == Group.BAD && random.nextInt(100)>95)randomDir();
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
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
      int bX = x + Tank.WIDTH/2 -Bullet.WIDTH/2;
      int bY = y + Tank.WIDTH/2 -Bullet.WIDTH/2;
        tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,tf));
    }

    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
