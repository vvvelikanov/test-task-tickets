## **Test task tickets**

### Environment:
#### Build system, JDK, OS:
* Apache Maven 3.6.3
* Maven home: /usr/share/maven 
* Java version: 17.0.10, vendor: Private Build, runtime: /usr/lib/jvm/java-17-openjdk-amd64
* Default locale: ru_RU, platform encoding: UTF-8
* OS name: "linux", version: "5.4.0-177-generic", arch: "amd64", family: "unix"

### Build and run:
In the project directory:
```
mvn compile
mvn exec:java -Dexec.mainClass="org.example.App"
```

