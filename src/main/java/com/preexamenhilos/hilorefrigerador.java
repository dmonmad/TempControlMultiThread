/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preexamenhilos;

import static java.lang.Thread.sleep;
import javax.swing.JLabel;

/**
 *
 * @author Nightm4re
 */
public class hilorefrigerador extends Thread {

    private volatile JLabel deseada;
    private volatile JLabel actual;

    private volatile hilocalefactor hc;

    private volatile boolean isRunning = true;
    private volatile boolean paused = false;

    public hilorefrigerador(JLabel deseada, JLabel actual) {

        this.deseada = deseada;
        this.actual = actual;
    }

    public synchronized void run() {

        while (isRunning) {
            System.out.println("WHILE #FRIO");
            
                int tempdes = Integer.parseInt(deseada.getText());
                int tempact = Integer.parseInt(actual.getText());
                System.out.println(Integer.toString(tempact - 1));
                actual.setText(Integer.toString(tempact - 1));
                System.out.println("IF #FRIO");

                if (tempact <= tempdes - 5) {
                    System.out.println("###FRIO TEMP ACT " + tempact + " / " + tempdes);
                    System.out.println("PAUSE #FRIO");
                    synchronized (hc) {
                        hc.notifyAll();
                    }
                    try {
                        System.out.println("WAIT #FRIO");
                        wait();
                    } catch (Exception e) {
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

    public void setHiloCalefactor(hilocalefactor hc) {
        this.hc = hc;
    }
}
