/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.frc.wpilibj;

/**
 *
 * @author 207797739
 */
public class BetterJoystick extends plugins.Joystick {

    boolean[] booleans;

    /**
     *
     * @param port
     */
    public BetterJoystick(int port) {
        super(port);
        booleans = new boolean[11];
    }

    /**
     *
     * @param button
     * @return
     */
    public boolean getFirstPress(int button) {
        if (!booleans[button-1]) {
            booleans[button-1] = getRawButton(button);
            return getRawButton(button);
        }
        booleans[button-1] = getRawButton(button);
        return false;
    }

}
