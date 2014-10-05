package plugins;

/**
 *
 * @author 207797739
 */
public class Joystick {

	double x,y;

    /**
     *
     * @param port
     */
    public Joystick(int port){
		
	}
	
    /**
     *
     * @return
     */
    public double getX() {
		return x;
	}

    /**
     *
     * @return
     */
    public double getY() {
		return y;
	}
	
    /**
     *
     * @param button
     * @return
     */
    public boolean getRawButton(int button){
		return true;
	}
	
    /**
     *
     * @return
     */
    public boolean getTrigger(){
		return true;
	}
}
