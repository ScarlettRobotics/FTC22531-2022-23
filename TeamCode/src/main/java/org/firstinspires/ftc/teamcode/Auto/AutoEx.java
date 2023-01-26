package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous
public class AutoEx extends OpMode {

    public void init(){
        WebcamName webcam = hardwareMap.get(WebcamName.class, "Webcam");// creating an openCvWebcam instance
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcam);// then using camera factory to create a camera instance using the webcam

        // Connects to the camera device
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.setPipeline(Pipeline);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    public void loop(){}
}
