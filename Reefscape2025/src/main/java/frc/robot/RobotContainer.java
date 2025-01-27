package frc.robot;

import com.ctre.phoenix6.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveModule.SteerRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Swerve.SwerveDrive;

public class RobotContainer {
  static SwerveRequest.FieldCentric m_driveRequest = new SwerveRequest.FieldCentric()
      .withDeadband(0.1).withRotationalDeadband(0.1) 
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage)
      .withSteerRequestType(SteerRequestType.MotionMagicExpo);
   
  public final static SwerveDrive m_drivetrain = RobotContainer.createDrivetrain();
         
  static Joystick m_joystick = new Joystick(0);
                    
    private static SwerveDrive createDrivetrain() {}



    public RobotContainer() {        
      m_drivetrain.setControl(m_driveRequest
        .withVelocityX(m_joystick.getRawAxis(1))
        .withVelocityY(m_joystick.getRawAxis(1))
        .withRotationalRate(m_joystick.getRawAxis(1)));
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
