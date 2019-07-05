//
// Created by YCWB0029 on 2019/7/5.
//

#ifndef WFF_TOOL_MYTHREAD_H
#define WFF_TOOL_MYTHREAD_H
#define COUNT_MAX 50;

#include "../../../../../../../Tool/Ndk/android-ndk-r15c/android-ndk-r15c/sysroot/usr/include/sys/types.h"
#include "../../../../../../../Tool/Ndk/android-ndk-r15c/android-ndk-r15c/sysroot/usr/include/pthread.h"
#include "android/log.h"
namespace test {
    /**
     * 锁
     */
    pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
    pthread_cond_t c_cond = PTHREAD_COND_INITIALIZER;
    pthread_cond_t p_cond = PTHREAD_COND_INITIALIZER;
    volatile int count = 0;
    volatile bool exit = false;

    class MyThread {
    public:
        /* 线程执行函数*/
/**
 * 生产线程
 * @param param
 * @return
 */
        void *product(void *param);

/**
 * 消费线程
 * @param param
 * @return
 */
        void *consume(void *param);
    };
}


#endif //WFF_TOOL_MYTHREAD_H
