package frc.robot.commands;

import frc.robot.subsystems.IntakeSub;

public class IntakeCom {
    IntakeSub intake;
    public IntakeCom(IntakeSub inputIntake) {
        intake = inputIntake;
    }

    public void startIntake() {
        intake.run(0.2);
    }

    public void rev() {
        intake.run(-0.2);
    }

    public void stop() {
        intake.run(0);
    }
}
