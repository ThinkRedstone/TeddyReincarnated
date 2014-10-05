/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootableRobot;

import plugins.*;
import constants.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 207797739
 */
public class Locker implements Runnable {

    private Relay locker;
    private boolean state;
    private Thread t;
    private Object lock = null;

    /**
     *
     * @param locker
     */
    public Locker(Relay locker) {
        this.locker = locker;
    }

    /**
     *
     * @param lockerPort
     */
    public Locker(int lockerPort) {
        this(new Relay(lockerPort));
    }

    /**
     *
     * @param lock
     */
    public void lock(Object lock) {
        this.lock = lock;
        state = false;
        t = new Thread(this);
        t.start();
    }

    /**
     *
     * @param lock
     */
    public void unlock(Object lock) {
        this.lock = lock;
        state = true;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        synchronized (lock) {
            if (state) {
                //assuming forward locks
                locker.set(Relay.Value.kForward);
                try {
                    Thread.sleep(Delay.LOCKER_CHANGE);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Locker.class.getName()).log(Level.SEVERE, null, ex);
                }
                locker.set(Relay.Value.kOff);
            } else {
                locker.set(Relay.Value.kBackward);
                try {
                    Thread.sleep(Delay.LOCKER_CHANGE);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Locker.class.getName()).log(Level.SEVERE, null, ex);
                }
                locker.set(Relay.Value.kOff);
            }
        }
    }

}
