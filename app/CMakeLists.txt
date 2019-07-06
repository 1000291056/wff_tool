# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_BUILD_TYPE Release)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(CMAKE_CXX_FLAGS_DEBUG "$ENV{CXXFLAGS} -O0 -Wall -g -ggdb")
#支持-std=gnu++11
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

set(NDK_INCLUDE_PATH D:/Tool/android-ndk-r15c-windows-x86_64/android-ndk-r15c/sysroot/usr)
include_directories(${OPENCV_PATH}/jni/include
        ${NDK_INCLUDE_PATH}/include
        )


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
        src/main/jni/NativeStudyOpencv.cpp)

target_link_libraries( # Specifies the target library.
        OpencvStudy
        ${log-lib})

target_link_libraries( # Specifies the target library.
        hello

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})