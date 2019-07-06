//
// Created by YCWB0029 on 2019/5/16.
//

#include "NativeStudyOpencv.h"
#include "android/log.h"
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "pthread.h"
#include "semaphore.h"
#include <unistd.h>
//全局环境
#define COUNT_MAX 50

struct Param {
    char name[20];
} param;

JavaVM *globleVm = nullptr;
jobject jo = nullptr;
/**
 * 锁
 */
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t c_cond = PTHREAD_COND_INITIALIZER;
pthread_cond_t p_cond = PTHREAD_COND_INITIALIZER;
volatile int count = 0;
volatile bool exitTask = false;
pthread_t pthread_p, pthread_c1, pthread_c2;
pthread_attr_t pthread_attr;
int max_priority;//最小优先级
int min_priority;//最大优先级
//设置优先级的结构体
sched_param sched_param_value;
//信号量
sem_t c_semt, p_semt;

//void *product(void *param) {
//    __android_log_print(ANDROID_LOG_ERROR, "from_jni", "生产线程开启");
//    while (!exitTask) {
//        //锁住资源
//        pthread_mutex_lock(&mutex);
//        while (count >= COUNT_MAX) {
//            sem_wait(&p_semt);
//        }
//        count++;
//        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "生产一个商品，剩余量%d", count);
//        sem_post(&c_semt);
//        //释放资源
//
//        pthread_mutex_unlock(&mutex);
//        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "生产线程释放锁");
//    }
//    pthread_exit(0);
//
//}
//
//void *consume(void *param) {
//    __android_log_print(ANDROID_LOG_ERROR, "from_jni", "消费线程开启");
//    while (!exitTask) {
//        //锁住资源
//        pthread_mutex_lock(&mutex);
//        while (count <= 0) {
//            sem_wait(&c_semt);
//        }
//        count--;
//        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "消费一个商品,剩余量%d", count);
//        sem_post(&p_semt);
//        //释放资源
//        pthread_mutex_unlock(&mutex);
//        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "消费线程释放锁");
//    }
//    pthread_exit(0);
//}
void *product(void *param) {
    while (!exitTask) {
        //锁住资源
        pthread_mutex_lock(&mutex);
        while (count >= COUNT_MAX) {
            pthread_cond_wait(&p_cond, &mutex);
        }
        count++;
        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "生产一个商品，剩余量%d", count);
        pthread_cond_signal(&c_cond);

        sleep(1);
        //释放资源
        pthread_mutex_unlock(&mutex);
    }
    pthread_exit(0);

}

void *consume(void *param) {
    while (!exitTask) {
        //锁住资源
        pthread_mutex_lock(&mutex);
        while (count <= 0) {
            pthread_cond_wait(&c_cond, &mutex);
        }
        count--;
        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "消费一个商品,剩余量%d", count);
        pthread_cond_signal(&p_cond);
        sleep(1);
        //释放资源
        pthread_mutex_unlock(&mutex);
    }
    pthread_exit(0);
}

void *fun(void *param) {
    __android_log_print(ANDROID_LOG_ERROR, "from_jni", "我在子线程！");
    JNIEnv *env = NULL;
    Param *p;
    if (param != nullptr) {
        p = static_cast<Param *>(param);
    }
    if (globleVm == nullptr) {
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

static void nativeExitTask(JNIEnv *env, jobject jo) {
    exitTask = true;
//    pthread_cond_destroy(&p_cond);
//    pthread_cond_destroy(&c_cond);
    sem_destroy(&p_semt);
    sem_destroy(&c_semt);
}

static void native_study_opencv_nativetest(JNIEnv *Eenv, jobject jobject) {
    __android_log_print(ANDROID_LOG_ERROR, "from_jni", "test_regicter");
    jo = Eenv->NewGlobalRef(jobject);
    pthread_t pthread;
    pthread_create(&pthread, NULL, fun, NULL);
    //初始化信号量
    sem_init(&p_semt, 0, 0);
    sem_init(&c_semt, 0, 0);
    //初始化线程属性
    pthread_attr_init(&pthread_attr);
    //获取最大优先级
    max_priority = sched_get_priority_max(SCHED_OTHER);
    //获取最小优先级
    min_priority=sched_get_priority_min(SCHED_OTHER);
    sched_param_value.sched_priority=max_priority;
    pthread_attr_setschedparam(&pthread_attr,&sched_param_value);


    pthread_create(&pthread_c1, &pthread_attr, consume, NULL);
    pthread_create(&pthread_p, NULL, product, NULL);

    pthread_create(&pthread_c2, &pthread_attr, consume, NULL);
    //cv::Mat mat=
}

static JNINativeMethod methos[] = {
        {"nativetest",     "()V", (void *) native_study_opencv_nativetest},
        {"nativeExitTask", "()V", (void *) nativeExitTask},
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
    globleVm = vm;
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
