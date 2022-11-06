#include <jni.h>
#include <string>

#include <libusb.h>
#include "depthai/depthai.hpp"

extern "C" JNIEXPORT jstring
Java_com_example_depthaiandroidtoolbox_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    auto r = libusb_set_option(nullptr, LIBUSB_OPTION_ANDROID_JNIENV, env);

    // Connect to device and start pipeline
    auto device = dai::Device(dai::OpenVINO::VERSION_2021_4, dai::UsbSpeed::SUPER);

    // Create pipeline
    dai::Pipeline pipeline;

    device.startPipeline(pipeline);
    //enum class UsbSpeed : int32_t { UNKNOWN, LOW, FULL, HIGH, SUPER, SUPER_PLUS };

    auto usbVal = static_cast<int32_t>(device.getUsbSpeed());
    std::string usbSpeedText = "DepthAi UsbSpeed: "+std::to_string(usbVal);
    return env->NewStringUTF(usbSpeedText.c_str());
}