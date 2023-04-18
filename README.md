# DepthAiAndroidToolbox

<p>
    <a href=""><img alt="MIT" src="https://img.shields.io/badge/License-MIT-yellow?logo=MIT&logoColor=white"></a>
    <a href="#"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.8.10-%23E34F26?logo=Kotlin&logoColor=white"></a>
    <a href="#"><img alt="Gradle" src="https://img.shields.io/badge/Gradle-8.0-02303A?logo=Gradle&logoColor=white"></a>
    <a href="#"><img alt="JetpackCompose" src="https://img.shields.io/badge/JetpackCompose-1.4.1-4285F4?logo=JetpackCompose&logoColor=white"></a>
    <a href="#"><img alt="OpenCV" src="https://img.shields.io/badge/OpenCV-4.7.0-5C3EE8?logo=OpenCV&logoColor=white"></a>
    <a href="https://conventionalcommits.org"><img alt="Conventional Commits" src="https://img.shields.io/badge/Conventional%20Commit-1.0.0-FE5196?logo=conventionalcommits&logoColor=white"></a>
    <a href="#"><img alt="android-studio" src="https://img.shields.io/badge/android studio-Giraffe-3DDC84?logo=androidstudio&logoColor=white"></a>
    <a href="#"><img alt="luxonis-depthai-core" src="https://img.shields.io/badge/Depthai_Core-2.21.2-4E26DA.svg?logo=data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+CjxzdmcKICAgdmVyc2lvbj0iMS4xIgogICB2aWV3Qm94PSIwIDAgMTI4LjQ2MDAxIDExMi43MyIKICAgeG1sOnNwYWNlPSJwcmVzZXJ2ZSIKICAgaWQ9InN2ZzI0IgogICBzb2RpcG9kaTpkb2NuYW1lPSJsb2dvX3N5bWJvbC5zdmciCiAgIGlua3NjYXBlOmV4cG9ydC1maWxlbmFtZT0iXFx3c2wkXFVidW50dS0yMC4wNFxob21lXGphbmNpYnVsa2FcZGV2XHdlYnNpdGVccHVibGljXGFzc2V0c1xtYXJrZXRpbmdcYnJhbmRcbG9nby53ZWJwIgogICBpbmtzY2FwZTpleHBvcnQteGRwaT0iOTYiCiAgIGlua3NjYXBlOmV4cG9ydC15ZHBpPSI5NiIKICAgaW5rc2NhcGU6dmVyc2lvbj0iMS4xLjIgKGI4ZTI1YmU4MzMsIDIwMjItMDItMDUpIgogICB3aWR0aD0iMTI4LjQ2MDAxIgogICBoZWlnaHQ9IjExMi43MyIKICAgeG1sbnM6aW5rc2NhcGU9Imh0dHA6Ly93d3cuaW5rc2NhcGUub3JnL25hbWVzcGFjZXMvaW5rc2NhcGUiCiAgIHhtbG5zOnNvZGlwb2RpPSJodHRwOi8vc29kaXBvZGkuc291cmNlZm9yZ2UubmV0L0RURC9zb2RpcG9kaS0wLmR0ZCIKICAgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIgogICB4bWxuczpzdmc9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48ZGVmcwogICBpZD0iZGVmczI4Ij4KCgoKCgoKCgoKICA8L2RlZnM+PHNvZGlwb2RpOm5hbWVkdmlldwogICBpZD0ibmFtZWR2aWV3MjYiCiAgIHBhZ2Vjb2xvcj0iI2ZmZmZmZiIKICAgYm9yZGVyY29sb3I9IiM2NjY2NjYiCiAgIGJvcmRlcm9wYWNpdHk9IjEuMCIKICAgaW5rc2NhcGU6cGFnZXNoYWRvdz0iMiIKICAgaW5rc2NhcGU6cGFnZW9wYWNpdHk9IjAuMCIKICAgaW5rc2NhcGU6cGFnZWNoZWNrZXJib2FyZD0iMCIKICAgc2hvd2dyaWQ9ImZhbHNlIgogICBpbmtzY2FwZTp6b29tPSIyLjgzNTU0ODIiCiAgIGlua3NjYXBlOmN4PSI3MS45NDM3NiIKICAgaW5rc2NhcGU6Y3k9IjU2LjI1MDE0NiIKICAgaW5rc2NhcGU6d2luZG93LXdpZHRoPSIxOTIwIgogICBpbmtzY2FwZTp3aW5kb3ctaGVpZ2h0PSIxMDAxIgogICBpbmtzY2FwZTp3aW5kb3cteD0iLTkiCiAgIGlua3NjYXBlOndpbmRvdy15PSItOSIKICAgaW5rc2NhcGU6d2luZG93LW1heGltaXplZD0iMSIKICAgaW5rc2NhcGU6Y3VycmVudC1sYXllcj0ic3ZnMjQiCiAgIGlua3NjYXBlOnNob3dwYWdlc2hhZG93PSJ0cnVlIgogICBmaXQtbWFyZ2luLXRvcD0iMCIKICAgZml0LW1hcmdpbi1sZWZ0PSIwIgogICBmaXQtbWFyZ2luLXJpZ2h0PSIwIgogICBmaXQtbWFyZ2luLWJvdHRvbT0iMCIgLz4KICA8c3R5bGUKICAgdHlwZT0idGV4dC9jc3MiCiAgIGlkPSJzdHlsZTIiPgogICAgLnN0MCB7CiAgICAgIGZpbGw6ICM1NzI0RTg7CiAgICB9CiAgPC9zdHlsZT4KICA8ZwogICBpZD0iZzY4MSIKICAgY2xpcC1wYXRoPSJub25lIgogICB0cmFuc2Zvcm09InRyYW5zbGF0ZSgtMC41LC0yNy45NykiPjxwYXRoCiAgICAgY2xhc3M9InN0MCIKICAgICBkPSJtIDcyLjYsMTE5LjA1IGMgLTE5LjE0LDAgLTM0LjcxLC0xNS41NyAtMzQuNzEsLTM0LjcyIDAsLTE5LjE0IDE1LjU3LC0zNC43MSAzNC43MSwtMzQuNzEgMTkuMTQsMCAzNC43MSwxNS41NyAzNC43MSwzNC43MSAwLjAxLDE5LjE0IC0xNS41NywzNC43MiAtMzQuNzEsMzQuNzIgbSAwLC05MS4wOCBjIC0yNy40MiwwIC01MC4yNiwxOS41OSAtNTUuMzEsNDUuNTQgSCAwLjUgdiAyMS42NSBoIDE2Ljc5IGMgNS4wNSwyNS45NSAyNy44OSw0NS41NCA1NS4zMSw0NS41NCAzMS4xMywwIDU2LjM2LC0yNS4yMyA1Ni4zNiwtNTYuMzYgMCwtMzEuMTMgLTI1LjIzLC01Ni4zNyAtNTYuMzYsLTU2LjM3IgogICAgIGlkPSJwYXRoMTgiIC8+PHBhdGgKICAgICBjbGFzcz0ic3QwIgogICAgIGQ9Im0gNzIuNiw4Ny4wOSBjIC0xLjUyLDAgLTIuNzYsLTEuMjQgLTIuNzYsLTIuNzYgMCwtMS41MiAxLjI0LC0yLjc2IDIuNzYsLTIuNzYgMS41MiwwIDIuNzYsMS4yNCAyLjc2LDIuNzYgMCwxLjUyIC0xLjI0LDIuNzYgLTIuNzYsMi43NiBtIDAsLTEzLjU3IGMgLTUuOTcsMCAtMTAuODEsNC44NCAtMTAuODEsMTAuODEgMCw1Ljk3IDQuODQsMTAuODEgMTAuODEsMTAuODEgNS45NywwIDEwLjgxLC00Ljg0IDEwLjgxLC0xMC44MSAwLC01Ljk3IC00Ljg0LC0xMC44MSAtMTAuODEsLTEwLjgxIgogICAgIGlkPSJwYXRoMjAiIC8+PC9nPgo8L3N2Zz4K"></a>
