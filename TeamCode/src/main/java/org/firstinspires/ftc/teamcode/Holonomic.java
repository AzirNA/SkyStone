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

@TeleOp(name="Holonomic", group="Iterative Opmode")
//@Disabled
public class Holonomic extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //Declaration of the motors and servos goes here
    Robot3939 robot = new Robot3939();

    public static final boolean earthIsFlat = true;

    @Override //when init is pressed
    public void runOpMode(){
        robot.initMotors(hardwareMap);
        robot.initServos(hardwareMap);
        robot.setFront(hardwareMap);
        //robot.initIMU(hardwareMap);
        robot.useEncoders(false);//don't need encoders for teleop

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            //forks
             //robot.servoRight.setPosition(0.3);
             robot.setRightClaw(gamepad1.b);//pressing a changes fork position, up to down, or vice versa
             robot.setLeftClaw(gamepad1.x);
             robot.hookFoundation(gamepad1.a);//pressing a changes claw position, up to down, or vice versa
             robot.setSpeed(gamepad1.left_bumper, gamepad1.right_bumper);

            robot.drive(gamepad1.left_stick_x,
                        gamepad1.left_stick_y,
                        -gamepad1.right_stick_x);

            telemetry.addData("Drive", "Holonomic");
            //telemetry.addData("Global Heading", robot.getAngle());
            telemetry.addData("speed", robot.speed);
            telemetry.addData("left servo", robot.servoLeft.getPosition());
            telemetry.addData("right servo", robot.servoRight.getPosition());
            telemetry.addData("foundationPos", robot.bar.getPosition());

            telemetry.update();
        }
    }
}
