package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class DriveThreeMotorTankDrive extends DriveThreeMotor
{

	public DriveThreeMotorTankDrive(SpeedController rearRightMotor, SpeedController midRightMotor,
			SpeedController frontRightMotor, SpeedController rearLeftMotor, SpeedController midLeftMotor,
			SpeedController frontLeftMotor)
	{
		super(rearRightMotor, midRightMotor, frontRightMotor, rearLeftMotor, midLeftMotor, frontLeftMotor);
	}

	@Override
	public boolean drive(double left, double right)
	{
		rearRightMotor.set(right);
		midRightMotor.set(right);
		frontRightMotor.set(right);
		
		rearLeftMotor.set(left);
		midLeftMotor.set(left);
		frontLeftMotor.set(left);
		return false;
	}

	@Override
	public boolean drive(double left, double right, double Z)
	{
		drive(left, right);
		return false;
	}

}
