//
// Created by YCWB0029 on 2019/7/5.
//

#include "MyThread.h"
#include "android/log.h"

 void *test::MyThread::product(void *param) {
    while (!exit) {
        //锁住资源
        pthread_mutex_lock(&mutex);
        while (count >= COUNT_MAX) {
            pthread_cond_wait(&c_cond, &mutex);
        }
        count++;
        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "生产一个商品");
        pthread_cond_signal(&c_cond);
        //释放资源
        pthread_mutex_unlock(&mutex);
    }

    return nullptr;
}

 void *test::MyThread::consume(void *param) {
    while (!exit) {
        //锁住资源
        pthread_mutex_lock(&mutex);
        while (count <= 0) {
            pthread_cond_wait(&c_cond, &mutex);
        }
        count--;
        __android_log_print(ANDROID_LOG_ERROR, "from_jni", "消费一个商品");
        pthread_cond_signal(&c_cond);
        //释放资源
        pthread_mutex_unlock(&mutex);
    }
    return nullptr;
}

void test::MyThread::startTask() {
    pthread_t  pthread_p,pthread_c1,pthread_c2;
    pthread_create(&pthread_p,NULL,product,NULL);
    pthread_create(&pthread_c1,NULL,consume,NULL);
    pthread_create(&pthread_c2,NULL,consume,NULL);

}
