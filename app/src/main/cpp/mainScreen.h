//
// Created by Onuralp SEZER on 16.04.2023.
//

#ifndef DEPTHAIANDROIDTOOLBOX_MAINSCREEN_H
#define DEPTHAIANDROIDTOOLBOX_MAINSCREEN_H

#include<jni.h>
#include <string>
#include <libusb.h>
#include <depthai/depthai.hpp>
#include <opencv2/opencv.hpp>
#include "cvDaiUtils.h"
#include "ndklogger.h"


extern "C" JNIEXPORT void JNICALL
Java_com_os_depthaiandroidtoolbox_MainViewModel_startDevice(JNIEnv *env,
                                                            jobject object,
                                                            jstring
                                                            model_path,
                                                            int rgbWidth,
                                                            int rgbHeight);


// Set DepthAi Device
std::shared_ptr<dai::Device> device;

// Set Outputs
std::shared_ptr<dai::DataOutputQueue> qRgb, qDepth, qDet;

// Detection Image
cv::Mat detection_img;

// Detection Label
static std::string detection_label;

// Neural network
std::vector<uint8_t> model_buffer;
static std::atomic<bool> syncNN{true};
std::vector<dai::ImgDetection> detections;

// Closer-in minimum depth, disparity range is doubled (from 95 to 190):
static std::atomic<bool> extended_disparity{true};
auto maxDisparity = extended_disparity ? 190.0f : 95.0f;

// Better accuracy for longer distance, fractional disparity 32-levels:
static std::atomic<bool> subpixel{false};
// Better handling for occlusions:
static std::atomic<bool> lr_check{false};



#endif //DEPTHAIANDROIDTOOLBOX_MAINSCREEN_H
