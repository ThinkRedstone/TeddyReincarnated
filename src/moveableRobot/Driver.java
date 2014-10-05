/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moveableRobot;

/**
 *
 * @author 207797739
 */
public class Driver {

    private GearBox leftGearBox, rightGearBox;

    /**
     *
     * @param leftGearBox
     * @param rightGearBox
     */
    public Driver(GearBox leftGearBox, GearBox rightGearBox) {
        this.leftGearBox = leftGearBox;
        this.rightGearBox = rightGearBox;
    }

    /**
     *
     * @param leftGearBoxPort1
     * @param leftGearBoxPort2
     * @param rightGearBoxPort1
     * @param rightGearBoxPort2
     */
    public Driver(int leftGearBoxPort1, int leftGearBoxPort2, int rightGearBoxPort1, int rightGearBoxPort2) {
        this(new GearBox(leftGearBoxPort1, leftGearBoxPort2), new GearBox(rightGearBoxPort1, rightGearBoxPort2));
    }

    /**
     *
     * @param speed
     */
    public void straigt(double speed){
        rightGearBox.set(speed);
        leftGearBox.set(-speed);
    }

    /**
     *
     * @param speed
     * turns right while speed>0
     */
    public void turn(double speed){
        rightGearBox.set(speed);
        leftGearBox.set(speed);
    }
}
