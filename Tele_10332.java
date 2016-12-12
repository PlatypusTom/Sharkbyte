package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Emmanuel on 12/8/2016.
 * Tele-Op code for Team 10332
 */
@TeleOp(name="TeleOp", group="Pushbot")
@Disabled
public class Tele_10332 extends LinearOpMode {

    /* Local OpMode members */
    Hardware_10332 robot = new Hardware_10332();

    //Stating the position and speed of the servos
    double servoPosition = robot.PUSH_START;
    double servoSpeed    = 0.02;

    double servo_1_position = robot.CAP_START;
    double servo_1_speed    = 0.02;

    @Override
    public void runOpMode() throws InterruptedException {

        double left;
        double right;


        //Get mapping from Hardware class
        robot.init(hardwareMap);

        //Send feedback message to Driver's Station
        telemetry.addData("Say", "Hi guys. Your Welcome");
        telemetry.update();

        //Wait for start button to be pushed
        waitForStart();


        while (opModeIsActive()) {

            //Mapping for drive-train
            left = -gamepad1.left_stick_y; //Tank Drive
            right = gamepad1.right_stick_y;
            //If tank drive is not working properly, then put a negate the gamepad on both lines

            robot.Left.setPower(left);    //set power to left side
            robot.Right.setPower(right);  //set power to right side

            //Servo mechanism (for beacons)
            if (gamepad1.left_bumper) {
                servoPosition += servoSpeed;
            } else if (gamepad1.right_bumper) {
                servoPosition -= servoSpeed;
            }

            //Servo mechanisms (for capping)
            if (gamepad1.x) {
                servo_1_position += servo_1_speed;
            } else if (gamepad1.y) {
                servo_1_position -= servo_1_speed;
            }

            // Move servos to new position.
            servoPosition = Range.clip(servoPosition, robot.PUSH_MAX, robot.PUSH_MIN);
            robot.Push1.setPosition(servoPosition);
            robot.Push2.setPosition(servoPosition);

            servo_1_position = Range.clip(servo_1_position, robot.CAP_MIN, robot.CAP_MAX);
            robot.Cap1.setPosition(servo_1_position);
            robot.Cap2.setPosition(servo_1_position);


            telemetry.addData("servo", servoPosition);
            telemetry.update();


            /*//Mechanism
            if (gamepad1.a) {
                robot.Lift1.setPower(0.8);
                if(robot.Touchy.isPressed()) {
                    robot.Lift2.setPower(0.8);
                }
            } else if (gamepad1.b){
                robot.Lift1.setPower(-0.8);
                robot.Lift2.setPower(-0.8);
            } else {
                robot.Lift1.setPower(0);
                robot.Lift2.setPower(0);
            }*/
            if (gamepad1.a) {
                robot.Lift1.setPower(0.8);
                sleep(3000);
                robot.Lift2.setPower(0.8);
            } else if (gamepad1.b) {
                robot.Lift1.setPower(-0.8);
                sleep(3000);
                robot.Lift2.setPower(-0.8);
            } else {
                robot.Lift1.setPower(0);
                robot.Lift2.setPower(0);
            }

            /**
             * I put a touch sensor in the program. You need to put it somewhere on the
             * capping mechanism so that when the first part of it lifts, something hits
             * the touch sensor and because of that, the other part of the mechanism
             * rises as well. Tell me how this works.
             */
/**
 //Mechanism fail safe
 if (gamepad1.a) {
 robot.Lift1.setPower(0.8);
 sleep(3000);
 robot.Lift2.setPower(0.8);
 } else if (gamepad1.b) {
 robot.Lift1.setPower(-0.8);
 sleep(3000);
 robot.Lift2.setPower(-0.8);
 } else {
 robot.Lift1.setPower(0);
 robot.Lift2.setPower(0);
 }
 **/
            /**
             * This is the fail safe if the first one doesn't work
             */




            robot.waitForTick(40);
            idle();
        }
    }
}
