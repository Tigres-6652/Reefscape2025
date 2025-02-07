package frc.robot.Subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.RobotController;
import frc.robot.Constants.ModuleConstants;

public class SwerveModule {
    TalonFX driveMotor;
    TalonFX turningMotor; 
    
    CANcoder absoluteCANcoder;
    PIDController TurningPIDcontrol;

    boolean absoluteCANcoderReversed;
    double absoluteCANcoderOffsedRad;

    public SwerveModule(int driveMotorId, int turningMotorId, double absoluteCANcoderOffsed, Boolean absoluteCANcoderReversed) {
        this.absoluteCANcoderOffsedRad = absoluteCANcoderOffsed;
        this.absoluteCANcoderReversed = absoluteCANcoderReversed;

        driveMotor = new TalonFX(driveMotorId);
        turningMotor = new TalonFX(turningMotorId);

        var ReversetTrue = new TalonFXConfiguration();
        var ReversetFalse = new TalonFXConfiguration();

        ReversetTrue.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        ReversetFalse.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        driveMotor.getConfigurator().apply(ReversetTrue);
        turningMotor.getConfigurator().apply(ReversetFalse);
        
        TurningPIDcontrol = new PIDController(ModuleConstants.kPTurning, 0, 0);
        TurningPIDcontrol.enableContinuousInput(-Math.PI, Math.PI);

        resetEncoder();
    }

    public double getDrivePosition() {
        return driveMotor.getPosition().getValueAsDouble();
    }

    public double getTurningPosition() {
        return turningMotor.getPosition().getValueAsDouble();
    }

    public double getDriveVelocity () {
        return driveMotor.getVelocity().getValueAsDouble();
    }

    public double getTurningVelocity () {
        return turningMotor.getVelocity().getValueAsDouble();
    }

    public double absoluteCANcoderOffsedRad() {
        var signal= turningMotor.getPosition();
        double posicion = signal.getValueAsDouble();
        return posicion * Math.PI * 2;
    }

    public void resetEncoder () {
        driveMotor.setPosition(0);
        turningMotor.setPosition(absoluteCANcoderOffsedRad);
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }

    @SuppressWarnings("deprecation")
    public void setDesiredState(SwerveModuleState state) {
        state = SwerveModuleState.optimize(state, getState().angle);
    }
}