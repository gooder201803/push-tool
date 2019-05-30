# push-tool
it is a push tool, Convenient Android Developer push so to Android device.
it only use in linux system now, such as unbuntu,mint...
usage:
. linux_make.sh
. run.sh

warning: 
if you use open-jdk 1.8.0, you linux_make.sh need be the following instead:
gcc -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/ -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux  -fPIC -shared -o libnative.so native.c
