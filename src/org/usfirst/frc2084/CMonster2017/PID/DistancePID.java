package org.usfirst.frc2084.CMonster2017.PID;

import org.usfirst.frc2084.CMonster2017.RobotMap;
import org.usfirst.frc2084.CMonster2017.subsystems.DriveBase;


import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistancePID extends PIDSubsystem {
	
	
	double Output; // Variable used to store the PID output	
static double PIDOutput; // Variable used to store the PID output
static double PIDInput;
	
	// The constructor passes a name for the PIDSubsystem 
	public DistancePID() {  
		
		super("DistancePID", 0.14, 0.0, 0.01);  //calls the parent constructor with arguments P,I,D
		//working PIDs: 0.24, 0, 0
		
		setAbsoluteTolerance(0.2);       // more parameters
		getPIDController().setContinuous(false);
		setInputRange(-10,  10);
	    setOutputRange(-0.25, 0.25); //1/5 speed
	    
	    LiveWindow.addActuator("DistancePID", "DistancePID", getPIDController());
	}
	
	public void ResetPID(){  //reset the PID controller
		getPIDController().reset();
	}

	
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return RobotMap.AverageDistance;
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
