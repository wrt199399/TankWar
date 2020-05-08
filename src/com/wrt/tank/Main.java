package com.wrt.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        TankFrame frame = new TankFrame();
        try {
            while (true){
                Thread.sleep(50);
                frame.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
