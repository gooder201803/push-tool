#ifndef __YZM_DBG__
#define __YZM_DBG__
/* 
how to  use

#define USE_MY_DBG
#define USE_MY_ERR
#define  FILE_NAME  "add.c"
#include "yzm_log.h"


 */

#include <stdio.h> 

#undef MY_DBG

#ifdef USE_MY_DBG
#define MY_DBG(fmt, args...)   printf(FILE_NAME"[%s](%d) " fmt "\n",  __FUNCTION__,__LINE__, ##args)
#else
#define MY_DBG(fmt, args...)   do { } while (0)
#endif




#undef MY_ERR

#ifdef USE_MY_ERR
#define MY_ERR(fmt, args...)   printf(FILE_NAME"[%s](%d)ERR! " fmt "\n",  __FUNCTION__,__LINE__, ##args)
#else
#define MY_ERR(fmt, args...)   do { } while (0)
#endif


#endif
