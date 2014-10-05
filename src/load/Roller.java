/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package load;

import plugins.DigitalInput;
import plugins.Relay;
import plugins.Talon;

/**
 *
 * @author ThinkRedstone
 */
public class Roller implements Runnable {

    private DigitalInput switchRight, switchLeft;
    private Talon rollRoller;
    private Relay moveRoller;
    private Thread t;

    /**
     *
     */
    public boolean close;

    /**
     *
     * @param switchRight
     * @param switchLeft
     * @param rollRoller
     * @param moveRoller
     */
    public Roller(DigitalInput switchRight, DigitalInput switchLeft, Talon rollRoller, Relay moveRoller) {
        this.switchRight = switchRight;
        this.switchLeft = switchLeft;
        this.rollRoller = rollRoller;
        this.moveRoller = moveRoller;
    }

    /**
     *
     * @param switchRightPort
     * @param switchLeftPort
     * @param rollRollerPort
     * @param moveRollerPort
     */
    public Roller(int switchRightPort, int switchLeftPort, int rollRollerPort, int moveRollerPort) {
        this.switchRight = new DigitalInput(switchRightPort);
        this.switchLeft = new DigitalInput(switchLeftPort);
        this.rollRoller = new Talon(rollRollerPort);
        this.moveRoller = new Relay(moveRollerPort);
    }

    /**
     *changes the state of the rollers
     */
    public void changeRoller() {
        t = new Thread(this);
        t.start();
    }
    
    /**
     *interrupts the moving of the roller 
     */
    public void interrupt(){
       t.interrupt();
   }

    /**
     *
     */
    //C-R: controll the rollerls in the range.FIXED
    public void run() {
        //<editor-fold defaultstate="collapsed" desc="runner">
        /**
         *   _
         * ~[}
         * o==.#
         * _   #'=o
         * \_/ \
         * |_
         */
//</editor-fold>
      
        //assuming true while triggered
        if (!close) {
            if (switchLeft.get() && switchRight.get()) {
                moveRoller.set(Relay.Value.kReverse);
                try {
                    Thread.sleep(constants.Delay.TIME_TO_RETRACT);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                moveRoller.set(Relay.Value.kOff);
            }
        } else if (switchLeft.get() == false && switchRight.get() == false) {
            while (!switchLeft.get() && !switchRight.get()) {
                moveRoller.set(Relay.Value.kForward);
            }

        }

        moveRoller.set(Relay.Value.kOff);
    }

    private void roll(double s) {
        rollRoller.set(s);
        try {
            Thread.sleep(constants.Delay.TIME_TO_LOAD);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        rollRoller.set(0);
    }

    /**
     * auto loads the ball
     */
    public void load() {
        close = true;
        this.changeRoller();
        this.roll(constants.rollerCon.LOAD_SPEED);
        close = false;
        this.changeRoller();
    }
}
