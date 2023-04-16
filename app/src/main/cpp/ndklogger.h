//
// Created by Onuralp SEZER on 16.04.2023.
//

#ifndef DEPTHAIANDROIDTOOLBOX_NDKLOGGER_H
#define DEPTHAIANDROIDTOOLBOX_NDKLOGGER_H

#include <android/log.h>

#define LOG_TAG "depthaiAndroid"

#define  LOGV(...)  __android_log_print(ANDROID_LOG_VERBOSE,    LOG_TAG, __VA_ARGS__)
#define  LOGW(...)  __android_log_print(ANDROID_LOG_WARN,       LOG_TAG, __VA_ARGS__)
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,      LOG_TAG, __VA_ARGS__)
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,       LOG_TAG, __VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,      LOG_TAG, __VA_ARGS__)

#endif //DEPTHAIANDROIDTOOLBOX_NDKLOGGER_H
