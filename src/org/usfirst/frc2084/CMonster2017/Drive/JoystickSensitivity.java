package org.usfirst.frc2084.CMonster2017.Drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickSensitivity {
	
	double Sensitivity = 1.0; //sensitivity is half of normal
	double Output;
	
	public double GetOutput(double Input){
		Output = Sensitivity * Math.pow(Input, 3) + (1-Sensitivity) * Input;
		// (1 - Sensitivity) sets the maximum to one
		//Cube the sensitivity because you want a parabola - double symmetrical parabola on either side of the y-axis
		//want a parabola because less sensitive at lower speeds but goes to the same max speed
		
		
		
		return Output;
		
		
		
		
	}

}
