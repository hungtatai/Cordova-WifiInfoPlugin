PhoneGap WifiInfoPlugin
==============

* License - The MIT License
* Test on cordova 2.6.0 (PhoneGap 2.6.0)


Install Step (On Eclipse)
--------------------------

1. Import File System to folder **src**, select **WifiInfoPlugin.java**
2. Edit **res\xml\config.xml**, add `<plugin name="WifiInfoPlugin" value="org.apache.cordova.plugin.WifiInfoPlugin"/>` into `<plugins> </plugins>`
3. add `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>` into **AndroidManifest.xml**
4. Import **WifiInfoPlugin.js** into .html

API
-----

### Example 1
	window.plugins.WifiInfo.get(function(wifi){ console.log(wifi); });

### Example 2
	window.plugins.WifiInfo.get(function(wifi){ 
		alert(wifi.SSID); 
	});
	
### Output Format
    object(6): {
       activity:  object(8): {
          MacAddress:  string(17): "4c:aa:16:ad:c8:xx"
          NetworkId:  number: 8
          SSID:  string(6): "HYHome"
          LinkSpeed:  number: 135
          HiddenSSID:  boolean: false
          RSSI:  number: -46
          IpAddress:  number: 67217600
          BSSID:  string(17): "78:44:76:de:f1:xx"
       }
       available:  array(2): {
          [0]:  object(5): {
             capabilities:  string(24): "[WPA-PSK-CCMP][WPS][ESS]"
             level:  number: -49
             frequency:  number: 2462
             BSSID:  string(17): "78:44:76:de:f1:xx"
             SSID:  string(6): "HYHome"
          }
          [1]:  object(5): {
             capabilities:  string(20): "[WPA2-PSK-CCMP][ESS]"
             level:  number: -62
             frequency:  number: 2412
             BSSID:  string(17): "fc:f5:28:5e:f4:xx"
             SSID:  string(7): "CHT9489"
          }
       }
    } at file:///android_asset/www/js/index.js:40


Changelog
-----

* 2013/4/5 
  * adjust output format
  * add **available network**
