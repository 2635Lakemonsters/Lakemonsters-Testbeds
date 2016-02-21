package org.usfirst.frc.team2635.robot;

import com.lakemonsters2635.actuator.interfaces.BaseDrive;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Class for driving a chassis with three motors on each side. 
 * @author LakeM
 *
 */
public abstract class DriveThreeMotor extends BaseDrive
{
	SpeedController rearRightMotor;
	SpeedController midRightMotor;
	SpeedController frontRightMotor;
	
	SpeedController rearLeftMotor;
	SpeedController midLeftMotor;
	SpeedController frontLeftMotor;
	public DriveThreeMotor(SpeedController rearRightMotor, SpeedController midRightMotor,
			SpeedController frontRightMotor, SpeedController rearLeftMotor, SpeedController midLeftMotor,
			SpeedController frontLeftMotor)
	{
		super();
		this.rearRightMotor = rearRightMotor;
		this.midRightMotor = midRightMotor;
		this.frontRightMotor = frontRightMotor;
		this.rearLeftMotor = rearLeftMotor;
		this.midLeftMotor = midLeftMotor;
		this.frontLeftMotor = frontLeftMotor;
	}

}
