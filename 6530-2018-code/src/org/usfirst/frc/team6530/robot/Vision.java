//package org.usfirst.frc.team6530.robot;
//
//import edu.wpi.cscore.CvSink;
//import edu.wpi.cscore.CvSource;
//import edu.wpi.cscore.UsbCamera;
//
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.IterativeRobot;
//
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//
//import java.util.ArrayList;
//
///**
// * Contains methods used for anything vision
// */
//@SuppressWarnings("unused")
//public class Vision extends IterativeRobot {
//
//    /// Name the usb output
//    private UsbCamera cameraOne;
//
//    void startCameraThread(){
//        //Places the vision in a separate thread from everything else as recommended by FIRST, faster
//        new Thread(this::concatCameraStream).start();
//
//    }
//
//    /**
//     * Start stream and push to output stream titled Concat.
//     * This method should only be used for starting the camera stream.
//     */
//        private void concatCameraStream() {
//        cameraOne = CameraServer.getInstance().startAutomaticCapture("One", 0);
//     
//
//        /// Dummy sinks to keep camera connection open.
//        CvSink cvsinkOne = new CvSink("OneSink");
//        cvsinkOne.setSource(cameraOne);
//        cvsinkOne.setEnabled(true);
//
//        /// Matrices to store each image from cameraOne
//        Mat oneSource = new Mat();
//        ///I don't know if we need this, if build fails comment it out
//        /// The ArrayList of One source is needed for the concat
//        ArrayList<Mat> sources = new ArrayList<>();
//        sources.add(oneSource);
//    
//        ///Not entirely sure if we need the below code, but if we decide to go 2 
//        ///cams for double FOV then it will be useful
//        /// Concatenation of both matrices
//        Mat concat = new Mat();
//
//        /// Puts the combined video on the SmartDashboard (I think)
//        /// The width is multiplied by 2 as the dimensions of the stream will have a width two times that of a single webcam
//        CvSource outputStream = CameraServer.getInstance().putVideo("Concat", Constants.CAM_WIDTH, Constants.CAM_HEIGHT);
//
//        while (!Thread.interrupted()) {
//            /// Provide each mat with the current frame
//            cvsinkOne.grabFrame(oneSource);
//            /// Combine the frames into a single mat in the Output and stream the image.
//            Core.hconcat(sources, concat);
//            outputStream.putFrame(concat);
//        }
//    }
//}