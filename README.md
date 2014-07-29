Volley-JSON
===========
Volley is the standered library to work with Parse and JSON with efecient and faster data aconnection introduced in 
Google I/O 2013. It eliminates the network stuff and HTTPconnection setup and disconnect . Its My sampple is with Post JSON parsing and IMage chaching with image size of 223 KB.
You will need to specify POST web API uri in 
      JsonRequestActivity.java 
        makeJsonObjReqPOST()

Integrating Volley to your project.
    You can include in two ways

Create Volley.jar and include as jar dependency to your project.
Include the volley project as Library Dependency in your project.
 
Creating Volley.jar

Import the project into eclipse.
$ cd volley
$ android update project -p . (Generate local.properties file )
$ ant jar
Right click on build.xml file and ‘Run as Ant Task’ , volley.jar would be created in /bin folder.
Library Dependency

Edit the project.properties file and the add the below line
android.library=true
Now right click on your project--> Properties--> Android --> Under Library section, choose ‘Add’ and select ‘Volley’ project as library dependency to your project.
Using Volley involves two main classes RequestQueue and Request.

RequestQueue - Dispatch Queue which takes a Request and executes in a worker thread or if cache found its takes from cache and responds back to the UI main thread.
Request - All network(HTTP) requests are created from this class. It takes main parameters required for a HTTP request like
METHOD Type - GET, POST, PUT, DELETE
URL
Request data (HTTP Body)
Successful Response Listener
Error Listener
Volley Provides two specific implementations of Request.

JsonObjectRequest
StringRequest

JsonObjectRequest

Creates HTTP request which helps in connecting to JSON based response API's. Takes parameters as

HTTP METHOD
A JSON object as request body ( mostly for POST & PUT api's)
Success & Error Listener.
Volley parses the HTTP Response to a JSONObject for you. If your server api's are JSON based, you can straightway go ahead and use JsonObjectRequest

StringRequest

To obtain the HTTP Response as a String, create HTTP Request using StringRequest Takes parameters as

HTTP METHOD
Success & Error Listener.
For more infor you can visit  https://github.com/adamrocker/volley and https://github.com/smanikandan14/Volley-demo

Above repo will work fine with Eclipse and for Android studio you will need to mange gradle.

