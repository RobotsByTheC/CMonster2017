package org.usfirst.frc2084.CMonster2017.subsystems;



import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.vision.CameraServer;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ni.vision.NIVision; 
import com.ni.vision.NIVision.Image;

/*visionThread = new Thread();
// Get the UsbCamera from CameraServer
UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
// Set the resolution
camera.setResolution(640, 480);

// Get a CvSink. This will capture Mats from the camera
CvSink cvSink = CameraServer.getInstance().getVideo();
// Setup a CvSource. This will send images back to the Dashboard
CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

// Mats are very memory expensive. Lets reuse this Mat.
Mat mat = new Mat();

//SmartDashboard."Vision", cvSink);

// This cannot be 'true'. The program will never exit if it is. This
// lets the robot stop this thread when restarting robot code or
// deploying.
while (!Thread.interrupted()) {
	// Tell the CvSink to grab a frame from the camera and put it
	// in the source mat.  If there is an error notify the output.
	if (cvSink.grabFrame(mat) == 0) {
		// Send the output the error.
		outputStream.notifyError(cvSink.getError());
		// skip the rest of the current iteration
		continue;
	}
	// Put a rectangle on the image
	Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
			new Scalar(255, 255, 255), 5);
	// Give the output stream a new image to display
	outputStream.putFrame(mat);
	
};
visionThread.setDaemon(true);
visionThread.start();
*/


public class VisionStream {
	//used for switching between two cameras
	
	//int currSession;
	//int sessionfront;
	//int sessionback;
	//private Image frame;
	//public static boolean switchCamera;
	
	

	  
	
	
	public void Init() {
		
	//Camera switching stuff	
		
	/*sessionfront = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	sessionback = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	currSession = sessionfront;
	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	NIVision.IMAQdxConfigureGrab(currSession);
	
	
	
	NIVision.IMAQdxGrab(currSession, frame, 1);
	NIVision.IMAQdxStartAcquisition(currSession);
	CameraServer.getInstance().setImage(frame);	
	
	SmartDashboard.putNumber("Camera", currSession);
	SmartDashboard.putNumber("sessionfront", sessionfront);
	SmartDashboard.putNumber("sessionback", sessionback);
	
	
	
	if (switchCamera == true){
		if(currSession == sessionfront){
			NIVision.IMAQdxStopAcquisition(currSession);
			currSession = sessionback;
			NIVision.IMAQdxConfigureGrab(currSession);
			NIVision.IMAQdxStartAcquisition(currSession);
		} else if (currSession == sessionback){
			NIVision.IMAQdxStopAcquisition(currSession);
			currSession = sessionfront;
			NIVision.IMAQdxConfigureGrab(currSession);
			NIVision.IMAQdxStartAcquisition(currSession);
		}
	}
	
	}
	


	private void changeCam(int sessionfront2) {
		// TODO Auto-generated method stub
		
	}

	private void execute() {
		// TODO Auto-generated method stub
		

		*/
	}

	}
