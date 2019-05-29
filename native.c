
#include <jni.h>  /* /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/ */
#include <stdio.h>
#include<stdlib.h>

//#define USE_MY_DBG
#define USE_MY_ERR
#define  FILE_NAME  "native.c"
#include "my_log.h"

/* 
typedef struct {
    char *name;          // Java里调用的函数名 
    char *signature;    // JNI字段描述符, 用来表示Java里调用的函数的参数和返回值类型 
    void *fnPtr;          // C语言实现的本地函数 
} JNINativeMethod;
 */

jstring JNICALL c_run_cmd(JNIEnv *env, jobject cls, jstring str)
{
	const jbyte *cstr;
    
	MY_DBG(" begin ");
	cstr = (*env)->GetStringUTFChars(env, str, NULL);
	if (cstr == NULL) 
    {
        MY_ERR(" cstr == NULL  return NULL ");
		return NULL; /* OutOfMemoryError already thrown */
	}
	MY_DBG(" Get string from java :%s ", cstr);
    
    system(cstr);
    
	(*env)->ReleaseStringUTFChars(env, str, cstr);

	return (*env)->NewStringUTF(env, "return from c");
}


static const JNINativeMethod methods[] = {
	{"run_cmd", "(Ljava/lang/String;)Ljava/lang/String;", (void *)c_run_cmd},
};




/* System.loadLibrary */
JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *jvm, void *reserved)
{
	JNIEnv *env;
	jclass cls;

	MY_DBG("begin");
    
	if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_4)) {
		return JNI_ERR; /* JNI version not supported */
	}
	cls = (*env)->FindClass(env, "push_tool");
	if (cls == NULL) {
		return JNI_ERR;
	}

	/* 2. map java run_cmd <-->c c_run_cmd */
	if ((*env)->RegisterNatives(env, cls, methods, 1) < 0)
		return JNI_ERR;

	MY_DBG("return JNI_VERSION_1_4");
	return JNI_VERSION_1_4;
}

