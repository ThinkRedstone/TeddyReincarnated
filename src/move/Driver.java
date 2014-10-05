/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package move;

/**
 *
 * @author 207797739
 */
public class Driver {
   // C-R: gearbox and not gearBox.FIXED
    private Gearbox leftGearbox, rightGearbox;

    /**
     *
     * @param leftGearbox
     * @param rightGearbox
     */
    // C-R: gearbox and not gearBox.FIXED
    public Driver(Gearbox leftGearbox, Gearbox rightGearbox) {
        this.leftGearbox = leftGearbox;
        this.rightGearbox = rightGearbox;
    }

    /**
     *
     * @param leftGearBoxPort1
     * @param leftGearBoxPort2
     * @param rightGearBoxPort1
     * @param rightGearBoxPort2
     */
    public Driver(int leftGearBoxPort1, int leftGearBoxPort2, int rightGearBoxPort1, int rightGearBoxPort2) {
        this(new Gearbox(leftGearBoxPort1, leftGearBoxPort2), new Gearbox(rightGearBoxPort1, rightGearBoxPort2));
    }

    /**
     *
     * @param speed
     */
    public void straigt(double speed){
        rightGearbox.set(speed);
        leftGearbox.set(-speed);
    }

    /**
     *
     * @param speed
     * turns right while speed>0
     */
    public void turn(double speed){
        rightGearbox.set(speed);
        leftGearbox.set(speed);
    }
    
    /*
    C-R:
    add the following methods:
        void driveTwoJosticks(double left, double right);
        void arcade(double speed, double turn);
    */
}
