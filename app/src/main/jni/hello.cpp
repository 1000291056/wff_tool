//
// Created by 13248 on 2018/4/20.
//

#include "jni.h"

/*
 * Class:     com_wff_wff_tool_nativecode_NativeObject
 * Method:    printMsg
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_wff_wff_1tool_nativecode_NativeObject_printMsg
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
    jstring tag = (env)->NewStringUTF("from jni:");
    jstring msg = (env)->NewStringUTF("aaa");
    (env)->CallStaticIntMethod(jclass, logMethod, tag, str);
};
/*
 * Class:     com_wff_wff_tool_nativecode_NativeObject
 * Method:    makeIntArray
 * Signature: (I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_wff_wff_1tool_nativecode_NativeObject_makeIntArray__I
        (JNIEnv *env, jobject jobject, jint size) {
    jsize mJsize = 5;
    jintArray array = (env)->NewIntArray( mJsize);
    int array0[5] = {2, 4, 6, 8, 5};
    (env)->SetIntArrayRegion( array, 0, 5, array0);
    return array;

};
/*
 * Class:     com_wff_wff_tool_nativecode_NativeObject
 * Method:    makeIntArray
 * Signature: (II)[[I
 */
JNIEXPORT jobjectArray JNICALL Java_com_wff_wff_1tool_nativecode_NativeObject_makeIntArray__II
        (JNIEnv *env, jobject o, jint r, jint c)
{
    jsize mRJsize = r;
    jsize mCJsize = c;
    jclass intArrCls = (env)->FindClass("[I");
    jobjectArray backArray = (env)->NewObjectArray(mRJsize, intArrCls,NULL);
    for(int i=0;i<r;i++){
        jintArray array = (env)->NewIntArray(mCJsize);
        jint array0[5] = {i+2, i+4, i+6, i+8, i+5};
        (env)->SetIntArrayRegion( array, 0, 5, array0);
        (env)->SetObjectArrayElement(backArray,i,array);
        //(env)->DeleteLocalRef(array0);
    }

    return backArray;
};
