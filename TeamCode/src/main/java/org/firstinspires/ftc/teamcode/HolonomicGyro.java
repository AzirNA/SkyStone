package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import pkg3939.Robot3939;

/**
 * Created by maryjane on 11/6/2018.
 * after 1st meet
 * mecanum wheels
 * Hi, edit
 */

@TeleOp(name="HolonomicGyro", group="Iterative Opmode")
//@Disabled
public class HolonomicGyro extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //Declaration of the motors and servos goes here
    Robot3939 robot = new Robot3939();

    public static final boolean earthIsFlat = true;


    @Override //when init is pressed
    public void runOpMode(){

        //Naming, Initialization of the hardware, use this deviceName in the robot controller phone
        //use the name of the object in the code
        robot.initMotors(hardwareMap);
        robot.initServos(hardwareMap);
        robot.initIMU(hardwareMap);
        robot.setFront(hardwareMap);

        robot.useEncoders(false);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double LX = -gamepad1.left_stick_x, LY = gamepad1.left_stick_y, RX = -gamepad1.right_stick_x;

            //forks
            robot.setRightClaw(gamepad1.b);//pressing a changes fork position, up to down, or vice versa
            robot.setLeftClaw(gamepad1.x);
            robot.hookFoundation(gamepad1.a);//pressing a changes claw position, up to down, or vice versa
            robot.setSpeed(gamepad1.left_bumper, gamepad1.right_bumper);

            double angle = robot.getAngle();
            if(angle < 0)
                angle += 360.0;

            //robot drive
            double coords[] = robot.getComponents(LX, LY, angle);
            double newLX = coords[0];
            double newLY = coords[1];

            robot.drive(newLX,newLY,RX);


            telemetry.addData("Drive", "Holonomic");
            telemetry.addData("Global Heading", angle);
            telemetry.addData("speed", robot.speed);
            telemetry.update();
        }
    }
}
