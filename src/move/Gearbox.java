/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package move;

import plugins.*;

/**
 *
 * @author 207797739
 */

   // C-R: Gearbox and not Gearbox.FIXED
public class Gearbox {
    // C-R: use SpeedController here.FIXED
    private SpeedController front, rear;
    
    /**
     *
     * @param front
     * @param rear
     */
    // C-R: use SpeedController here.FIXED
    // C-R: t1 and t2 are not clear, use front and rear instead.FIXED
    public Gearbox(SpeedController front, SpeedController rear) {
        this.front = front;
        this.rear = rear;
    }
    
    /**
     *
     * @param frontPort
     * @param reaPort
     */
    // C-R: port1 and port2 are not clear, use front and rear instead.FIXED
    public Gearbox(int frontPort, int reaPort) {
        this(new Talon(frontPort), new Talon(reaPort));
    }

    /**
     *
     * @param speed
     */
    // C-R: parameter should be named speed.FIXED
    public void set(double speed) {
        front.set(speed);
        rear.set(speed);
        
    }
    
}
