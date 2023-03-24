package frc.robot.subsystems;
import com.ctre.phoenix.sensors.CANCoder;


public class CANCoderWrapper extends CANCoder {
    private boolean reversed;
    //private double distancePerPulse;
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

}
