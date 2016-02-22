
package org.usfirst.frc.team2635.robot;


import com.lakemonsters2635.actuator.interfaces.BaseActuator;
import com.lakemonsters2635.actuator.modules.ActuatorSimple;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

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
	boolean debugEnableDrive = true;
	
	CANTalon rearRightMotor;
	CANTalon midRightMotor;
	CANTalon frontRightMotor;
	
	CANTalon rearLeftMotor;
	CANTalon midLeftMotor;
	CANTalon frontLeftMotor;
	
	DriveThreeMotor robotDrive;
	

	CANTalon rightFlywheelMotor;
	CANTalon leftFlywheelMotor;
	CANTalon feedRearMotor;
	
	BaseActuator<Double> flywheelMethod;
	BaseActuator<Double> feedMethod;
	
	//Xbox controller
	Joystick rightJoystick;
	Joystick leftJoystick;
	
	final int LOAD_FRONT_BUTTON = 1;
	final int WHEEL_AXIS = 0;
	final int FEED_AXIS = 2;
	
	final int REAR_RIGHT_CHANNEL = 1;
	final int MID_RIGHT_CHANNEL = 2;
	final int FRONT_RIGHT_CHANNEL = 3;
	
	final int REAR_LEFT_CHANNEL = 4;
	final int MID_LEFT_CHANNEL = 5;
	final int FRONT_LEFT_CHANNEL = 6;
	
	final int RIGHT_Y_AXIS = 1;
	final int LEFT_Y_AXIS = 1;
	
	final int FEED_SPEED_AXIS = 2;
	final int SHOOTER_SPEED_AXIS = 2;
	
	final int RIGHT_FLYWHEEL_CHANNEL = 10;
	final int LEFT_FLYWHEEL_CHANNEL = 7;
	final int FEED_CHANNEL = 11;
	
	final double FEED_SPEED = -1.0;
	
	final double SHOOTER_FEED_SPEED = -0.5; 
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
    	if(debugEnableDrive)
    	{
    		rearRightMotor = new CANTalon(REAR_RIGHT_CHANNEL);
	    	//rearRightMotor.changeControlMode(TalonControlMode.Follower);
	    	//rearRightMotor.set(FRONT_RIGHT_CHANNEL);
	    	
	    	midRightMotor = new CANTalon(MID_RIGHT_CHANNEL);
//	    	midRightMotor.changeControlMode(TalonControlMode.Follower);
//	    	midRightMotor.set(FRONT_RIGHT_CHANNEL);
//	    	
	    	frontRightMotor = new CANTalon(FRONT_RIGHT_CHANNEL);
//	    	frontRightMotor.changeControlMode(TalonControlMode.Speed);
//	    	frontRightMotor.setPID(DRIVE_P_DEFAULT, DRIVE_I_DEFAULT, DRIVE_D_DEFAULT);
//	    	
	    	rearLeftMotor = new CANTalon(REAR_LEFT_CHANNEL);
//	    	rearLeftMotor.changeControlMode(TalonControlMode.Follower);
//	    	rearLeftMotor.set(FRONT_LEFT_CHANNEL);
//	    	
	    	midLeftMotor = new CANTalon(MID_LEFT_CHANNEL);
//	    	midLeftMotor.changeControlMode(TalonControlMode.Follower);
//	    	midLeftMotor.set(FRONT_LEFT_CHANNEL);
//	    	
	    	frontLeftMotor = new CANTalon(FRONT_LEFT_CHANNEL);
//	    	frontLeftMotor.changeControlMode(TalonControlMode.Speed);
//	    	frontLeftMotor.setPID(DRIVE_P_DEFAULT, DRIVE_I_DEFAULT, DRIVE_D_DEFAULT);
			robotDrive = new DriveThreeMotorTankDrive(rearRightMotor, midRightMotor, frontRightMotor, rearLeftMotor, midLeftMotor, frontLeftMotor);
    	}
    	leftFlywheelMotor = new CANTalon(LEFT_FLYWHEEL_CHANNEL);
    	rightFlywheelMotor = new CANTalon(RIGHT_FLYWHEEL_CHANNEL);
    	feedRearMotor = new CANTalon(FEED_CHANNEL);
    	
    	rightJoystick = new Joystick(0);
    	leftJoystick = new Joystick(1);
        
    	flywheelMethod = new ActuatorTwoMotorInverse(leftFlywheelMotor, rightFlywheelMotor);
        feedMethod = new ActuatorSimple(feedRearMotor);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
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
    	
    	boolean wheel = rightJoystick.getRawButton(3);
    	boolean feed = leftJoystick.getRawButton(3);
    	
    	double wheelSpeed = rightJoystick.getRawAxis(SHOOTER_SPEED_AXIS);
    	double feedSpeed = leftJoystick.getRawAxis(FEED_SPEED_AXIS);
    	SmartDashboard.putBoolean("Wheel activate", wheel);
    	SmartDashboard.putBoolean("Feed activate", feed);
    	SmartDashboard.putNumber("Wheel speed", wheelSpeed);
    	SmartDashboard.putNumber("Feed speed", feedSpeed);
    	if(wheel)
    	{
    		flywheelMethod.actuate(wheelSpeed);
    	}
    	else
    	{
    		flywheelMethod.actuate(0.0);
    	}
    	if(feed)
    	{
    		feedMethod.actuate(feedSpeed);
    	}
    	else
    	{
    		feedMethod.actuate(0.0);
    	}
    	if(debugEnableDrive)
    	{
        	double RY = rightJoystick.getRawAxis(RIGHT_Y_AXIS);
        	double LY = -leftJoystick.getRawAxis(LEFT_Y_AXIS);
        	robotDrive.drive(LY, RY);
    	}
       
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
    
    }
    
}
