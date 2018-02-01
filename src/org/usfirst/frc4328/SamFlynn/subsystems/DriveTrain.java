// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4328.SamFlynn.subsystems;

import org.usfirst.frc4328.SamFlynn.Robot;
import org.usfirst.frc4328.SamFlynn.RobotMap;
import org.usfirst.frc4328.SamFlynn.commands.CurveDrive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {

	//Drive stuff
    private final SpeedController left1 = RobotMap.driveTrainLeft;
    
    private final SpeedController right1 = RobotMap.driveTrainRight;
    
    private final DifferentialDrive driveTrain = RobotMap.driveTrain;
    
    //Sesors
    private final Ultrasonic leftSonic = RobotMap.leftSonic;
    private final Ultrasonic rightSonic = RobotMap.rightSonic;
    
    private final Encoder leftEncoder = RobotMap.leftEncoder;
    private final Encoder rightEncoder = RobotMap.rightEncoder;

    private final Servo cameraServo = RobotMap.cameraServo;
    
    @Override
    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        setDefaultCommand(new CurveDrive());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void curveDrive() {
    	driveTrain.curvatureDrive(-Robot.oi.driver1.getY(), Robot.oi.driver1.getTwist(), Robot.oi.driver1.getTriggerPressed());
    }
    
    public void normalDrive() {
    	driveTrain.arcadeDrive(-Robot.oi.driver1.getY(), Robot.oi.driver1.getTwist());
    }
    
    public void normalDrive(double y, double z) {
    	driveTrain.arcadeDrive(y, z);
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	driveTrain.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void stop() {
    	RobotMap.driveTrain.arcadeDrive(0.0,0.0);
    }
    
    public double getLeftSonic() {
    	return leftSonic.getRangeInches();
    }

    public double getRightSonic() {
    	return rightSonic.getRangeInches();
    }
    
	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}
	
	public void moveCameraServo() {
		cameraServo.setAngle((-1*(Robot.oi.driver1.getRawAxis(3))*22.5)+157.5);
		SmartDashboard.putNumber("Camera Servo Angle", cameraServo.getAngle());
		SmartDashboard.putNumber("Camera Slider Thing", Robot.oi.driver1.getRawAxis(3));
	}


}
