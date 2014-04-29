Cordova WifiInfoPlugin
==============

* License - The MIT License
* Test on Cordova 3.4.0


Install Step (Cordova CLI)
--------------------------

### 
	cordova plugin add https://github.com/blakebyrnes/org.apache.cordova.wifiinfo

API
-----

### Example 1
```javascript
window.wifi.lan;			//Connected WiFi info
window.wifi.networks;	    //Array of the visible WiFi networks
```

### Example 2
```javascript
window.wifi.refresh();
```

Changelog
-----
* 2014/4/29
  * Listening for cordova connect was causing conflicts with NetworkConnection plugin.  Removed and simply call wifi.refresh() when network connection state changes in the network connect plugin.
* 2014/4/17 
  * Change wifi object structure
  * Fix SSID string format
* 2014/4/3 
  * Add compatibility with Cordova 3.4.0
  * Change output structure
  * Add plugman compatibility
* 2013/4/5 
  * Adjust output format
  * Add **available network**
