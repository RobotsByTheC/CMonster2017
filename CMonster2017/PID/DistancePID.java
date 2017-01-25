package org.usfirst.frc2084.CMonster2017.PID;

import org.usfirst.frc2084.CMonster2017.RobotMap;
import org.usfirst.frc2084.CMonster2017.subsystems.DriveBase;


import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistancePID extends PIDSubsystem {
	
	
	double Output; // Variable used to store the PID output	
static double PIDOutput; // Variable used to store the PID output
static double PIDInput;
	
	// The constructor passes a name for the PIDSubsystem 
	public DistancePID() {  
		
		super("DistancePID", 1, 0.0, 0.0);  //calls the parent constructor with arguments P,I,D
		
		setAbsoluteTolerance(0.1);          // more parameters
		getPIDController().setContinuous(false);
		setInputRange(-10,  10);
	    setOutputRange(-1, 1);
	}
	
	public void Reset(){  //reset the PID controller
		DistancePID.this.Reset();
	}

	
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		//SmartDashboard.putNumber("DistancePID1", DriveBase.AverageDistance);
		return DriveBase.AverageDistance;
	}
	

	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Output = (output);
				
	}
	
	public double getOutput(){
		return Output;
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	  

}
