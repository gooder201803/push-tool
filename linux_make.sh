#!/bin/bash

javac push_tool.java
gcc -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/ -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux -fPIC -shared -o libnative.so native.c

