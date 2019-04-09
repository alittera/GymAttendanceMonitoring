# **Gym Attendance Monitoring**

<a href="https://www.dis.uniroma1.it/" title="DIAG"><img src="img/sapienza.jpg" width=1000></a>

**Gym Attendance Monitoring** is a proof of concept of an android application which tracks the user through the gym to monitor the use of the rooms and to have a customer loyalty system. 
The project is part of the [***Internet of Things course***](http://ichatz.me/Site/InternetOfThings2019) held at [***Sapienza University of Rome***](https://www.uniroma1.it/it).

For the correct functioning it is necessary to own the Estimote branded beacons; Microsoft Azure and Grafana technologies will also be used.

* **Authors**:
<br/>- *Andrea Littera* <a href="https://github.com/alittera" title="GitHub"><img src=https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png width=20/></a>
<a href="https://www.linkedin.com/in/andrea-littera/" title="LinkedIn"><img src=https://cdn1.iconfinder.com/data/icons/logotypes/32/square-linkedin-512.png width=20/></a>
<br/>- *Gianmarco Cariggi* <a href="https://github.com/giacar" title="GitHub"><img src=https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png width=20/></a>
<a href="https://www.linkedin.com/in/gianmarco-cariggi/" title="LinkedIn"><img src=https://cdn1.iconfinder.com/data/icons/logotypes/32/square-linkedin-512.png width=20/></a>
<br/>- *Marco Costa* <a href="https://github.com/marcocosta96" title="GitHub"><img src=https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png width=20/></a>
<a href="https://www.linkedin.com/in/marco-costa-ecs" title="LinkedIn"><img src=https://cdn1.iconfinder.com/data/icons/logotypes/32/square-linkedin-512.png width=20/></a>

* **Technologies**:
<br/><div style="float: left; text-align: center; padding-right: 20px;">[Estimote Beacons](https://estimote.com/)<br/><a href="https://estimote.com/" title="Estimote"><img src="img/beacon.png" width = 250/></a></div><div style="float: left"></div><div style="float: left; text-align: center">[Android SDK](https://developer.android.com/)<br/><a href="https://developer.android.com/" title="Android"><img src="img/android.png" width=235/></div><div style="clear: both"></div><div style="float: left; text-align: center; padding-right: 30px;">[Azure IoT Hub](https://azure.microsoft.com/en-us/services/iot-hub/)<br/><a href="https://azure.microsoft.com/en-us/services/iot-hub/" title="Azure"><img src="img/azure.png" width=240/></a></div><div style="float: left; text-align: center">[Grafana](https://grafana.com/)<br/><a href="https://grafana.com/" title="Grafana"><img src="img/grafana.jpg" width=240/></a></div><div style="clear: both"></div>

* **Presentations**:
<br/><pre>     **Preliminary Presentation**                        **Final Presentation**
<br/><a href="https://www.slideshare.net/AndreaLittera1/connected-gym" title="Preliminary Presentation"><img src="img/presentation.gif" width=300/></a> <a href="https://www.slideshare.net/AndreaLittera1/connected-gym-final-presentation" title="Final Presentation"><img src="img/presentationFinal.gif" width=400 height=220/></a></pre>

* **Blog post**:
<br/><pre>                    **Hackster**
<br/><a href="https://www.hackster.io/151941/gym-attendance-monitoring-via-beacon-123eef" title="Hackster Blog"><img src="img/hackster.gif" width=400/></a></pre>

## **How does it work**

### **Track User** 
<img src="img/estimote.gif" width=1000/>

GPS cannot be used to track a user inside a building. This requires a different solution. The best method is to use beacons, ***Bluetooth Low Energy*** (***BLE***) components that communicate their presence to the devices around it. It is powered by a non-replaceable lithium battery. They work like a lighthouse, so your smartphone can know which is the nearest one. For our purpose, we have used the ***Estimote Beacons***. We have associated a room with each beacon, so you can know which room the user is in. To integrate them into the application, [***Estimote SDK***](https://github.com/Estimote/Android-Fleet-Management-SDK) have been used.

### **Store and analyse data**
<img src="img/azure.gif" width=1000/>

To store user data from the application, you need to store it in a database on the cloud. To do this we used ***Azure IoT Hub***, an open and flexible cloud platform as a service that supports open-source SDKs and multiple protocols. This makes it possible to securely connect, monitor, and manage billions of devices to develop Internet of Things applications. Two databases were used: the first one for user's data and the second one for events (entrance to a room, change of room, abandonment of the room...). The data is sent by the application to Azure thanks to [***Azure IoT SDKs***](https://github.com/Azure/azure-iot-sdks).

### **Graphicize the analyzed data**
<img src="img/grafana.gif" width=1000/>

To graph the data stored on Azure, we used ***Grafana***,an open-source, general purpose dashboard and graph composer, which runs as a web application. Thanks to the [***Azure Monitoring plugin for Grafana***](https://grafana.com/plugins/grafana-azure-monitor-datasource), it's possible to queries Azure SQL database to create statics useful for gym's owner.

## **How to run the app**
* Simply install the ***Connected Gym.apk*** application on your smartphone (You should allow installation from unknown sources).
* Launch the application on your smartphone
* Grant location permissions
* Turn on **Bluetooth** and **GPS**
* Enter your details in **Account** section
* Click **Save**