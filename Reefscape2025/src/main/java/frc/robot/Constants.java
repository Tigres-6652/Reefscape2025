package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {
    public static final class  ModuleConstants {
        public static final double kWheelDiametrMeters = Units.inchesToMeters(4);
        public static final double kDriveMotorGearRatio = 1 / 0;
        public static final double kTurningMotorGearRatio = 1 / 0;
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiametrMeters;
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * Math.PI * 2;
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60; 
        public static final double kPTurning = 0.2;   
    }    
}