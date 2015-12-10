propserver-java-client
===============
Java client for Property Server available at    https://github.com/panbhatt/propserver

### Features
It provides the client API implementation for Property Manager server.
* Retrieve all the API for a Project by its Name/Env/Release
* Provider refresh() to refresh the keys at runtime while the application is running.

### Install

    Soon will be available on MVN Repository, for time, please build yourself. 

## Introduction

This is lightweight java module for using Property Manager server. It provides the client implementation of the API which makes it very useful and easy to retrive the properties (KEYS) of a project from the centralized Property Server.


Here is an example on how to use it:

```java
import com.propserver.java.client.*

String  projectName = "VisaCheckout" ;
String propManagerUrl = "http://localhost:3000" ;
String environment = "DEV" ;
String release = "0.0" ;
            
PropManager propManager = PropManager.getInstance(propManagerUrl, projectName, environment, release) ;

System.out.println(propManager.get("KEY_NAME");

```

## Property Manager configuration parameters

* `propManagerUrl`: A URL that points to the API Endpoint of PROPERTY MANAGER SERVER   [ REQUIRED ]
* `projectName`: Name of the project for which we want the properties are to be taken from the property Server [ REQUIRED ]
* `environment`: Name of the managed environment for which the properties are to be taken from the property Server [ REQUIRED ]
* `release`: Name of the release like 'v1.0', '0.0' for which the properties are being are to be taken from the property server [ REQUIRED ]

[See more]: https://github.com/panbhatt/propserver

## Methods

### get(keyName)
Main method. Returns the value of the key for the PROJECT/ENV/RELEASE combination being present at the property server. Will be undefined if the key is not present.

### getAllKeys()
This will retrieve all the keys of for the PROJECT/ENV/RELEASE combination being maintained at the property server.

### refresh()
Refresh the internal cache by fetching the set of keys for the PROJECT/ENV/RELEASE combination.



## LICENSE - "MIT License"


Copyright (c) 2015 Pankaj Bhatt (panbhatt@gmail.com)

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

