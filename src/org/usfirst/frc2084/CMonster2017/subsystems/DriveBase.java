// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc2084.CMonster2017.subsystems;

import org.usfirst.frc2084.CMonster2017.Robot;
import org.usfirst.frc2084.CMonster2017.RobotMap;
import org.usfirst.frc2084.CMonster2017.Drive.ArcadeDrive;
import org.usfirst.frc2084.CMonster2017.Drive.JoystickSensitivity;
import org.usfirst.frc2084.CMonster2017.PID.DistancePID;
import org.usfirst.frc2084.CMonster2017.PID.HeadingPID;
import org.usfirst.frc2084.CMonster2017.commands.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import com.ctre.*;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveBase extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final AHRS ahrs = RobotMap.ahrs;

	// There are 2 driver motors on each side of the robot.
	// Here are the declarations.
	private final CANTalon leftTalon1 = RobotMap.driveBaseLeftTalon1;
	// private final PIDController leftPIDController2 =
	// RobotMap.driveBasePIDLeftPIDController2;
	private final CANTalon rightTalon1 = RobotMap.driveBaseRightTalon1;
	// private final PIDController rightPIDController2 =
	// RobotMap.driveBasePIDRightPIDController2;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	double leftMotorSpeed; // variables used to calculate motor speed
	double rightMotorSpeed;
	double moveSpeed;
	double rotateSpeed;

	boolean InPosition; // variables for autonomous
	public static double LeftDistance; //left and right distance are averaged for the average distance
	public static double RightDistance;
	public static double scale = 1.0;
	int Waypoint = 1; // the number of way points stating at 1.
	double[] WayPoints = new double[3]; // array with 3 elements holding the
										// distance to each way point;
	ArcadeDrive arcadeDrive = new ArcadeDrive();
	JoystickSensitivity joystickSensitivity = new JoystickSensitivity();
	private final double WheelDiameter = RobotMap.DRIVE_SUBSYSTEM_WHEEL_DIAMETER;
	double[] returnData = new double[2];
	Joystick RightJoystick;
	Joystick LeftJoystick;
	public boolean isInverted = false;

	HeadingPID headingPID = RobotMap.headingPID; // instance variables
	DistancePID distancePID = RobotMap.distancePID;

	public void EnableDriveBase() {
		leftTalon1.reset(); // reset PID controllers before enable to prevent
							// "wind up"
		rightTalon1.reset();
		leftTalon1.enable();
		rightTalon1.enable();
		distancePID.enable();

		headingPID.enable();
		Robot.gearBase.CompressorOn(); //turn on the compressor when the robot is enabled
		leftTalon1.setPosition(0);
		rightTalon1.setPosition(0);
		
	}

	public void DisableDriveBase() {
		leftTalon1.disable();
		rightTalon1.disable();
		distancePID.disable();
		headingPID.disable();
	}

	public void DriveAutonomous() {

		moveSpeed = distancePID.getOutput();
		rotateSpeed = headingPID.getOutput(); // Get and store the output from
												// the heading PID controller.

		SmartDashboard.putNumber("HeadingPID", rotateSpeed);// rotateSpeed is
															// the value of
															// HeadingPID
		SmartDashboard.putNumber("YAW", (double) ahrs.getYaw());
		SmartDashboard.putNumber("DistancePID", moveSpeed);
		// SmartDashboard.putNumber("pitch", (double)ahrs.getPitch());
		// SmartDashboard.putNumber("roll", (double)ahrs.getRoll());

		returnData = arcadeDrive.calculateSpeed(moveSpeed, rotateSpeed);
		leftMotorSpeed = returnData[0];
		rightMotorSpeed = returnData[1];
		// these 3 lines replace the arcade calculations blocks, call them from
		// ArchadeDrive class

		leftTalon1.set(leftMotorSpeed * 586); // set thing to max RPM
		rightTalon1.set(rightMotorSpeed * 586);

		// LeftDistance = leftEncoder.getDistance(); //Read encoder distance
		// traveled in meters.
		// RightDistance = rightEncoder.getDistance();
		// have to invert rightDistance so the average isn't negative
		
		
		LeftDistance = leftTalon1.getPosition() * RobotMap.DISTANCE_PER_PULSE;
		RightDistance = rightTalon1.getPosition() * RobotMap.DISTANCE_PER_PULSE;
		//multiply the position of the talon by the distance traveled per rotation
		//this yields the total distance
		
		LeftDistance *= -1;  //invert the leftDistance so values are positive
		RobotMap.AverageDistance = (LeftDistance + RightDistance) / 2; // Calculate
																		// the
																		// average
																		// distance
																		// traveled.

		//SmartDashboard stuff		
		//SmartDashboard.putNumber("WheelDiameter", WheelDiameter);
		SmartDashboard.putNumber("LeftDistance", LeftDistance);
		SmartDashboard.putNumber("AV Distance", RobotMap.AverageDistance);
		SmartDashboard.putNumber("Right Distance", RightDistance);
		SmartDashboard.putBoolean("Inversion", isInverted);
		SmartDashboard.putNumber("RightTalonPosition", rightTalon1.getPosition());
		SmartDashboard.putNumber("LeftTalonPosition", leftTalon1.getPosition());
		SmartDashboard.putNumber("DistancePerPulse", RobotMap.DISTANCE_PER_PULSE);
		// SmartDashboard.putNumber("HeadingPID", headingPID.getOutput());
		SmartDashboard.putNumber("LeftMotorSpeed", leftMotorSpeed);
		SmartDashboard.putNumber("RightMotorSpeed", rightMotorSpeed);
					

	}

	// method called during teleop from DriveWithJoystick command. executes
	// arcade drive algorithm using joy stick inputs.
	public void JoystickInputs(Joystick RightJoystick, Joystick LeftJoystick) { // teleop
																				// method

		// moveSpeed = stick.getY() * -1; // set variables = Joystick inputs
		// invert the y value to -1 so that pushing the joystick forward gives a
		// positive value

		// rotateSpeed = stick.getX() * -1; //also invert the x value so
		// right/left aren't inverted

		// returnData = arcadeDrive.calculateSpeed(moveSpeed, rotateSpeed);

		//these are the commands for inverting the drive, called into the Inversion command
		
		//if (isInverted) {
			rightTalon1.setInverted(isInverted);
			leftTalon1.setInverted(isInverted);
			if (!isInverted){
				leftMotorSpeed = LeftJoystick.getY();
				rightMotorSpeed = RightJoystick.getY() * -1;
			} else {
				leftMotorSpeed = RightJoystick.getY();
				rightMotorSpeed = LeftJoystick.getY() * -1;
			}
			
//		}
/*
		 else {
			rightTalon1.setInverted(false);
			leftTalon1.setInverted(false);
			leftMotorSpeed = LeftJoystick.getY() * -1;
			rightMotorSpeed = RightJoystick.getY();
		}
		*/
		
		
		SmartDashboard.putNumber("RightTalonPosition", rightTalon1.getPosition());
		SmartDashboard.putNumber("LeftTalonPosition", leftTalon1.getPosition());
		SmartDashboard.putNumber("YAW", (double) ahrs.getYaw());




		//leftMotorSpeed = LeftJoystick.getY() * -1;

		//rightMotorSpeed = RightJoystick.getY();

		leftMotorSpeed = joystickSensitivity.GetOutput(leftMotorSpeed);
		rightMotorSpeed = joystickSensitivity.GetOutput(rightMotorSpeed);
		//joystick sensitivity stuff

		// leftMotorSpeed *= -1; // invert motor speed command.

		// Drive the left and right sides of the robot at the specified speeds.
		rightTalon1.set(rightMotorSpeed * 586 * scale); //set to max RPM
		leftTalon1.set(leftMotorSpeed * 586 * scale);

		

	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoystick());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
