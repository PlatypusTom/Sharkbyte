package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 12/8/2016.
 * Code behind Team 10332's Tele-Op code
 */

public class Hardware_10332 {

    /* Public OpMode members. */
    public DcMotor Left = null;   //Left Drive Train
    public DcMotor Right = null;  //Right Drive Train

    public DcMotor Lift1 = null;  //front of capping mechanism
    public DcMotor Lift2 = null;  //back of capping mechanism

    public Servo Push1 = null;  //Beacon pusher
    public Servo Push2 = null;

    public Servo Cap1 = null;  //Cap ball holder
    public Servo Cap2 = null;

    public TouchSensor Touchy = null; //touch sensor for mechanism


    public final static double PUSH_MIN = 0.20; //for beacon servos
    public final static double PUSH_MAX = 0.90;
    public final static double PUSH_START = 0.2;

    public final static double CAP_MIN = 0.20; //for capping servos
    public final static double CAP_MAX = 0.90;
    public final static double CAP_START = 0.2;


    /* Local Op-Mode members*/
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize motors
        Left = hwMap.dcMotor.get("left");
        Right = hwMap.dcMotor.get("right");

        //Lift1 = hwMap.dcMotor.get("lift_1");
        //Lift2 = hwMap.dcMotor.get("lift_2");

        Push1 = hwMap.servo.get("push1");
        Push2 = hwMap.servo.get("push2");
        Push1.setPosition(PUSH_START);
        Push2.setPosition(PUSH_START);

        //Cap1 = hwMap.servo.get("cpa1");
        //Cap2 = hwMap.servo.get("cap2");
        //Cap1.setPosition(CAP_START);
        //Cap2.setPosition(CAP_START);

        //Touchy = hwMap.touchSensor.get("touchy");


        // Set motor powers to zero
        Left.setPower(0);
        Right.setPower(0);


        // Set all motors to run without encoders.
        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }



    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */

    public void waitForTick(long periodMs) throws InterruptedException {

        long remaining = periodMs - (long) period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
