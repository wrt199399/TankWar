package com.wrt.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    Tank myTank = new Tank(200 , 200 ,Dir.LEFT,Group.GOOD,this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    static final  int GAME_WIDTH = 400, GAME_HEIGHT = 400;


    public TankFrame() {

        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


    }

    //使用双缓冲解决画面闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffscreen = offScreenImage.getGraphics();
        gOffscreen.setColor(Color.BLACK);
        gOffscreen.fillRect(0 ,0,GAME_WIDTH,GAME_HEIGHT);
        print(gOffscreen);
        g.drawImage(offScreenImage,0,0,null);
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawString("子弹的数量:"+bullets.size(),10,60);
        g.drawString("敌军的数量:"+tanks.size(),10,80);
        g.drawString("爆炸的数量:"+tanks.size(),10,100);
        myTank.paint(g);
//        for(Bullet b : bullets){
//            b.paint(g);
//        }
        //根据集合中的数量画子弹
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        //根据集合中的数量画坦克
        for(int i=0;i<tanks.size();i++){
            tanks.get(i).paint(g);
        }

        for(int i=0;i<bullets.size();i++){
            for(int j=0;j<tanks.size();j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

        for(int i=0;i<explodes.size();i++){
            explodes.get(i).paint(g);
        }

    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL&&!bR&&!bU&&!bD){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bU) myTank.setDir(Dir.UP);
                if(bD) myTank.setDir(Dir.DOWN);
            }

        }
    }

}
