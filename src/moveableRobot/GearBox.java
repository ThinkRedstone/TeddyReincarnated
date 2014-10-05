/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moveableRobot;

import plugins.*;

/**
 *
 * @author 207797739
 */
public class GearBox {
    
    private Talon t1, t2;
    
    /**
     *
     * @param t1
     * @param t2
     */
    public GearBox(Talon t1, Talon t2) {
        this.t1 = t1;
        this.t2 = t2;
    }
    
    /**
     *
     * @param port1
     * @param port2
     */
    public GearBox(int port1, int port2) {
        this(new Talon(port1), new Talon(port2));
    }

    /**
     *
     * @param s
     */
    public void set(double s) {
        t1.set(s);
        t2.set(s);
        
    }
    
}
