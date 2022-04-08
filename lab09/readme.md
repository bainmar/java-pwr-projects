# 1. Informacje

- [1. Informacje](#1-informacje)
  - [1.1. JNI](#11-jni)
    - [1.1.1. Wykonane instrukcje](#111-wykonane-instrukcje)
      - [1.1.1.1. wygenerowanie nagłówka metod macierzystych](#1111-wygenerowanie-nagłówka-metod-macierzystych)
      - [1.1.1.2. wygenerowanie biblioteki współdzielonej](#1112-wygenerowanie-biblioteki-współdzielonej)
      - [1.1.1.3. ustawienie "property JVM"](#1113-ustawienie-property-jvm)
  - [1.2. Chat](#12-chat)
    - [1.2.1. keytool & jarsigner](#121-keytool--jarsigner)

## 1.1. JNI

### 1.1.1. Wykonane instrukcje

#### 1.1.1.1. wygenerowanie nagłówka metod macierzystych

```bash
  javac 
  -h JNI/headers/ 
  -d JNI/target/classes/ 
  JNI/src/main/java/com/bartoszek/jni/JNIStringTest.java
```

#### 1.1.1.2. wygenerowanie biblioteki współdzielonej

```bash
g++ -shared 
    -I /usr/lib/jvm//java-11-openjdk-amd64/include/ 
    -I /usr/lib/jvm/java-11-openjdk-amd64/include/linux/ 
    -I JNI/headers/ -o JNI/lib/libscalarLib.so 
    -fPIC 
    JNI/src/scalarEngine.cpp
```

**-fPIC** - option generate position-independent code (PIC) suitable for use in a
shared library, if supported for the target machine.

#### 1.1.1.3. ustawienie "property JVM"

```-Djava.library.path=/home/marcin/IdeaProjects/Java-zadania/JNI/lib```

var p = new FilePermission("/tmp/*", "read,write");

## 1.2. Chat

### 1.2.1. keytool & jarsigner

```bash
keytool -genkey -keystore my_key -alias Marcin
Enter keystore password:  
Re-enter new password: 
What is your first and last name?
  [Unknown]:  Marcin B
What is the name of your organizational unit?
  [Unknown]:  PWR
What is the name of your organization?
  [Unknown]:  PWR
What is the name of your City or Locality?
  [Unknown]:  Wroclaw
What is the name of your State or Province?
  [Unknown]:  Polska
What is the two-letter country code for this unit?
  [Unknown]:  PL
Is CN=Marcin B, OU=PWR, O=PWR, L=Wroclaw, ST=Polska, C=PL correct?
  [no]:  y
```

```bash
keytool -selfcert -keystore my_key -alias Marcin
Enter keystore password: 
```

```bash
jarsigner -keystore my_key rsalibrary.jar Marcin
Enter Passphrase for keystore: 
jar signed.

Warning: 
The signer's certificate is self-signed.
```

```bash
jarsigner -verify rsalibrary.jar 

jar verified.
```


