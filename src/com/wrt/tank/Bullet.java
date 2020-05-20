package com.wrt.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public  static int WIDTH = ResourceManager.bulletU.getWidth();
    public static int HEIGHT = ResourceManager.bulletU.getHeight();
    private int x, y;
    private Dir dir;
    private boolean living = true;
    private TankFrame tf = null;
    private Group group =  Group.BAD;

    Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }
//        g.setColor(Color.RED);
//        g.fillOval(x, y, WIDTH, HEIGHT);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceManager.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD,x,y,null);
                break;
        }
        move();

    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()){
            return ;
        }
        //TODO 用一个rect 记录子弹的位置
//        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX,eY,tf));
        }
    }

    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
