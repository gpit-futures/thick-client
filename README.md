
# thick-client
## Developers notes
### To set up the thick-client demonstrator in a Java IDE...
- Ensure [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html), [Git](https://git-scm.com/downloads) and [Maven](https://maven.apache.org/download.cgi) are installed and set-up on your machine (including your Java IDE of choice e.g. Eclipse)
 - Clone the [thick-client repository](https://github.com/gpit-futures/thick-client.git) using git clone (or your favourite GUI tool) using the URL https://github.com/gpit-futures/thick-client.git to an appropriate local directory.
 - Import the project into your IDE as a Maven project.  The pom.xml file containing the build configuration required for import can be found in the local directory created above.
### To run via the Java IDE
 - The main class is com.answerdigital.thick.FXMLApplication.  Simply run this class as a standard Java application.
 ### To run via an executable JAR file
 - In a console, Navigate to the root directory of the thick-client project as defined above (the directory containing the pom.xml file).
 - Build the project using the 'mvn clean install' command.
 - The JAR file to execute can be found in the /target directory.
### Dependencies
- A working version of the [framework](https://github.com/gpit-futures/frame) in order to demonstrate patient context sharing between the two.
- A working version of the [core module](https://github.com/gpit-futures/pulse) within the framework.
- A working [authentication server](https://github.com/gpit-futures/auth-server)
### Configuration
All configuration for the application can be found in... **{root_directory}/src/main/resources/application-env.properties**
Below is a description of each properties function...

#### base.url
The URL for a working version of core, required to retrieve the same patient set available to the core module.  Only change the host part (in bold) or the system will fail...

**http://ec2-18-130-26-44.eu-west-2.compute.amazonaws.com** /api/patients/detailed
#### auth.url
The URL for a working version of the authentication server, required to authenticate the user's access to core.  Again only change the host part of the resource...

**http://ec2-18-130-14-227.eu-west-2.compute.amazonaws.com** /oauth/token

#### client.id
The client application ID for use by the authentication server, **must not be changed or authentication will fail**.
#### client.secret
The client password for use by the authentication server, **must not be changed or authentication will fail**.
#### search.query
The search query for internal use when interrogating the core application for patient data, **any change risks search failure**.
#### response.format
The format in which data is transferred between the thick-client and core, currently JSON.
#### javafx.stage.resizable
If set to true the application window can be resized, risking layout issues.  If set to false the window size remains fixed, preventing layout issues.
