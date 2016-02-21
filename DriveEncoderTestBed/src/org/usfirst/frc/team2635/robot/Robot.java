
package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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
public class Robot extends IterativeRobot {
	int REAR_RIGHT_CHANNEL = 0;
	int MID_RIGHT_CHANNEL = 1;
	int FRONT_RIGHT_CHANNEL = 2;
	
	int REAR_LEFT_CHANNEL = 3;
	int MID_LEFT_CHANNEL = 4;
	int FRONT_LEFT_CHANNEL = 5;
	
	String KEY_P = "P";
	String KEY_I = "I";
	String KEY_D = "D";
	
	double P_DEFAULT = 0.0;
	double I_DEFAULT = 0.0;
	double D_DEFAULT = 0.0;
	
	CANTalon rearRightMotor;
	CANTalon midRightMotor;
	CANTalon frontRightMotor;
	
	CANTalon rearLeftMotor;
	CANTalon midLeftMotor;
	CANTalon frontLeftMotor;
	DriveThreeMotor drive;
	
	Joystick leftJoystick;
	Joystick rightJoystick;
	double scaler = 1000.0;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
    	SmartDashboard.putNumber(KEY_P, P_DEFAULT);
    	SmartDashboard.putNumber(KEY_I, I_DEFAULT);
    	SmartDashboard.putNumber(KEY_D, D_DEFAULT);
    	
    	rearRightMotor = new CANTalon(REAR_RIGHT_CHANNEL);
    	rearRightMotor.changeControlMode(TalonControlMode.Follower);
    	rearRightMotor.set(FRONT_RIGHT_CHANNEL);
    	
    	midRightMotor = new CANTalon(MID_RIGHT_CHANNEL);
    	midRightMotor.changeControlMode(TalonControlMode.Follower);
    	midRightMotor.set(FRONT_RIGHT_CHANNEL);
    	
    	frontRightMotor = new CANTalon(FRONT_RIGHT_CHANNEL);
    	frontRightMotor.changeControlMode(TalonControlMode.Speed);
    	
    	rearLeftMotor = new CANTalon(REAR_LEFT_CHANNEL);
    	rearLeftMotor.changeControlMode(TalonControlMode.Follower);
    	rearLeftMotor.set(FRONT_LEFT_CHANNEL);
    	
    	midLeftMotor = new CANTalon(MID_LEFT_CHANNEL);
    	midLeftMotor.changeControlMode(TalonControlMode.Follower);
    	midLeftMotor.set(FRONT_LEFT_CHANNEL);
    	
		frontLeftMotor = new CANTalon(FRONT_LEFT_CHANNEL);
    	frontLeftMotor.changeControlMode(TalonControlMode.Speed);
    	
    	drive = new DriveThreeMotorTankDrive(rearRightMotor, midRightMotor, frontRightMotor, rearLeftMotor, midLeftMotor, frontLeftMotor);
    	
    	rightJoystick = new Joystick(0);
    	leftJoystick = new Joystick(1);
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

    public void teleopInit()
    {
    	frontLeftMotor.setPID(SmartDashboard.getNumber(KEY_P), SmartDashboard.getNumber(KEY_I), SmartDashboard.getNumber(KEY_D));
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
    	double leftY = leftJoystick.getRawAxis(1) * scaler;
    	double rightY = rightJoystick.getRawAxis(1) * scaler;
        drive.drive(leftY, rightY);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
    
    }
    
}
