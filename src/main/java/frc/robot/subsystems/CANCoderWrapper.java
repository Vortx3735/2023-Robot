package frc.robot.subsystems;
import com.ctre.phoenix.sensors.CANCoder;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.Encoder;


public class CANCoderWrapper extends CANCoder {
    private boolean reversed;
    private double distancePerPulse;
    public CANCoderWrapper(int deviceID, String canbus, boolean reversed) {
        super(deviceID, canbus);
        this.reversed = reversed;
    }

    public CANCoderWrapper(int deviceID, String canbus) {
        super(deviceID, canbus);
        this.reversed = false;
    }

    public double getReversed(double input) {
        if ( reversed ) {
            return -input;
        }
        return input;
    }

    public void setRev(boolean rev) {
        reversed = rev;
    }

    public void reset() {
        super.setPosition(0);
    }

    public void setDistancePerPulse(double dis) {
        distancePerPulse = dis;
    }

    public double getDistance() {
        double circumference = Constants.ModuleConstants.kWheelDiameterMeters * Math.PI;
        double distance = super.getPosition() * circumference;
        return distance;
        
    }


}
