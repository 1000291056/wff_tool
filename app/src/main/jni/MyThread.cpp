//
// Created by YCWB0029 on 2019/7/5.
//

#include "MyThread.h"
#include "../../../../../../../Tool/Ndk/android-ndk-r15c/android-ndk-r15c/sysroot/usr/include/android/log.h"

void *test::MyThread::product(void *param) {
    while (!exit) {
        //锁住资源
        pthread_mutex_lock(&mutex);
        while (count >= COUNT_MAX){
            pthread_cond_wait(&c_cond, &mutex);
        }
        count++;
        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "生产一个商品");
        //释放资源
        pthread_mutex_unlock(&mutex);
    }

    return nullptr;
}

void *test::MyThread::consume(void *param) {
    while (!exit) {
        //锁住资源
        pthread_mutex_lock(&mutex);

        //释放资源
        pthread_mutex_unlock(&mutex);
    }
    return nullptr;
}
