# Cordova WifiInfo Plugin

* License - The MIT License
* Test on Cordova 3.4.0


##Install Step (Cordova CLI)

`cordova plugin add https://github.com/dippi/org.apache.cordova.wifiinfo`

## API

### Get WifiInfo

Retrieve the wifi information available from the last scan.  
(TODO: handle options to require a new scan and fresh data)

```javascript
navigator.wifi.getWifiInfo(success, error, options);
```

### Watch WifiInfo

Require a new wifi scan as soon as possible and
retrieve continuatively the obtained data.  
(TODO: handle options to enable/disable the scan request)

```javascript
id = navigator.wifi.watchWifiInfo(success, error, options);
```

### Clear Watch

Unwatch wifi updates

```javascript
navigator.wifi.clearWatch(id);
```

### On Success Data

```javascript
{
	connection: {
		BSSID: BSSID,
		HiddenSSID: HiddenSSID,
		SSID: SSID,
		MacAddress: MacAddress,
		IpAddressInt: IpAddressInt,
		IpAddress: IpAddress,
		NetworkId: NetworkId,
		RSSI: RSSI,
		LinkSpeed: LinkSpeed
	},
	
	networks: [
		{
			BSSID: BSSID,
			SSID: SSID,
			frequency: frequency,
			level: level,
			capabilities: capabilities
		},
		...
	]
}
```

Changelog
-----
* 2014/5/19
  * Code refactor (Exposed only callback based interface on `navigator` inspired by `geolocation` api)
  * Possibility to watch/unwatch for changes on the wifi info
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
