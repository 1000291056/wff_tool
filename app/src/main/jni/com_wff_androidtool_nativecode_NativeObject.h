/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_wff_androidtool_nativecode_NativeObject */

#ifndef _Included_com_wff_androidtool_nativecode_NativeObject
#define _Included_com_wff_androidtool_nativecode_NativeObject
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    printMsg
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_wff_androidtool_nativecode_NativeObject_printMsg
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    makeIntArray
 * Signature: (I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_wff_androidtool_nativecode_NativeObject_makeIntArray__I
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    makeIntArray
 * Signature: (II)[[I
 */
JNIEXPORT jobjectArray JNICALL Java_com_wff_androidtool_nativecode_NativeObject_makeIntArray__II
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    testCObject
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_wff_androidtool_nativecode_NativeObject_testCObject
  (JNIEnv *, jobject);

/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    printIntArrayElement
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_com_wff_androidtool_nativecode_NativeObject_printIntArrayElement
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_wff_androidtool_nativecode_NativeObject
 * Method:    printByteArrayElement
 * Signature: ([B)V
 */
JNIEXPORT void JNICALL Java_com_wff_androidtool_nativecode_NativeObject_printByteArrayElement
  (JNIEnv *, jobject, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif