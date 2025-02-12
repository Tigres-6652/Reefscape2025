package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstats;
import frc.robot.Subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;

public class RobotContainer {
  private final SwerveSubsystem drivebase = new SwerveSubsystem();
  private final CommandXboxController m_drivController = new CommandXboxController(0);

  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
    () -> m_drivController.getLeftY() * -1,
    () -> m_drivController.getLeftX() * -1)
    .withControllerRotationAxis(m_drivController::getRightX)
    .deadband(OperatorConstats.DEADBAND)
    .scaleTranslation(0.8)
    .allianceRelativeControl(true);

  SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(
    m_drivController::getRightX,                                                                      
    m_drivController::getRightY)
    .headingWhile(true);

  SwerveInputStream driveRobotOriented = driveAngularVelocity.copy().robotRelative(true).allianceRelativeControl(false);

  SwerveInputStream driveAngularVelocityKeyboard = SwerveInputStream.of(drivebase.getSwerveDrive(),
    () -> -m_drivController.getLeftY(),
    () -> -m_drivController.getLeftX())
    .withControllerRotationAxis(() -> m_drivController.getRawAxis(2))
    .deadband(OperatorConstats.DEADBAND)
    .scaleTranslation(0.8)
    .allianceRelativeControl(true);

  SwerveInputStream driveDirectAngleKeyboard     = driveAngularVelocityKeyboard.copy()
    .withControllerHeadingAxis(
    () -> Math.sin(m_drivController.getRawAxis(2) * Math.PI) * (Math.PI *2),
    () -> Math.cos(m_drivController.getRawAxis(2) * Math.PI) * (Math.PI *2))
    .headingWhile(true);

  public RobotContainer() {
    configureBindings();
    DriverStation.silenceJoystickConnectionWarning(true);
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
