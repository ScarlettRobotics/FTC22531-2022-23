package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
//org/openftc/easyopencv/OpenCvCamera.java

/** Manages the webcam of the camera to be used by the autonomous portion of running. */
public class WebcamCore {
    private int webcamView;
    private WebcamName webcamName;
    private OpenCvCamera webcam;
    private Pipeline pipeline;

    // FOR INFO ON WHAT THESE LINES OF CODE ARE DOING, SEE:
    // https://github.com/OpenFTC/EasyOpenCV/blob/master/doc/user_docs/camera_initialization_overview.md
    public void initWebcam (HardwareMap hardwareMap){
        webcamView = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
                hardwareMap.appContext.getPackageName());
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "NAME_OF_CAMERA_IN_CONFIG_FILE");
        pipeline = new Pipeline();

        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, webcamView);
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened ()
            {
                // Usually this is where you'll want to start streaming from the camera (see section 4)
                webcam.startStreaming(400, 300);
            }
            @Override
            public void onError ( int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }

}
