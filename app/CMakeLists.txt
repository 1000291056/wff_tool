# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_BUILD_TYPE Release)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(CMAKE_CXX_FLAGS_DEBUG "$ENV{CXXFLAGS} -O0 -Wall -g -ggdb")
#支持-std=gnu++11
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

set(OPENCV_PATH D:/Self/Project/opencv-3.4.3-android-sdk/OpenCV-android-sdk/sdk/native/)
set(NDK_INCLUDE_PATH  D:/Tool/Ndk/android-ndk-r15c/android-ndk-r15c/sysroot/usr)
include_directories(${OPENCV_PATH}/jni/include
        ${NDK_INCLUDE_PATH}/include
        )
#####opencv第三方静态库开始#####

#1.libIlmImf
add_library(libIlmImf STATIC IMPORTED)
set_target_properties(libIlmImf PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/3rdparty/libs/${ANDROID_ABI}/libIlmImf.a)
#2.liblibjasper
add_library(liblibjasper STATIC IMPORTED)
set_target_properties(liblibjasper PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/3rdparty/libs/${ANDROID_ABI}/liblibjasper.a)
#3.liblibjpeg
add_library(liblibjpeg STATIC IMPORTED)
set_target_properties(liblibjpeg PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/3rdparty/libs/${ANDROID_ABI}/liblibjpeg-turbo.a)
#4.liblibpng
add_library(liblibpng STATIC IMPORTED)
set_target_properties(liblibpng PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/3rdparty/libs/${ANDROID_ABI}/liblibpng.a)
#5.liblibtiff
add_library(liblibtiff STATIC IMPORTED)
set_target_properties(liblibtiff PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/3rdparty/libs/${ANDROID_ABI}/liblibtiff.a)
#6.liblibwebp
add_library(liblibwebp STATIC IMPORTED)
set_target_properties(liblibwebp PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/3rdparty/libs/${ANDROID_ABI}/liblibwebp.a)
#7.libtbb
add_library(libtbb STATIC IMPORTED)
set_target_properties(libtbb PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/3rdparty/libs/${ANDROID_ABI}/libtbb.a)

#####opencv第三方静态库结束#####

#####opencv静态库开始#####

#1.libopencv_calib3d
add_library(libopencv_calib3d STATIC IMPORTED)
set_target_properties(libopencv_calib3d PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_calib3d.a)
#2.libopencv_core
add_library(libopencv_core STATIC IMPORTED)
set_target_properties(libopencv_core PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_core.a)
#3.libopencv_features2d
add_library(libopencv_features2d STATIC IMPORTED)
set_target_properties(libopencv_features2d PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_features2d.a)
#4.libopencv_flann
add_library(libopencv_flann STATIC IMPORTED)
set_target_properties(libopencv_flann PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_flann.a)
#5.libopencv_highgui
add_library(libopencv_highgui STATIC IMPORTED)
set_target_properties(libopencv_highgui PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_highgui.a)
#6.libopencv_imgcodecs
add_library(libopencv_imgcodecs STATIC IMPORTED)
set_target_properties(libopencv_imgcodecs PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_imgcodecs.a)
#7.libopencv_imgproc
add_library(libopencv_imgproc STATIC IMPORTED)
set_target_properties(libopencv_imgproc PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_imgproc.a)
#8.libopencv_ml
add_library(libopencv_ml STATIC IMPORTED)
set_target_properties(libopencv_ml PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_ml.a)
#9.libopencv_objdetect
add_library(libopencv_objdetect STATIC IMPORTED)
set_target_properties(libopencv_objdetect PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_objdetect.a)
#10.libopencv_photo
add_library(libopencv_photo STATIC IMPORTED)
set_target_properties(libopencv_photo PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_photo.a)
#11.libopencv_shape
add_library(libopencv_shape STATIC IMPORTED)
set_target_properties(libopencv_shape PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_shape.a)
#12.libopencv_stitching
add_library(libopencv_stitching STATIC IMPORTED)
set_target_properties(libopencv_stitching PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_stitching.a)
#13.libopencv_superres
add_library(libopencv_superres STATIC IMPORTED)
set_target_properties(libopencv_superres PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_superres.a)
#14.libopencv_video
add_library(libopencv_video STATIC IMPORTED)
set_target_properties(libopencv_video PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_video.a)
#15.libopencv_videoio
add_library(libopencv_videoio STATIC IMPORTED)
set_target_properties(libopencv_videoio PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_videoio.a)
#16.libopencv_videostab
add_library(libopencv_videostab STATIC IMPORTED)
set_target_properties(libopencv_videostab PROPERTIES IMPORTED_LOCATION
        ${OPENCV_PATH}/staticlibs/${ANDROID_ABI}/libopencv_videostab.a)

#####opencv静态库结束#####

add_library( # Sets the name of the library.
        hello

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/jni/hello.cpp

        src/main/jni/School.cpp)


find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)
add_library( # Sets the name of the library.
        OpencvStudy
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        src/main/jni/MyThread.cpp
        src/main/jni/NativeStudyOpencv.cpp)

target_link_libraries( # Specifies the target library.
        OpencvStudy

        # Links the target library to the log library
        # included in the NDK.
        #opencv静态库
        libopencv_shape libopencv_stitching libopencv_objdetect libopencv_superres
        libopencv_videostab libopencv_calib3d libopencv_features2d libopencv_highgui
        libopencv_videoio libopencv_imgcodecs libopencv_video libopencv_photo
        libopencv_ml libopencv_imgproc libopencv_flann libopencv_core

        #opencv依赖的静态库
        libIlmImf liblibjasper liblibjpeg liblibpng liblibtiff liblibwebp libtbb
        ${log-lib})

target_link_libraries( # Specifies the target library.
        hello

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})