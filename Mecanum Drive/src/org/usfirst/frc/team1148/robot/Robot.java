package org.usfirst.frc.team1148.robot;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive class.
 */
public class Robot extends SampleRobot {
	
	CameraServer server;
    RobotDrive robotDrive;
    Joystick stick;
    Gyro gyro;
    DigitalInput ultraSonic;

    // Channels for the wheels
    final int frontLeftChannel	= 0;
    final int rearLeftChannel	= 3;
    final int frontRightChannel	= 1;
    final int rearRightChannel	= 2;
    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;

    public Robot() {
    	//server = CameraServer.getInstance();
       // server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        //server.startAutomaticCapture("cam0");
        robotDrive = new RobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel);
    	robotDrive.setInvertedMotor(MotorType.kFrontRight, true);	// invert the left side motors
    //	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);		// you may need to change or remove this to match your robot
        robotDrive.setExpiration(0.1);

        stick = new Joystick(joystickChannel);
        gyro  = new Gyro(0);
        ultraSonic = new DigitalInput(0);
        
    }
        

    /**
     * Runs the motors with Mecanum drive.
     */
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        gyro.reset();
        while (isOperatorControl() && isEnabled()) {
        	
        	// Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation.
        	// This sample does not use field-oriented drive, so the gyro input is set to zero.
        	if(ultraSonic.get())
        		robotDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
        	else
        		robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ()/2, gyro.getRate());
            
            Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
        }
    }  
}
