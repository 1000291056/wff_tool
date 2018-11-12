LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := native_lib
LOCAL_SRC_FILES := hello.cpp

include $(BUILD_SHARED_LIBRARY)