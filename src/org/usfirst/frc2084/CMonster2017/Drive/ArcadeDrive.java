package org.usfirst.frc2084.CMonster2017.Drive;

public class ArcadeDrive {
	
	  double leftMotorSpeed;  // variables used to calculate motor speed
	  double rightMotorSpeed;
	  double moveSpeed;
	  double rotateSpeed;
	  double[] robotSpeed = new double [2];
	  static double Output;
	  static double usePIDOutput;
	
	
	
	public double[] calculateSpeed (double moveSpeed,double rotateSpeed){
		
		 // Do the calculations for arcade drive.
        if (moveSpeed > 0.0) {
            if (rotateSpeed > 0.0) {
                leftMotorSpeed = moveSpeed - rotateSpeed;
                rightMotorSpeed = Math.max(moveSpeed, rotateSpeed);
            } else {
                leftMotorSpeed = Math.max(moveSpeed, -rotateSpeed);
                rightMotorSpeed = moveSpeed + rotateSpeed;
            }
        } else {
            if (rotateSpeed > 0.0) {
                leftMotorSpeed = -Math.max(-moveSpeed, rotateSpeed);
                rightMotorSpeed = moveSpeed + rotateSpeed;
            } else {
                leftMotorSpeed = moveSpeed - rotateSpeed;
                rightMotorSpeed = -Math.max(-moveSpeed, -rotateSpeed);
            }
        }
        
        // Since one of the drive motors is physically mounted opposite the other, 
        // we need to invert one of the motor speed command.
        rightMotorSpeed *= -1; // invert motor speed command.
        
        robotSpeed[0] = leftMotorSpeed;  // array element 0 is always the left motor speed.
        robotSpeed[1] = rightMotorSpeed;  // array element 1 is always the right motor speed.
		
		
		return robotSpeed;  // return to DriveBasePID
		
	}
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Output = (output);
				
	}
	
	public static double getOutput(){
		return Output;
	}

}
