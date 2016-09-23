# javabeanutils

- It's about how to copy java properties to another object with similar structure;

How to install:

1. download target/JavaBeanUtils-0.0.1-SNAPSHOT.jar

2. add local repo

mvn install:install-file -Dfile=path-to-file/JavaBeanUtils-0.0.1-SNAPSHOT.jar -DgroupId=com.github.andersonfonseka \
    -DartifactId=JavaBeanUtils -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

reference: https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html

3. add dependency in your pom.xml:

```xml
		<dependency>
			<groupId>com.github.andersonfonseka</groupId>
		    <artifactId>JavaBeanUtils</artifactId>
		    <version>0.0.1-SNAPSHOT</version>		
		</dependency>
```

How to use:

- See JavaBeanUtilsTest - JUnit Test Case
- The Domain and DTO packages contains both: source and target objects written in the unit test case. 


