package edu.wpi.frc.wpilibj;

import move.*;
import plugins.*;
import constants.*;
import load.*;
import shoot.*;

/**
 *
 * @author 207797739
 */
public class RobotTemplate {

    BetterJoystick driverJ;
    BetterJoystick navJ;
    Driver driveTrain;
    Shooter shooter;
    Roller roller;

    /**
     *
     */
    public RobotTemplate() {
    }

    /**
     *
     */
    public void robotInit() {
        driverJ = new BetterJoystick(Controls.DRIVER_PORT);
        navJ = new BetterJoystick(Controls.NAV_PORT);
        driveTrain = new Driver(Ports.LEFT_TALON_1, Ports.LEFT_TALON_2, Ports.RIGHT_TALON_1, Ports.RIGHT_TALON_2);
        shooter = new Shooter(Ports.LOCKER_PORT, Ports.WIND_TALON_PORT, Ports.WIND_ENCODER_PORT_1, Ports.WIND_ENCODER_PORT_2, Ports.LOCKER_LOCKED_PORT, Ports.LOCKER_OPEN_PORT);
        roller = new Roller(Ports.ROLLER_RIGHT_SWITCH_PORT, Ports.ROLLER_LEFT_SWITCH_PORT, Ports.ROLLER_ROLL_PORT, Ports.ROLLER_MOVE_PORT);
    }

    /**
     *
     */
    public void autonomousPeriodic() {

    }

    /**
     *
     */
    public void teleopPeriodic() {
        // C-R: getRawButton returns true multiple times.FIXED
        if (navJ.getFirstPress(Controls.SHOOT)) {
            shooter.shoot();
        }
        if (navJ.getFirstPress(Controls.PRIME)) {
            shooter.prime();
        }
        if (navJ.getFirstPress(Controls.LOAD)) {
            roller.load();
        }
        if (driverJ.getFirstPress(1)) {
            driveTrain.turn(driverJ.getX());
        } else {
            driveTrain.straigt(driverJ.getY());
        }
    }
}
