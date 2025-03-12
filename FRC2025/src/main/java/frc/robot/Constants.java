// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * copp0junstants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

//gyro calibration constant
  public static final double VoltsPerDPS = 0.0128;
//encoder CPR
  public static final int CountsPerRevolution = 8192;

  public static final double TRACK_WIDTH = Units.inchesToMeters(27);
  public static final double WHEEL_BASE = Units.inchesToMeters(27);
  public static final int kGyroPort = 0;

  public static final int kRightRearID = 9;
  public static final int kLeftRearID = 2;
  public static final int kRightFrontID = 4;
  public static final int kLeftFrontID = 3;

  public static final int kLeftElevID = 6;
  public static final int kRightElevID = 5;
  

}
