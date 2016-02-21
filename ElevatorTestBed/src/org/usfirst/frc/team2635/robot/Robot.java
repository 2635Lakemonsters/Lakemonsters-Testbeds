
package org.usfirst.frc.team2635.robot;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;
import com.lakemonsters2635.actuator.modules.ActuatorSimple;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{

	final String ELEVATOR_KEY_P = "Elevator P";
	final String ELEVATOR_KEY_I = "Elevator I";
	final String ELEVATOR_KEY_D = "Elevator D";
	final double ELEVATOR_P_DEFAULT = 0.0;
	final double ELEVATOR_I_DEFAULT = 0.0;
	final double ELEVATOR_D_DEFAULT = 0.0;
	
	final String SETPOINT_KEY = "Setpoint";
	
	final int ELEVATOR_RIGHT_CHANNEL = 11;
	final int ELEVATOR_LEFT_CHANNEL = 14;
	
	CANTalon rightElevatorMotor;
	CANTalon leftElevatorMotor;
	
	BaseActuator<Double> elevateMethod;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		SmartDashboard.putNumber(SETPOINT_KEY, 0.0);
    	SmartDashboard.putNumber(ELEVATOR_KEY_P, ELEVATOR_P_DEFAULT);
    	SmartDashboard.putNumber(ELEVATOR_KEY_I, ELEVATOR_I_DEFAULT);
    	SmartDashboard.putNumber(ELEVATOR_KEY_D, ELEVATOR_D_DEFAULT);

		rightElevatorMotor = new CANTalon(ELEVATOR_RIGHT_CHANNEL);
    	rightElevatorMotor.changeControlMode(TalonControlMode.Position);
    	rightElevatorMotor.setPID(ELEVATOR_P_DEFAULT, ELEVATOR_I_DEFAULT, ELEVATOR_D_DEFAULT);
    	
    	leftElevatorMotor = new CANTalon(ELEVATOR_LEFT_CHANNEL);
    	leftElevatorMotor.changeControlMode(TalonControlMode.Follower);
    	leftElevatorMotor.set(ELEVATOR_RIGHT_CHANNEL);
    			
		elevateMethod = new ActuatorSimple(rightElevatorMotor);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit()
	{
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopInit()
	{
		rightElevatorMotor.setPID(SmartDashboard.getNumber(ELEVATOR_KEY_P), SmartDashboard.getNumber(ELEVATOR_KEY_I), SmartDashboard.getNumber(ELEVATOR_KEY_D));
	}
	public void teleopPeriodic()
	{
		elevateMethod.actuate(SmartDashboard.getNumber(SETPOINT_KEY));
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{

	}

}
