#!/bin/bash 

# set JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-9-oracle
JAVA_BIN=$JAVA_HOME/bin
echo Java: $JAVA_BIN

# clean
echo Cleaning ...
rm -rf build
mkdir build
rm -rf mlib
mkdir mlib

# download deps
rm -rf libs
mkdir libs
echo Maven deps ...
mvn package

#list all dependencies
LIBS=$(find libs -name "*.jar")
#replace spaces ' ' with :
CLASSPATH=$(echo $LIBS | sed 's/ /:/g')
echo CLASSPATH=$CLASSPATH     

# compile
echo Compiling ...
$JAVA_BIN/javac -mp libs -d build -modulesourcepath src $(find src -name "*.java")

# module
echo Creating Module: Iris
cp -Rf src/iris/META-INF build/iris
$JAVA_BIN/jar --create --file mlib/iris@1.0.jar --main-class br.unb.cic.iris.MainApp --module-version 1.0 -C build/iris .

echo Executing ...
$JAVA_BIN/java -cp $CLASSPATH -mp mlib:libs -m iris