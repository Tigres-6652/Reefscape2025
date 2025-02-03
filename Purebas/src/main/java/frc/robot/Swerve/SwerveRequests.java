package frc.robot.Swerve;

import com.ctre.phoenix6.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveModule.SteerRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveRequests extends SubsystemBase {

  public SwerveRequests() {}

  private final SwerveRequest.FieldCentric m_driveRequest = new SwerveRequest.FieldCentric()
    .withDeadband( 0.1).withRotationalDeadband(0.1)
    .withDriveRequestType(DriveRequestType.OpenLoopVoltage)
    .withSteerRequestType(SteerRequestType.MotionMagicExpo);

  private final Joystick m_joystick = new Joystick(0);
  public final SwerveDrivetrain m_drivetrain = TunerConstants.createDrivetrain();

  @Override
  public void periodic() {
    m_drivetrain.setControl(m_driveRequest
      .withVelocityX(-m_joystick.getRawAxis(0))
      .withVelocityY(-m_joystick.getRawAxis(0))
      .withRotationalRate(m_joystick.getRawAxis(0) - m_joystick.getRawAxis(0))
      );
  }
}