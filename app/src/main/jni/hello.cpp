//
// Created by 13248 on 2018/4/20.
//

#include "jni.h"
#include "School.h"
#include <string>
#include "com_wff_androidtool_nativecode_NativeObject.h"
#include "stdio.h"
#include <android/log.h>

void printMsg(JNIEnv *env, jobject jobject, jstring str);
/*
 * Class:     com_wff_wff_tool_nativecode_NativeObject
 * Method:    printMsg
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_wff_androidtool_nativecode_NativeObject_printMsg
        (JNIEnv *env, jobject jobject, jstring str) {
    jclass jclass = (env)->FindClass("android/util/Log");
    if (jclass == NULL) {
        return;
    }
    jmethodID logMethod = (env)->GetStaticMethodID(jclass, "i",
                                                   "(Ljava/lang/String;Ljava/lang/String;)I");
    if (logMethod == NULL) {
        return;
    }
    School school;
    char *showname = school.showName();
    jstring tag = (env)->NewStringUTF("from jni:");
    jstring msg = (env)->NewStringUTF(showname);

    (env)->CallStaticIntMethod(jclass, logMethod, tag, str);
    (env)->CallStaticIntMethod(jclass, logMethod, tag, msg);
    (env)->DeleteLocalRef(tag);
    (env)->DeleteLocalRef(msg);
};
/*
 * Class:     com_wff_wff_tool_nativecode_NativeObject
 * Method:    makeIntArray
 * Signature: (I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_wff_androidtool_nativecode_NativeObject_makeIntArray__I
        (JNIEnv *env, jobject jobject, jint size) {
    jsize mJsize = 5;
    jintArray array = (env)->NewIntArray(mJsize);
    int array0[5] = {2, 4, 6, 8, 5};
    (env)->SetIntArrayRegion(array, 0, 5, array0);

    return array;

};
/*
 * Class:     com_wff_wff_tool_nativecode_NativeObject
 * Method:    makeIntArray
 * Signature: (II)[[I
 */
JNIEXPORT jobjectArray JNICALL Java_com_wff_androidtool_nativecode_NativeObject_makeIntArray__II
        (JNIEnv *env, jobject o, jint r, jint c) {
    jsize mRJsize = r;
    jsize mCJsize = c;
    jclass intArrCls = (env)->FindClass("[I");
    jobjectArray backArray = (env)->NewObjectArray(mRJsize, intArrCls, NULL);
    for (int i = 0; i < r; i++) {
        jintArray array = (env)->NewIntArray(mCJsize);
        jint array0[5] = {i + 2, i + 4, i + 6, i + 8, i + 5};
        (env)->SetIntArrayRegion(array, 0, 5, array0);
        (env)->SetObjectArrayElement(backArray, i, array);
        //(env)->DeleteLocalRef(array0);
    }

    return backArray;
};
/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    testCObject
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_wff_androidtool_nativecode_NativeObject_testCObject
        (JNIEnv *env, jobject o) {

};
/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    printIntArrayElement
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_com_wff_androidtool_nativecode_NativeObject_printIntArrayElement
        (JNIEnv *env, jobject jo, jintArray jia) {
    jsize size = (env)->GetArrayLength(jia);
    jboolean copy = true;
    jint *nativearray = (env)->GetIntArrayElements(jia, &copy);
    for (int i = 0; i < size; ++i) {
        int j = *nativearray++;
        jstring i_str = (env)->NewStringUTF(std::to_string(j).data());
        printMsg(env, jo, i_str);
    }

};

void printMsg(JNIEnv *env, jobject jobject, jstring str) {
    jclass jclass = (env)->FindClass("android/util/Log");
    if (jclass == NULL) {
        return;
    }
    jmethodID logMethod = (env)->GetStaticMethodID(jclass, "i",
                                                   "(Ljava/lang/String;Ljava/lang/String;)I");
    if (logMethod == NULL) {
        return;
    }
    jstring tag = (env)->NewStringUTF("from jni:");
    (env)->CallStaticIntMethod(jclass, logMethod, tag, tag);
    (env)->CallStaticIntMethod(jclass, logMethod, tag, str);
    (env)->DeleteLocalRef(tag);

};
/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    printByteArrayElement
 * Signature: ([B)V
 */
JNIEXPORT void JNICALL Java_com_wff_androidtool_nativecode_NativeObject_printByteArrayElement
        (JNIEnv *env, jobject o, jbyteArray jba) {
    jbyte *jb = env->GetByteArrayElements(jba, 0);

    char *msgTemp = NULL;
    msgTemp = new char[env->GetArrayLength(jba) + 1];
    memset(msgTemp, 0, env->GetArrayLength(jba) + 1);
    memcpy(msgTemp, jb, env->GetArrayLength(jba));
    msgTemp[env->GetArrayLength(jba)] = 0;
    jstring msg = env->NewStringUTF(msgTemp);
    jboolean exce = env->ExceptionCheck();

    jstring i_str = (env)->NewStringUTF(("exce="+std::to_string(exce)).data());
    printMsg(env, o, msg);
    printMsg(env, o, i_str);
    if (exce <= 0) {

    }
__android_log_print(ANDROID_LOG_ERROR,"------","1111111111");
    env->DeleteLocalRef(msg);
    env->DeleteLocalRef(i_str);
    env->ReleaseByteArrayElements(jba, jb, 0);

};