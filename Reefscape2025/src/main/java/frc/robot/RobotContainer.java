package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstats;
import frc.robot.Subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;

public class RobotContainer {
  private final SwerveSubsystem drivebase = new SwerveSubsystem();
  private final CommandXboxController m_drivController = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
    drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);
  }

  SwerveInputStream driveAngularyVelocity = SwerveInputStream.of(
    drivebase.getSwerveDrive(),
    ()-> m_drivController.getLeftY() * -1,
    ()-> m_drivController.getLeftX() * -1)
    .withControllerRotationAxis(m_drivController::getRightX)
    .deadband(OperatorConstats.DEADBAND)
    .scaleTranslation(0.8)
    .allianceRelativeControl(true);

  SwerveInputStream driveDriecAngle = driveAngularyVelocity.copy().withControllerHeadingAxis(
    m_drivController::getRightX, 
    m_drivController::getRightY)
    .headingWhile(true);

  Command driveFieldOrientedDriectAngle = drivebase.driveFieldOriented(driveDriecAngle);

  Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularyVelocity);

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
