package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSub extends SubsystemBase{
    private WPI_TalonSRX intakeMotor;

    public IntakeSub(int id) {
        intakeMotor = new WPI_TalonSRX(id);
    }

    public void run(double speed) {
        intakeMotor.set(speed);
    }

}
