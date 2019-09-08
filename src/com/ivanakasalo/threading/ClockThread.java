/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Ivy
 */
public class ClockThread extends Thread{
    private final Label label;
        private volatile boolean running = true;
        private static final String DATE_FORMAT_NOW = "HH:mm:ss";

        public ClockThread(Label label) {
            this.label = label;

        }

        void terminate() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                Calendar kalendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
                Platform.runLater(() -> label.setText(sdf.format(kalendar.getTime())));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(getName() + ": interrupted, will check if need to be terminated");
                }
            }
        }
}
