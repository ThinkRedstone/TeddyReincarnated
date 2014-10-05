/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoot;

import constants.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import plugins.*;

/**
 *
 * @author 207797739
 */
public class Shooter implements Runnable {

    private Locker locker;
    private EncodedTalon wind;
    private DigitalInput lockerLocked, lockerOpen;
    boolean autoWind = ShooterCon.AUTO_WIND_DEFAULT;
    Thread t;
    private int turnsToWind;
//    final private byte OPEN = 1;
//    final private byte CLOSE = -1;
//    final private byte MIDDLE = 0;
//    private byte lockerState;

    /**
     * checks locker state
     *
     * @return Locker State
     */
//    public byte getLockerState() {
//        if (lockerOpen.get()) {
//            lockerState = OPEN;
//        } else if (lockerLocked.get()) {
//            lockerState = CLOSE;
//        } else {
//            lockerState = MIDDLE;
//        }
//        return lockerState;
//    }
    /**
     *
     * @param turnsToWind
     */
    public void setTurnsToWind(int turnsToWind) {
        this.turnsToWind = turnsToWind;
    }

    /**
     *
     * @param locker
     * @param wind
     * @param lockerLocked
     * @param lockerOpen
     */
    public Shooter(Locker locker, EncodedTalon wind, DigitalInput lockerLocked, DigitalInput lockerOpen) {
        this.locker = locker;
        this.wind = wind;
        this.lockerLocked = lockerLocked;
        this.lockerOpen = lockerOpen;
    }

    /**
     *
     * @param lockerPort
     * @param windTalonPort
     * @param windEncoderPort1
     * @param windEncoderPort2
     * @param lockerLockedPort
     * @param lockerOpenPort
     */
    public Shooter(int lockerPort, int windTalonPort, int windEncoderPort1, int windEncoderPort2, int lockerLockedPort, int lockerOpenPort) {
        this(new Locker(lockerPort), new EncodedTalon(windTalonPort, windEncoderPort1, windEncoderPort2), new DigitalInput(lockerLockedPort), new DigitalInput(lockerOpenPort));
    }

    private void wind() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        synchronized (this) {
            if (autoWind) {
                wind.set(ShooterCon.WIND_SPEED);
                while (wind.getEncoder() < ShooterCon.TURNS_TO_WIND * 60) {
                }
                wind.set(0);
                wind.resetEncoder();
            } else {
                wind.set(ShooterCon.WIND_SPEED);
                while (wind.getEncoder() < turnsToWind * 60) {
                }
                wind.set(0);
                wind.resetEncoder();
            }

        }
    }

    /**
     * primes the next shot
     */
    public void prime() {
        locker.lock(this);
        wind();
    }

    /**
     * shoots a primed shot
     */
    public void shoot() {
        locker.unlock(this);

    }
}
