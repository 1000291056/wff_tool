//
// Created by YCWB0029 on 2019/5/16.
//

#include "NativeStudyOpencv.h"
#include "android/log.h"
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <opencv2/core/mat.hpp>
#include "pthread.h"
//全局环境

struct Param {
    char name[20];
} param;

JavaVM *globleVm = nullptr;
jobject jo = nullptr;

void *fun(void *param) {
    __android_log_print(ANDROID_LOG_ERROR, "from_jni", "我在子线程！");
    JNIEnv *env = NULL;
    Param *p ;
    if (param != nullptr) {
        p = static_cast<Param *>(param);
    }
    if (globleVm== nullptr){
        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "globleVm==null！");
    }
//    if (globleVm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
//        return nullptr;
//    }


    if (globleVm != nullptr) {
        if (globleVm->AttachCurrentThread(&env, NULL) == JNI_OK) {
//            jclass c = env->FindClass("Lcom/wff/androidtool/nativecode/NativeTest");
            jclass c = env->GetObjectClass(jo);
            jmethodID jmethod = env->GetStaticMethodID(c, "callback", "()V");
            env->CallStaticVoidMethod(c, jmethod, NULL);
            globleVm->DetachCurrentThread();
        }
        pthread_exit(0);
    }
    env->DeleteGlobalRef(jo);
    return nullptr;
}

static void native_study_opencv_nativetest(JNIEnv *Eenv, jobject jobject) {
    __android_log_print(ANDROID_LOG_ERROR, "from_jni", "test_regicter");
    jo=Eenv->NewGlobalRef(jobject);
    pthread_t pthread;
    pthread_create(&pthread, NULL, fun, NULL);
    //cv::Mat mat=
}

static JNINativeMethod methos[] = {
        {"nativetest", "()V", (void *) native_study_opencv_nativetest},
};

/**
 * 注册本地生活
 * @param engv
 * @return
 */
static int registerNatives(JNIEnv *engv) {

    jclass clazz;
    clazz = engv->FindClass("com/wff/androidtool/opencv/OpencvStudy");   //找到类
    if (clazz == nullptr) {
        return JNI_FALSE;
    }
    int res = engv->RegisterNatives(clazz, methos, sizeof(methos) / sizeof(methos[0]));
    if (res < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}
/**
 * java system.load入口
 * @param vm
 * @param reserved
 * @return
 */
JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    jint result = -1;
    globleVm=vm;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }

    //为了方便管理我们将不同java类中的native方法分别注册
    registerNatives(env); //注册MainActivity类的native方法
    return JNI_VERSION_1_4;
}

void NativeStudyOpencv::study() {

}

NativeStudyOpencv::~NativeStudyOpencv() = default;
