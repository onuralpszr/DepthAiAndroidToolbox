# DepthAiAndroidToolbox

<p>
    <a href=""><img alt="MIT" src="https://img.shields.io/badge/License-MIT-yellow?logo=MIT&logoColor=white"></a>
    <a href="#"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.8.10-%23E34F26?logo=Kotlin&logoColor=white"></a>
    <a href="#"><img alt="OpenCV" src="https://img.shields.io/badge/OpenCV-4.7.0-5C3EE8?logo=OpenCV&logoColor=white"></a>
    <a href="https://conventionalcommits.org"><img alt="Conventional Commits" src="https://img.shields.io/badge/Conventional%20Commit-1.0.0-FE5196?logo=conventionalcommits&logoColor=white"></a>
    <a href="#"><img alt="android-studio" src="https://img.shields.io/badge/android studio-Giraffe-3DDC84?logo=androidstudio&logoColor=white"></a>
</p>

[![ktlint](https://img.shields.io/badge/ktlint%20code--style-%E2%9D%A4-FF4081)](https://pinterest.github.io/ktlint/)


DepthAi Android Toolbox Application For OAK-D Devices


This android project needs to OpenCV 4.7.0 (and other 4.x.y versions) for Android in [Android Studio](https://developer.android.com/studio) with Native Development Kit (NDK) support.
[Android NDK](https://developer.android.com/ndk) enables you to implement your [OpenCV](https://opencv.org) image processing pipeline in C++ and call that C++ code from Android Kotlin/Java code through JNI ([Java Native Interface](https://en.wikipedia.org/wiki/Java_Native_Interface)).

## Get Submodules
Make sure submodules are initialized and updated 
```
git submodule update --init --recursive
```

## How to use this repository

1. [Download and Install Android Studio](https://developer.android.com/studio)

2. [Install NDK and CMake](https://developer.android.com/studio/projects/install-ndk.md)

3. Clone this repository as an Android Studio project :
     * In Android Studio, click on `File -> New -> Project from Version Control -> Git`
     * Paste this repository *Github URL*, choose a *project directory* and click next.

4. Install *OpenCV Android release* :
    * Download [OpenCV 4.7.0 Android release](https://github.com/opencv/opencv/releases/download/4.7.0/opencv-4.7.0-android-sdk.zip) or download latest available Android release on [OpenCV website](https://opencv.org/releases/).
    * Unzip downloaded file and put **OpenCV-android-sdk** directory next your project and rename folder `opencvsdk470`. If you want to place somewhere else please change path in `settings.gradle` 

    * Optional(For linux) you can run setupOpenCV.sh for automatic download and setup gradle file for opencv 


5. Sync Gradle and run the application on your Android Device!

## Keywords

Kotlin, OpenCV 4, Android, Android Studio, Native, NDK, Native Development Kit, JNI, C++,
