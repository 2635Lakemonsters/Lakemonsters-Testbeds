
package org.usfirst.frc.team2635.robot;



import com.lakemonsters2635.actuator.interfaces.BaseActuator;
import com.lakemonsters2635.actuator.modules.ActuatorSimple;

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
	final String CAMERA_Y_KEY_P = "Camera Y P";
	final String CAMERA_Y_KEY_I = "Camera Y I";
	final String CAMERA_Y_KEY_D = "Camera Y D";
	final double CAMERA_Y_P_DEFAULT = 0.0;
	final double CAMERA_Y_I_DEFAULT = 0.0;
	final double CAMERA_Y_D_DEFAULT = 0.0;
	
	public static final int REAR_RIGHT_CHANNEL = 1;
	public static final int MID_RIGHT_CHANNEL = 2;
	public static final int FRONT_RIGHT_CHANNEL = 3;
	
	public static final int REAR_LEFT_CHANNEL = 4;
	public static final int MID_LEFT_CHANNEL = 5;
	public static final int FRONT_LEFT_CHANNEL = 6;
	
	public static final int JOYSTICK_LEFT_CHANNEL = 1;
	public static final int JOYSTICK_RIGHT_CHANNEL = 0;
	
	public static final int RIGHT_Y_AXIS = 1;
	public static final int LEFT_Y_AXIS = 1;
	
	public static final String DRIVE_KEY_P = "Drive P";
	public static final String DRIVE_KEY_I = "Drive I";
	public static final String DRIVE_KEY_D = "Drive D";
	
	public static final double DRIVE_P_DEFAULT = 0.0;
	public static final double DRIVE_I_DEFAULT = 0.0;
	public static final double DRIVE_D_DEFAULT = 0.0;


	final int TILT_CHANNEL = 9;
	CANTalon tiltMotor;
	CANTalon rearRightMotor;
	CANTalon midRightMotor;
	CANTalon frontRightMotor;
	
	CANTalon rearLeftMotor;
	CANTalon midLeftMotor;
	CANTalon frontLeftMotor;
	
	DriveThreeMotor robotDrive;
	
	Joystick rightJoystick; //Shooter controls
	Joystick leftJoystick; //Climber controls

	String KEY_SETPOINT = "Setpoint";
	BaseActuator<Double> tiltMethod;

	public void driveInit()
	{
		//TODO: might have to invert the output for some of these. There is a function to do that in CANTalon
    	rearRightMotor = new CANTalon(REAR_RIGHT_CHANNEL);
    	//rearRightMotor.changeControlMode(TalonControlMode.Follower);
    	//rearRightMotor.set(FRONT_RIGHT_CHANNEL);
    	
    	midRightMotor = new CANTalon(MID_RIGHT_CHANNEL);
//    	midRightMotor.changeControlMode(TalonControlMode.Follower);
//    	midRightMotor.set(FRONT_RIGHT_CHANNEL);
//    	
    	frontRightMotor = new CANTalon(FRONT_RIGHT_CHANNEL);
//    	frontRightMotor.changeControlMode(TalonControlMode.Speed);
//    	frontRightMotor.setPID(DRIVE_P_DEFAULT, DRIVE_I_DEFAULT, DRIVE_D_DEFAULT);
//    	
    	rearLeftMotor = new CANTalon(REAR_LEFT_CHANNEL);
//    	rearLeftMotor.changeControlMode(TalonControlMode.Follower);
//    	rearLeftMotor.set(FRONT_LEFT_CHANNEL);
//    	
    	midLeftMotor = new CANTalon(MID_LEFT_CHANNEL);
//    	midLeftMotor.changeControlMode(TalonControlMode.Follower);
//    	midLeftMotor.set(FRONT_LEFT_CHANNEL);
//    	
		frontLeftMotor = new CANTalon(FRONT_LEFT_CHANNEL);
//    	frontLeftMotor.changeControlMode(TalonControlMode.Speed);
//    	frontLeftMotor.setPID(DRIVE_P_DEFAULT, DRIVE_I_DEFAULT, DRIVE_D_DEFAULT);
		robotDrive = new DriveThreeMotorTankDrive(rearRightMotor, midRightMotor, frontRightMotor, rearLeftMotor, midLeftMotor, frontLeftMotor);
      	rightJoystick = new Joystick(JOYSTICK_RIGHT_CHANNEL);
      	leftJoystick = new Joystick(JOYSTICK_LEFT_CHANNEL);

	}

	public void robotInit()
	{
    	SmartDashboard.putNumber(CAMERA_Y_KEY_P, CAMERA_Y_P_DEFAULT);
    	SmartDashboard.putNumber(CAMERA_Y_KEY_I, CAMERA_Y_I_DEFAULT);
    	SmartDashboard.putNumber(CAMERA_Y_KEY_D, CAMERA_Y_D_DEFAULT);
    	SmartDashboard.putNumber(KEY_SETPOINT, 0.0);
    	tiltMotor = new CANTalon(TILT_CHANNEL);
    	//tiltMotor.changeControlMode(TalonControlMode.Position);
    	//tiltMotor.setPID(CAMERA_Y_P_DEFAULT, CAMERA_Y_I_DEFAULT, CAMERA_Y_D_DEFAULT);
    	
    	tiltMethod = new ActuatorSimple(tiltMotor);

    	driveInit();
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
    public void driveTeleop()
    {
    	double RY = rightJoystick.getRawAxis(RIGHT_Y_AXIS);
    	double LY = -leftJoystick.getRawAxis(LEFT_Y_AXIS);
    	robotDrive.drive(LY, RY);

    }

	public void teleopPeriodic()
	{
		if(rightJoystick.getRawButton(3))
		{
			tiltMethod.actuate(SmartDashboard.getNumber(KEY_SETPOINT));
		}
		else
		{
			tiltMethod.actuate(0.0);
		}
		//driveTeleop();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{

	}

}
