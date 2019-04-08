# **Gym Attendance Monitoring**

<a href="https://www.dis.uniroma1.it/" title="DIAG"><img src="img/sapienza.jpg" width=1000></a>

**Gym Attendance Monitoring** is a proof of concept of an android application which tracks the user through the gym to monitor the use of the rooms and to have a customer loyalty system. 
The project is part of the [Internet of Things course](http://ichatz.me/Site/InternetOfThings2019) held at the Sapienza University of Rome.

For the correct functioning it is necessary to own the Estimote branded beacons; Microsoft Azure and Grafana technologies will also be used.

* **Authors**:
<br/>- *Andrea Littera* <a href="https://github.com/alittera" title="GitHub"><img src=https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png width=20/></a>
<a href="https://www.linkedin.com/in/andrea-littera/" title="LinkedIn"><img src=https://cdn1.iconfinder.com/data/icons/logotypes/32/square-linkedin-512.png width=20/></a>
<br/>- *Gianmarco Cariggi* <a href="https://github.com/giacar" title="GitHub"><img src=https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png width=20/></a>
<a href="https://www.linkedin.com/in/gianmarco-cariggi-400180174/" title="LinkedIn"><img src=https://cdn1.iconfinder.com/data/icons/logotypes/32/square-linkedin-512.png width=20/></a>
<br/>- *Marco Costa* <a href="https://github.com/marcocosta96" title="GitHub"><img src=https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png width=20/></a>
<a href="https://www.linkedin.com/in/marco-costa-ecs" title="LinkedIn"><img src=https://cdn1.iconfinder.com/data/icons/logotypes/32/square-linkedin-512.png width=20/></a>

* **Technologies**:
<br/><pre>       [Estimote Beacons](https://estimote.com/)                  [Android SDK](https://developer.android.com/)
<br/><a href="https://estimote.com/" title="Estimote"><img src="img/beacon.png" width = 250/></a>   <a href="https://developer.android.com/" title="Android"><img src="img/android.png" width=235/>
</br>        [Azure IoT Hub](https://azure.microsoft.com/en-us/services/iot-hub/)                      [Graphana](https://grafana.com/)
<br/><a href="https://azure.microsoft.com/en-us/services/iot-hub/" title="Azure"><img src="img/azure.png" width=240/></a>    <a href="https://grafana.com/" title="Grafana"><img src="img/grafana.jpg" width=240/></a></pre>

* **Preliminary Presentation**:
<br/><a href="https://www.slideshare.net/AndreaLittera1/connected-gym" title="SlideShare"><img src="img/presentation.jpg" width=300/></a>

## **How does it work**

### **User track** 
<img src="img/estimote.gif" width=1000/>
How beacons work

### **Data on cloud**
<img src="img/azure.gif" width=1000/>
Azure

### **Grafana**
<img src="img/grafana.gif" width=1000/>
How grafana works

## **How to run the app**
* Simply install the *Connected Gym.apk* application on your smartphone (You should allow installation from unknown sources).
* Launch the application on your smartphone
* Grant location permissions
* Turn on **Bluetooth** and **GPS**
* Enter your details in **Account** section
* Click **Save**