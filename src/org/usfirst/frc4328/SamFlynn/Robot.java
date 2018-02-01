// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc4328.SamFlynn;

import org.usfirst.frc4328.SamFlynn.commands.AutoLL;
import org.usfirst.frc4328.SamFlynn.commands.AutoLM;
import org.usfirst.frc4328.SamFlynn.commands.AutoLR;
import org.usfirst.frc4328.SamFlynn.commands.AutoRL;
import org.usfirst.frc4328.SamFlynn.commands.AutoRM;
import org.usfirst.frc4328.SamFlynn.commands.AutoRR;
import org.usfirst.frc4328.SamFlynn.commands.DriveForwards;
import org.usfirst.frc4328.SamFlynn.commands.TestAutonomous;
import org.usfirst.frc4328.SamFlynn.subsystems.CubeManipulator;
import org.usfirst.frc4328.SamFlynn.subsystems.DriveTrain;
import org.usfirst.frc4328.SamFlynn.subsystems.Elevator;
import org.usfirst.frc4328.SamFlynn.subsystems.Winch;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	Command autonomousCommand;
	SendableChooser<Integer> autoChooser = new SendableChooser<>();
	SendableChooser<String> autoPositionChooser = new SendableChooser<>();

	public static OI oi;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	public static Winch winch;
	public static CubeManipulator cubeManipulator;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		driveTrain = new DriveTrain();
		elevator = new Elevator();
		winch = new Winch();
		cubeManipulator = new CubeManipulator();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// Add commands to Autonomous Sendable Chooser
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

		autoChooser.addDefault("Put cube in switch", new Integer(1));
		autoChooser.addObject("Put cube in scale", new Integer(2));
		autoChooser.addObject("Go forwards", new Integer(3));

		autoPositionChooser.addDefault("Left", "Left");
		autoPositionChooser.addObject("Middle", "Middle");
		autoPositionChooser.addObject("Right", "Right");

		CameraServer.getInstance().startAutomaticCapture();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
		SmartDashboard.putData("Auto Mode", autoChooser);
		SmartDashboard.putData("Auto Postition", autoPositionChooser);
	}

	/**
	 * This function is called when the disabled button is hit. You can use it to
	 * reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {
		determineAutonomous();
		autonomousCommand = null;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {

		determineAutonomous();

		if (autonomousCommand != null)
			autonomousCommand.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	public void updateSmartDashboard() {
		// SmartDashboard.putNumber("Left Ultrasonic", driveTrain.getLeftSonic());
		// SmartDashboard.putNumber("Right Ultrasonic", driveTrain.getRightSonic());
		// SmartDashboard.putNumber("Left Encoder", driveTrain.getLeftEncoder().get());
		SmartDashboard.putNumber("Right Encoder", driveTrain.getRightEncoder().get());
	}

	public void determineAutonomous() {

		char fieldData;
		if (DriverStation.getInstance().getGameSpecificMessage() != null)
			fieldData = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		else
			fieldData = 'F';

		// redneck exception policy
		if (autoChooser.getSelected() != null && autoPositionChooser.getSelected() != null) {

			// If switch cube deposit is selected
			if (autoChooser.getSelected().equals(1)) {
				switch (autoPositionChooser.getSelected()) {

				case "Left":
					if (fieldData == 'L') {
						// left code
						autonomousCommand = new AutoLL();

					} else {
						// right code
						autonomousCommand = new AutoRL();

					}
					break;
				case "Middle":
					if (fieldData == 'L') {
						// left code
						autonomousCommand = new AutoLM();

					} else {
						// right code
						autonomousCommand = new AutoRM();

					}
					break;
				case "Right":
					if (fieldData == 'L') {
						// left code
						autonomousCommand = new AutoLR();

					} else {
						// right code
						autonomousCommand = new AutoRR();

					}
					break;

				default:
					autonomousCommand = new TestAutonomous();
					break;

				}
			}

			// If scale cube deposit is selected
			if (autoChooser.getSelected().equals(2)) {

			}

			// If go straight forwards is selected
			if (autoChooser.getSelected().equals(3)) {
				// drive forwards for 100 encoder distance
				autonomousCommand = new DriveForwards(1635, 0.8);
			}

		}

	}

}