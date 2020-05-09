package com.wrt.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();

        //初始化敌方坦克
        for(int i=0;i<5;i++){
            tf.tanks.add(new Tank(50+i*50,100,Dir.DOWN,Group.BAD,tf));
        }

        try {
            while (true){
                Thread.sleep(50);
                tf.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
