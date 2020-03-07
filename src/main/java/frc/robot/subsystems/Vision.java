package frc.robot.subsystems;


import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * tv  Whether the limelight has any valid targets (0 or 1)
 * tx  Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
 * ty  Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
 * ta  Target Area (0% of image to 100% of image)
 * ts  Skew or rotation (-90 degrees to 0 degrees)
 * tl  The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
 * TODO Move this to Pantry Vision
 *
 * @author Matthew Morley
 */
public class LimeLight extends SubsystemBase {

    private static LimeLight instance;
    private static Object mutex = new Object();

    public static LimeLight getInstance() {
        LimeLight result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new LimeLight();
            }
        }
        return result;
    }

    NetworkTable table;
    double[] data, angles;
    private static final double x_resolution_low = 320;
    private static final double y_resolution_low = 240;
    private static final double x_resolution_high = 960;
    private static final double y_resolution_high = 720;
    boolean isHighRes = false;
    public PipelinePreset mCurrentPipeline;
    private static final PipelinePreset kDefaultPreset = PipelinePreset.k2dVision;


    private static final int kDefaultPipeline = 1;
    private NetworkTable smartDashboard;

    private LimeLight() {
        this.table = NetworkTableInstance.getDefault().getTable("limelight");
        this.setPipeline(kDefaultPreset);
        smartDashboard = NetworkTableInstance.getDefault().getTable("SmartDashboard");
        smartDashboard.getEntry("Desired Vision Pipeline").setNumber(kDefaultPipeline);
        smartDashboard.addEntryListener("Desired Vision Pipeline",
                (smartDashboard, key, entry, value, flabs) -> {
                    setPipeline((int) value.getDouble());
                },
                EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        table.getEntry("stream").setNumber(2);
    }

    public double[] getData() {
        /** Whether the limelight has any valid targets (0 or 1) */
        NetworkTableEntry tv = table.getEntry("tv");
        /** Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees) */
        NetworkTableEntry tx = table.getEntry("tx");
        /** Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees) */
        NetworkTableEntry ty = table.getEntry("ty");
        /** Target Area (0% of image to 100% of image) */
        NetworkTableEntry ta = table.getEntry("ta");
        /** Skew or rotation (-90 degrees to 0 degrees) */
        NetworkTableEntry ts = table.getEntry("ts");
        /** The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency. */
        NetworkTableEntry tl = table.getEntry("tl");

        double[] data = new double[6];

        data[0] = tv.getDouble(0);
        data[1] = tx.getDouble(0);
        data[2] = ty.getDouble(0);
        data[3] = ta.getDouble(0);
        data[4] = ts.getDouble(0);
        data[5] = tl.getDouble(0);
        return data;
    }

    /** Turn on the Limelight LED */
    public void turnOnLED() {
        table.getEntry("ledMode").setNumber(0);
    }

    public double getTargetXPixels() {
        return table.getEntry("thor").getDouble(0);
    }

    /** Turn off the Limelight LED */
    public void turnOffLED() {
        table.getEntry("ledMode").setNumber(1);
    }

    /**
     * Get the Pose2d of a vision target with an offset applied. This way someone else can account for offset from limelight to bumpers
     * @param //kOffset how far away the front of the bumpers is from the camera (in inches)
     * @param //distanceToShiftBy how far to move everything up/right so it shows up on falcon dashboard
     */


    public enum PipelinePreset {
        kDefault(2), k2dVision(1), k3dVision(0);

        private int id;

        private static PipelinePreset[] values = null;

        public static PipelinePreset fromID(int i) {
            if (PipelinePreset.values() == null) {
                PipelinePreset.values = PipelinePreset.values();
            }
            return PipelinePreset.values[i];
        }

        private PipelinePreset(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    /**
     * Set the pipeline of the limelight
     */
    public void setPipeline(PipelinePreset req_) {
        table.getEntry("pipeline").setNumber(req_.getId());
        this.mCurrentPipeline = req_;
        if (req_.name().contains("3d")) {
            this.isHighRes = true;
        } else {
            this.isHighRes = false;
        }
    }

    public void setPipeline(int req_) {
        if (req_ > PipelinePreset.values().length - 1)
            return;
        PipelinePreset _req_ = PipelinePreset.values()[req_];
        this.mCurrentPipeline = _req_;
        setPipeline(_req_);
    }

    public int getPipeline() {
        return table.getEntry("pipeline").getNumber(0).intValue();
    }

    /**
     * Get the area of the tracked target as a percentage from 0% to 100%
     * @return area as percentage of total area
     */
    public double getTargetArea() {
        return (table.getEntry("ta")).getDouble(0);
    }

    /**
     * Get the dx from crosshair to tracked target
     * @return skew from -90 to 0 degrees
     */
    public double getTargetSkew() {
        return (table.getEntry("ts")).getDouble(0);
    }

    /**
     * Get the latency from camera data to NT entry
     * @return pipeline latency contribution in seconds
     */
    /**
     * Return the number of targets currently being tracked
     * @return currently tracked targets
     */
    public double getTrackedTargets() {
        return (table.getEntry("tv")).getDouble(0);
    }

    /**
     * Estimate the distance to a given target using a measurement
     * @param kTarget the target we are tracking
     * @param mMeasured the target we measured
     */

    public class MeasuredVisionTarget {
        private double x_, y_, width_, height_, area_;

        MeasuredVisionTarget(double x, double y, double width, double height, double area) {
            x_ = x;
            y_ = y;
            width_ = width;
            height_ = height;
            area_ = area;
        }

        public double getX() {
            return x_;
        }

        public double getY() {
            return y_;
        }

        public double getWidth() {
            return width_;
        }

        public double getHeight() {
            return height_;
        }

        public double getArea() {
            return area_;
        }
    }

    public enum LEDMode {
        kON, kOFF;
    }

    public static class SetLEDs extends InstantCommand {
        private LEDMode mode;

        public SetLEDs(LEDMode mode) {
            this.mode = mode;
            setRunWhenDisabled(true);
        }

        @Override
        protected void initialize() {
            if (mode == LEDMode.kON)
                LimeLight.getInstance().turnOnLED();
            if (mode == LEDMode.kOFF)
                LimeLight.getInstance().turnOffLED();
        }

    }

    public static class setPipeline extends InstantCommand {
        private PipelinePreset mode;

        public setPipeline(PipelinePreset mode) {
            this.mode = mode;
            setRunWhenDisabled(true);
        }

        @Override
        protected void initialize() {
            LimeLight.getInstance().setPipeline(mode);
        }

    }

    @Override
    protected void initDefaultCommand() {

    }


    @Override
    public void periodic() {
        // var target = VisionTargetFactory.getHatchDualTarget();
        // var measured = new MeasuredVisionTarget
        // var distance = getDistance(target, mMeasured)
    }

}