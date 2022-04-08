#include<jni.h>
#include<stdio.h>
#include "com_bartoszek_jni_ScalarTest.h"

/*
 * Class:     com_bartoszek_jni_ScalarTest
 * Method:    multi01
 * Signature: ([Ljava/lang/Double;[Ljava/lang/Double;)Ljava/lang/Double;
 */
JNIEXPORT jobject JNICALL Java_com_bartoszek_jni_ScalarTest_multi01
  (JNIEnv * env, jobject, jobjectArray a, jobjectArray b){
    jdouble sum=0.0;
    jclass doubleClass=env->FindClass("java/lang/Double");
    jmethodID v_result=env->GetStaticMethodID(doubleClass,"valueOf","(D)Ljava/lang/Double;");
    jmethodID v_value=env->GetMethodID(doubleClass,"doubleValue","()D");

    const jsize count=env->GetArrayLength(a);
    for(jsize i=0;i<count;i++){
       jobject v1_e=env->GetObjectArrayElement(a,i);
       jobject v2_e=env->GetObjectArrayElement(b,i);

       jdouble el_a=env->CallDoubleMethod(v1_e,v_value);
       jdouble el_b=env->CallDoubleMethod(v2_e,v_value);

        env->DeleteLocalRef(v1_e);
        env->DeleteLocalRef(v2_e);
       sum=sum+(el_a*el_b);
    }
    return env->CallStaticObjectMethod(doubleClass,v_result,sum);
 }

/*
 * Class:     com_bartoszek_jni_ScalarTest
 * Method:    multi02
 * Signature: ([Ljava/lang/Double;)Ljava/lang/Double;
 */
JNIEXPORT jobject JNICALL Java_com_bartoszek_jni_ScalarTest_multi02
  (JNIEnv *env, jobject obj, jobjectArray a){
        jdouble sum=0.0;
        jclass doubleClass=env->FindClass("java/lang/Double");
        jmethodID v_result=env->GetStaticMethodID(doubleClass,"valueOf","(D)Ljava/lang/Double;");
        jmethodID v_value=env->GetMethodID(doubleClass,"doubleValue","()D");
        const jsize count=env->GetArrayLength(a);

        jclass cls = env->GetObjectClass(obj);
        jmethodID b_array=env->GetMethodID(cls,"getB","()[Ljava/lang/Double;");
        jobjectArray b=(jobjectArray)env->CallObjectMethod(obj,b_array);

        for(jsize i=0;i<count;i++){
               jobject v1_e=env->GetObjectArrayElement(a,i);
               jdouble el_a=env->CallDoubleMethod(v1_e,v_value);
               jobject v2_e=env->GetObjectArrayElement(b,i);
               jdouble el_b=env->CallDoubleMethod(v2_e,v_value);

               env->DeleteLocalRef(v1_e);
               env->DeleteLocalRef(v2_e);

               sum=sum+(el_a*el_b);
            }
            return env->CallStaticObjectMethod(doubleClass,v_result,sum);

  }

/*
 * Class:     com_bartoszek_jni_ScalarTest
 * Method:    multi03
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_bartoszek_jni_ScalarTest_multi03
  (JNIEnv *env, jobject obj){
    jdouble sum=0.0;
            jclass doubleClass=env->FindClass("java/lang/Double");
            jmethodID v_result=env->GetStaticMethodID(doubleClass,"valueOf","(D)Ljava/lang/Double;");
            jmethodID v_value=env->GetMethodID(doubleClass,"doubleValue","()D");



            jclass cls = env->GetObjectClass(obj);
            jmethodID a_array=env->GetMethodID(cls,"getA","()[Ljava/lang/Double;");
            jmethodID b_array=env->GetMethodID(cls,"getB","()[Ljava/lang/Double;");


            jobjectArray a=(jobjectArray)env->CallObjectMethod(obj,a_array);
            jobjectArray b=(jobjectArray)env->CallObjectMethod(obj,b_array);
            const jsize count=env->GetArrayLength(a);
            for(jsize i=0;i<count;i++){
                   jobject v1_e=env->GetObjectArrayElement(a,i);
                   jdouble el_a=env->CallDoubleMethod(v1_e,v_value);
                   jobject v2_e=env->GetObjectArrayElement(b,i);
                   jdouble el_b=env->CallDoubleMethod(v2_e,v_value);

                   env->DeleteLocalRef(v1_e);
                   env->DeleteLocalRef(v2_e);

                   sum=sum+(el_a*el_b);
                }
             jfieldID c=env->GetFieldID(cls,"c","Ljava/lang/Double;");
             env->SetObjectField(obj,c,env->CallStaticObjectMethod(doubleClass,v_result,sum));
  }