#!/bin/bash 

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

CLASSPATH="libs/antlr-2.7.7.jar:libs/cdi-api-1.1-PFD.jar:libs/classmate-1.3.0.jar:libs/dom4j-1.6.1.jar:libs/el-api-2.2.jar:libs/geronimo-jta_1.1_spec-1.1.1.jar:libs/hibernate-commons-annotations-5.0.1.Final.jar:libs/hibernate-core-5.2.1.Final.jar:libs/hibernate-jpa-2.1-api-1.0.0.Final.jar:libs/jandex-2.0.0.Final.jar:libs/javassist-3.20.0-GA.jar:libs/javax.inject-1.jar:libs/jboss-interceptors-api_1.1_spec-1.0.0.Beta1.jar:libs/jboss-logging-3.3.0.Final.jar:libs/jsr250-api-1.0.jar:libs/sqlite-jdbc-3.8.11.2.jar"
echo CLASSPATH=$CLASSPATH
#export CLASSPATH=$CLASSPATH

#CLASSPATH=/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/antlr-2.7.7.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/cdi-api-1.1-PFD.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/classmate-1.3.0.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/dom4j-1.6.1.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/el-api-2.2.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/geronimo-jta_1.1_spec-1.1.1.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/hibernate-commons-annotations-5.0.1.Final.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/hibernate-core-5.2.1.Final.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/hibernate-jpa-2.1-api-1.0.0.Final.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/jandex-2.0.0.Final.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/javassist-3.20.0-GA.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/javax.inject-1.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/jboss-interceptors-api_1.1_spec-1.0.0.Beta1.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/jboss-logging-3.3.0.Final.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/jsr250-api-1.0.jar:/home/pedro/desenvolvimento/workspace-java9/TesteHibernate/libs/sqlite-jdbc-3.8.11.2.jar     

# compile
echo Compiling ...
$JAVA_BIN/javac -mp libs -classpath libs/hibernate-core-5.2.1.Final.jar -d build -release 9 -modulesourcepath src $(find src -name "*.java")
#$JAVA_BIN/javac -classpath $CLASSPATH -d build -modulesourcepath src $(find src -name "*.java")


# modules
echo Creating Modules ...

echo Creating Module: MODULO
cp src/modulo/*.properties build/modulo
cp src/modulo/*.xml build/modulo
cp -Rf src/modulo/META-INF build/modulo
$JAVA_BIN/jar --create --file mlib/modulo@1.0.jar --main-class teste.MainApp --module-version 1.0 -C build/modulo .

echo Executing ...
$JAVA_BIN/java -cp $CLASSPATH -mp mlib:libs -m modulo