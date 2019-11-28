/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preexamenhilos;

import javax.swing.JLabel;

/**
 *
 * @author Nightm4re
 */
public class hilocalefactor extends Thread {

    private volatile JLabel deseada;
    private volatile JLabel actual;

    private volatile hilorefrigerador hr;

    private volatile boolean isRunning = true;
    private volatile boolean paused = false;

    public hilocalefactor(JLabel deseada, JLabel actual) {

        this.deseada = deseada;
        this.actual = actual;

    }

    public synchronized void run() {

        while (isRunning) {
            System.out.println("While CALOR");
            if (!paused) {
                int tempdes = Integer.parseInt(deseada.getText());
                int tempact = Integer.parseInt(actual.getText());
                System.out.println(Integer.toString(tempact + 1));
                actual.setText(Integer.toString(tempact + 1));
                System.out.println("IF CALOR");

                if (tempact >= tempdes + 5) {
                    System.out.println("---CALOR TEMP ACT " + tempact + " / " + tempdes);
                    System.out.println("PAUSE THREAD CALOR");
                    synchronized (hr) {
                        hr.notifyAll();
                    }
                    try {
                        System.out.println("WAIT CALOR");
                        wait();
                    } catch (Exception e) {
                    }
                }
            }
            
            try {
                Thread.sleep(200);
            } catch (Exception e) {

            }
        }

    }

    public void pauseThread() {
        paused = true;
    }

    public void resumeThread() {
        paused = false;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void setHiloRefrigerador(hilorefrigerador hr) {
        this.hr = hr;
    }
}