</p>

[![ktlint](https://img.shields.io/badge/ktlint%20code--style-%E2%9D%A4-FF4081)](https://pinterest.github.io/ktlint/)


DepthAi Android Toolbox Application For OAK-D Devices

## Description 

This Android application that utilizes the OAK-D camera and OpenCV library to perform YOLOv5 object detection and depth detection. The OAK-D camera provides high-quality images and videos with advanced depth sensing capabilities, while OpenCV is a widely-used computer vision library that provides various image processing and analysis tools.

With this application, users can take advantage of the OAK-D camera's advanced features to detect and track objects in real-time. The YOLOv5 model is used for object detection, which is a state-of-the-art deep learning model that can recognize a wide range of objects with high accuracy. The depth detection feature enables users to obtain information about the distance between objects in the scene, which can be useful for various applications such as robotics, autonomous driving, and augmented reality.


This project needs to OpenCV 4.7.0 (and other 4.x.y versions) for Android in [Android Studio](https://developer.android.com/studio) with Native Development Kit (NDK) support.
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

5. Open your opencv build.gradle and change your compileSdk,minSdkVersion,targetSdkVersion same as your main project. Due to gradle 8.0 you also gonna need to add namespace and enable aidl build config and set min JDK to 17. 

```
    namespace 'org.opencv'

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }

    buildFeatures {
        aidl true
    }

```

6. Sync Gradle and run the application on your Android Device!

## Keywords

Kotlin, OpenCV 4, Android, Android Studio, Native, NDK, Native Development Kit, JNI, C++,
