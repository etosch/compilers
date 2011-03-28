javac -d bin src/circuit/Pallet.java
cd bin
jar cvf ../CircuitLanguage.jar circuit/*
cd ..
jarsigner -keystore compilers -storepass ozymandias -keypass ozymandias CircuitLanguage.jar mykey 