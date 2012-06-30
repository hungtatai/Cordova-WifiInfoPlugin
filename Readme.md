PhoneGap WifiInfoPlugin
==============

* Author: HondaDai
* License - The MIT License
* Test on PhoneGap 1.7.0 (cordova 1.7)


Install Step (On Eclipse)
--------------------------

1. Import File System to folder **src**, select **WifiInfoPlugin.java**
2. Edit **res\xml\plugins.xml**, add `<plugin name="WifiInfoPlugin" value="com.phonegap.plugin.WifiInfoPlugin"/>` into `<plugins> </plugins>`
3. add `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>` into **AndroidManifest.xml**
4. Import **WifiInfoPlugin.js** into .html

API
-----

### Example 1
	window.plugins.WifiInfo.get(function(wifi){ console.log(wifi); });
	> undefined
	> Object
		   BSSID: "00:21:91:08:63:19"
		   HiddenSSID: false
		   IpAddress: -1191139136
		   LinkSpeed: 65
		   MacAddress: "E0:B9:A5:8B:6E:95"
		   NetworkId: 0
		   RSSI: -55
		   SSID: "dlink"
		   __proto__: Object

### Example 2
	window.plugins.WifiInfo.get(function(wifi){ 
		alert(wifi.SSID); 
	});

