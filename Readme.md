# CA2 Part 2

- Created a new branch "tut-basic-gradle"

- Created the demo project using spring initializer, with the following settings and dependencies:

![spring init](https://i.imgur.com/2tWuIDJ.png)

- Replaced the src folder of the demo spring project with the one from CA1, along with the webpack.config.js and package.json files.

- Deleted the built folder

- Added the following line to the plugins section of build.gradle, and the following block:
```aidl
id "org.siouan.frontend" version "1.4.1"
```
```aidl
frontend {
nodeVersion = "12.13.1"
assembleScript = "run webpack"
}
```
This allows the application to interact with the frontend code

- Using ```./gradlew bootRun``` we now have the following output in the 8080 port:

![frontend](https://i.imgur.com/23BuZsf.png)

- Added the following task to build.gradle to copy the generated jar file to a dist folder:

```task copyJar(type: Copy) {
    group = "DevOps"
    description = "Backups the generated jar file into the dist folder"
    dependsOn(build)
    from 'build/libs'
    include '*.jar'
    into 'dist'
}
```

![dist](https://i.imgur.com/IjEGEeh.png)

- Added the following task to delete the generated built folder, and the clean.DoFirst block to assign dependency: 
````aidl
task deleteWebpack(type: Delete) {

    delete 'src/main/resources/static/built'
}

clean{
    dependsOn 'deleteWebpack'
}
````

# Alternative (Bazel)

Bazel is a build tool developed by Google in 2015.
Bazel offers unusually strong guarantees about the integrity of its builds and unusually low performance requirements in building them, in order to accommodate Google's needs.

Bazel's low performance requirements are achieved because builds are designed to execute as fast as intrinsically possible given the resources available to them. Tasks are as parallelizable as their dependency chains allow. Unnecessary work is never executed (i.e. “up-to-date” tasks are always skipped). 

Bazel uses Starlark on its Build files, a domain specific language.


#Alternative Implementation

- Installed Bazel and defined its variable path
![bazel version](https://i.imgur.com/YKXgdOm.png)

- Downloaded the template bazel java project from ```https://github.com/bazelbuild/examples```


- Copied the src folder from CA1 and inserted it into the Bazel project.

- Edited the BUILD file to reflect the project structure:
````aidl
java_binary(
    name = "ReactAndSpringDataRestApplication",
    srcs = glob(["src/main/java/com/greglturnquist/payroll/*.java"]),
)
````  

- Tested the command ```bazel build```. The build ran successfully, and it created some additional folders:
![bazel build](https://i.imgur.com/pyGEk1V.png)
  

- Tested the command ```bazel run App``` without success:
![bazel run App](https://i.imgur.com/itoyO38.png)
  
- As instructed, experimented with 
  
```bazel run App --javabase=@bazel_tools//tools/jdk:remote_jdk11```

![bazel run App jdk](https://i.imgur.com/UQSjDoG.png)

again without success.

- Tried to make a task to copy the jar file, adding the following to the BUILD folder:

```aidl
genrule(
    name = "copyjar",
    srcs = ["src/main/resources/static/*"],
    cmd = """
           mkdir dist
           cp $(SRCS) $(@D)
          """,
    outs = ["dist"],
)
```
After several failed attempts, I could not finish the implementation of the alternative.
As a conclusion analysis, Gradle is more intuitive to operate, as well as more user-friendly in terms of documentation and available resources online.
Bazel has a lot of issues with Springboot and the JDK, which makes it an alternative that is not beginner-friendly.



  

  

  
  



