# scaleway-sdk
Java SDK for Scaleway Cloud computing 

# Status
[![Build Status](https://travis-ci.org/segator/scaleway-sdk.svg?branch=master)](https://travis-ci.org/segator/scaleway-sdk)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Code Coverage](https://codecov.io/gh/segator/scaleway-sdk/coverage.svg?branch=master)](https://codecov.io/gh/segator/scaleway-sdk)

**Maven dependency**
```xml
<dependency>
    <groupId>com.github.segator</groupId>
    <artifactId>scaleway-sdk</artifactId>
    <version>0.1-SNAPSHOT</version>
</dependency>
```


```java
//Create Client Instance
ScalewayClient scaleClient = ScalewayFactory.getScalewayClient(accessKey, orgKey);

//Get all My Servers
List<ScalewayServer> allMyServers =scaleClient.getAllServers();

//Get All the Images 
List<ScalewayImage> allTheImages = scaleClient.getAllImages();

//Create Server Instance using a Image by his Id
ScalewayImage xenialImage = scaleClient.getImage("75c28f52-6c64-40fc-bb31-f53ca9d02de9");//ubuntu xenial 16.04
ScalewayServer server = scaleClient.createBasicServer("myTest", xenialImage, ScalewayCommercialType.C2S, "tag1", "tag2");

//Get Organization & User Info
List<ScalewayOrganization> organizations = scaleClient.getAllOrganizations();


//Add new SSH key to an User
ScalewayUserKeyDefinition ssh = new ScalewayUserKey("ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAQEAtIiDxn8RtE1tZLXfv........");

//Execute Task to an a Server Async,The task is executed in background
ScalewayServerTask taskInBackground = scaleClient.executeServerAction(server, ScalewayServerAction.POWER_ON)
//Execute Task to an a Server Sync, The thread is blocked until the task done
ScalewayServerTask taskResult = scaleClient.executeServerActionSync(server, ScalewayServerAction.POWER_ON)

 
scaleClient.addSSHKey(userID, ssh);

```
