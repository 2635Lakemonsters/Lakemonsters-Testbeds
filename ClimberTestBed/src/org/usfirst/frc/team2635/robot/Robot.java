
package org.usfirst.frc.team2635.robot;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;
import com.lakemonsters2635.actuator.modules.ActuatorSimple;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	//Two motors, one talon ;)
	CANTalon climberMotors;
	int CLIMBER_CHANNEL = -1;
	BaseActuator<Double> climberMethod;
	String KEY_SETPOINT = "Climber setpoint";
	public void robotInit()
	{
		SmartDashboard.putNumber(KEY_SETPOINT, 0.0);
		climberMotors = new CANTalon(CLIMBER_CHANNEL);
		climberMethod = new ActuatorSimple(climberMotors);
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
	public void teleopPeriodic()
	{
		climberMethod.actuate(SmartDashboard.getNumber(KEY_SETPOINT));
	}



	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{

	}

}
