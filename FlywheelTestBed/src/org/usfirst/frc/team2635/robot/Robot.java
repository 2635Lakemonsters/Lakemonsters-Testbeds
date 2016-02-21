
package org.usfirst.frc.team2635.robot;

import org.usfirst.frc.team2635.modules.ActuatorTwoMotor;
import org.usfirst.frc.team2635.modules.Flywheel;

import com.lakemonsters2635.actuator.modules.ActuatorSimple;
import com.lakemonsters2635.sensor.modules.SensorRawButton;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
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
	Flywheel flywheel;
	CANTalon rightFlywheelMotor;
	CANTalon leftFlywheelMotor;
	CANTalon feedMotor;
	CANTalon rightElevatorMotor;
	CANTalon leftElevatorMotor;
	
	CANTalon tiltMotor;
	Joystick rightJoystick; //Shooter controls
	Joystick leftJoystick; //Climber controls

	final int JOYSTICK_LEFT_CHANNEL = 1;
	final int JOYSTICK_RIGHT_CHANNEL = 0;

	final String ELEVATOR_KEY_P = "Elevator P";
	final String ELEVATOR_KEY_I = "Elevator I";
	final String ELEVATOR_KEY_D = "Elevator D";
	
	final double ELEVATOR_P_DEFAULT = 0.0;
	final double ELEVATOR_I_DEFAULT = 0.0;
	final double ELEVATOR_D_DEFAULT = 0.0;

	final String SHOOTER_KEY_P = "Shooter P";
	final String SHOOTER_KEY_I = "Shooter I";
	final String SHOOTER_KEY_D = "Shooter D";
	
	final double SHOOTER_P_DEFAULT = 0.0;
	final double SHOOTER_I_DEFAULT = 0.0;
	final double SHOOTER_D_DEFAULT = 0.0;

	final String CAMERA_Y_KEY_P = "Camera Y P";
	final String CAMERA_Y_KEY_I = "Camera Y I";
	final String CAMERA_Y_KEY_D = "Camera Y D";
	final double CAMERA_Y_P_DEFAULT = 0.0;
	final double CAMERA_Y_I_DEFAULT = 0.0;
	final double CAMERA_Y_D_DEFAULT = 0.0;

	
	final int RIGHT_FLYWHEEL_CHANNEL = 7;
	final int LEFT_FLYWHEEL_CHANNEL = 8;
	//final int FEED_CHANNEL = 9;
	final int TILT_CHANNEL = 10;
	final int ELEVATOR_RIGHT_CHANNEL = 11;
	final int ELEVATOR_LEFT_CHANNEL = 14;
	
	final double FIRE_SPEED = 1.0; //TODO: If speed mode implemented, multiply this by speed scaler
	final double FEED_SPEED = 1.0;
	final double LOAD_FRONT_SPEED = -0.5; //TODO: If speed mode implemented, multiply this by speed scaler
	//final double LOAD_BACK_SPEED = 0.5;
	
	final double ELEVATE_UP_SPEED = 0.5;
	final double ELEVATE_DOWN_SPEED = -0.5;
	
	final double TILT_SCALER = 1.0; //TODO: Find maximum tilt distance;
	final double ELEVATION_DISTANCE = 0.0; //TODO: Find maximum elevation distance
	//Right hand joystick
	final int TILT_AXIS = 2;
	final int AIM_BUTTON = 3;
	final int FIRE_BUTTON = 1;
	final int LOAD_FRONT_BUTTON = 2;
	//final int LOAD_BACK_BUTTON = 4;
	final int ELEVATE_UP_BUTTON = 10;
	final int ELEVATE_DOWN_BUTTON = 11;

	public void robotInit()
	{
    	rightFlywheelMotor = new CANTalon(RIGHT_FLYWHEEL_CHANNEL);
    	//rightFlywheelMotor.changeControlMode(TalonControlMode.Speed);
    	
    	leftFlywheelMotor = new CANTalon(LEFT_FLYWHEEL_CHANNEL);
    	//leftFlywheelMotor.changeControlMode(TalonControlMode.Speed);
    	//feedMotor = new CANTalon(FEED_CHANNEL);
    	
    	rightElevatorMotor = new CANTalon(ELEVATOR_RIGHT_CHANNEL);
    	rightElevatorMotor.changeControlMode(TalonControlMode.Position);
    	rightElevatorMotor.setPID(ELEVATOR_P_DEFAULT, ELEVATOR_I_DEFAULT, ELEVATOR_D_DEFAULT);
    	
    	leftElevatorMotor = new CANTalon(ELEVATOR_LEFT_CHANNEL);
    	leftElevatorMotor.changeControlMode(TalonControlMode.Follower);
    	leftElevatorMotor.set(ELEVATOR_RIGHT_CHANNEL);
    	
    	tiltMotor = new CANTalon(TILT_CHANNEL);
    	tiltMotor.changeControlMode(TalonControlMode.Position);
    	tiltMotor.setPID(CAMERA_Y_P_DEFAULT, CAMERA_Y_I_DEFAULT, CAMERA_Y_D_DEFAULT);
    	

    	rightJoystick = new Joystick(JOYSTICK_RIGHT_CHANNEL);
    	leftJoystick = new Joystick(JOYSTICK_LEFT_CHANNEL);

    	flywheel = new Flywheel(
    			new ActuatorTwoMotor(leftFlywheelMotor, rightFlywheelMotor), 
    			/*new ActuatorSimple(feedMotor), */
    			new ActuatorTwoMotor(leftFlywheelMotor, rightFlywheelMotor), 
    			new ActuatorSimple(feedMotor),
    			new SensorRawButton(LOAD_FRONT_BUTTON, rightJoystick), 
    		
    			new ActuatorSimple(rightElevatorMotor),
    			new ActuatorSimple(tiltMotor));

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
		boolean fire = rightJoystick.getRawButton(FIRE_BUTTON);
		if(fire)
		{
			
			//TODO: Find max tilt height, find max elevate height
			flywheel.fire(FIRE_SPEED, 0.0, 0.0, FEED_SPEED);
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{

	}

}
