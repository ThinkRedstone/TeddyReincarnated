/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoot;

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
    private Object key;

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
     * @param key
     */
    public void lock(Object key) {
        this.key = key;
        state = false;
        t = new Thread(this);
        t.start();
    }

    /**
     *
     * @param key
     */
    public void unlock(Object key) {
        this.key = key;
        state = true;
        t = new Thread(this);
        t.start();
    }

    
    @Override
    public void run() {
        synchronized (key) {
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
                locker.set(Relay.Value.kReverse);
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
