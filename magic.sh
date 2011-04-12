cd src
javac -d ../bin circuit/Pallet.java
javac -d ../bin lang/Test.java
cd ../bin
jar cvf ../CircuitLanguage.jar circuit/* lang/*
cd ..
jarsigner -keystore compilers -storepass ozymandias -keypass ozymandias CircuitLanguage.jar mykey 