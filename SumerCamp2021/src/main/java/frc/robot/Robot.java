// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;

/**
 * The VM is configured to automatically run this class, and to call the run() function when the
 * robot is enabled. If you change the name of this class or the package after creating this
 * project, you must also update the build.gradle file in the project.
 */
public class Robot extends EducationalRobot {
  // PWM channels 0 and 1 respectively
  private final Spark m_leftMotor = new Spark(0);
  private final Spark m_rightMotor = new Spark(1);


  // Assumes a gamepad plugged into channnel 0
  private final XboxController m_controller = new XboxController(0);
  //make sure to check ID with the romi webpage
  private final Servo m_servo = new Servo(4);
  AnalogInput dist = new AnalogInput(0);
 

 // Set up the differential drive controller
 private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   public static double voltageToDist(double voltage){

    return (17.8333/Math.pow(voltage, 0.717466)) - 5.16699;
   }
  @Override
  public void robotInit() {
    m_diffDrive.setSafetyEnabled(false);
    m_diffDrive.setRightSideInverted(false);
  }

  /** This function is run when the robot is enabled. */
  @Override
  public void run() {
    //preforms action once during auto
   
   /*
   Comments in Java
   Comments are not compiled and run, like the rest of a Java program. 
   They are useful to describe what a certain section of code is doing in a program, 
   so other developers reading your code will have a better idea of what is going on.

   They can also be used to temporarily remove code for debugging purposes

    Multi line comments are done like this
   */
    //single line comments are done like this



    /*
    If-statements in Java
    If statements are logic statements in Java that only run the code within them if a condition is met
    For example, in english, the code below could be written as
    if Autonomus mode is enabled, set the robot speed to 0.5
    */
    if(m_ds.isAutonomousEnabled()){
    /*
     When you want to print text to the console, use System.out.println("whatever is inside needs to be in quotes!");
     This can be helpful for debugging!
     Try uncommenting the line of code below to test this out!
     */
      //System.out.println("Auto If Statement");
      m_diffDrive.tankDrive(0.5, 0.5);
    
 
   }
   /*
   While-loops in Java
   The while loop is a logic structure that runs the code with in it again and again until the condition is not true.
   For example, in english, the code below could be written as
   while Autonomus mode is enabled, print out the distance reading every 0.25 seconds.
   try uncommenting the print statement below to test this out!
   */
   while(m_ds.isAutonomousEnabled()){
     
    //System.out.println("While Loop");
    System.out.println(Robot.voltageToDist(dist.getAverageVoltage()));
    Timer.delay(0.25);
   }
    //repeats action while teleoperated mode is enabled
    while (m_ds.isOperatorControlEnabled()){
      
      //basic drive code
      //m_diffDrive.arcadeDrive(m_controller.getRawAxis(1), m_controller.getRawAxis(4), true);
      m_diffDrive.tankDrive(m_controller.getRawAxis(1), m_controller.getRawAxis(3), false);
      
      
      //servo code
      if(m_controller.getXButton()==true){
        m_servo.set(0.5);
      }

      if(m_controller.getYButton()==true){
        m_servo.set(0.0);
      }
      
      }
      
    }

  }

