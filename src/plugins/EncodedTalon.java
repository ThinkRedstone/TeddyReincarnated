/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins;

/**
 *
 * @author 207797739
 */
public class EncodedTalon extends Talon {

    private final Encoder e;

    /**
     *
     * @param talonPort
     * @param encoderPort1
     * @param encoderPort2
     */
    public EncodedTalon(int talonPort, int encoderPort1, int encoderPort2) {
        super(talonPort);
        e = new Encoder(encoderPort1, encoderPort2);
        e.start();
    }

    /**
     *
     */
    public void resetEncoder() {
        e.reset();
    }

    /**
     *
     * @return
     */
    public double getEncoder() {
        return e.get();
    }

    /**
     *
     */
    public void stopEncoder() {
        e.stop();
    }

}
