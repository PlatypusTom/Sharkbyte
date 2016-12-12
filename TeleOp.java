package org.firstinspires.ftc.teamcode;

import android.view.Display;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Anton on 11/3/2016.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Telop2", group="OctoPi")
public class TeleOp extends LinearOpMode {
    //Initiate variables
    Hardware_10332 robot = new Hardware_10332(); //Use created hardware Class

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        //declaring button variables
        double left;
        double right;
        boolean leftExtended=false;
        boolean rightExtended= false;


        //signal robot waiting with message
        telemetry.addData("Sharkbyte is ready for ", "START");
        telemetry.update();

        //wait for driver to push play
        waitForStart();

        //until stop is pressed
        while(opModeIsActive()){
            //Mapping for drive-train
            left = -gamepad1.left_stick_y; //Tank Drive
            right = gamepad1.right_stick_y;
            //If tank drive is not working properly, then put a negate the gamepad on both lines

            robot.Left.setPower(left);    //set power to left side
            robot.Right.setPower(right);  //set power to right side

            //activate left pusher by pushing left bumper
            if(gamepad1.left_bumper){
                robot.Push1.setPosition(1);
            } else if(gamepad1.left_trigger>0){
                robot.Push1.setPosition(.5);
            }
            if(gamepad1.right_bumper){
                robot.Push2.setPosition(0);
            } else if(gamepad1.right_trigger>0){
                robot.Push2.setPosition(.5);
            }
            //activate right pusher by pushing right bumper

        }



    }
}
