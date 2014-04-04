package org.apache.cordova.wifiinfo;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class cordovaWifiInfo extends CordovaPlugin { 
		
		public cordovaWifiInfo() {
		}
		
		@Override
		public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getWifiInfo")) {
            Context context = cordova.getActivity().getApplicationContext();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, this.loadData(context));
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return true;
        }
        return false;
    }

		private JSONObject loadData(Context context) {
			WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
  		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			JSONObject obj = new JSONObject();
	    try {
	        JSONObject activity = new JSONObject();
	        activity.put("BSSID", wifiInfo.getBSSID());
	        activity.put("HiddenSSID", wifiInfo.getHiddenSSID());
	        activity.put("SSID", wifiInfo.getSSID());
	        activity.put("MacAddress", wifiInfo.getMacAddress());
	        activity.put("IpAddress", wifiInfo.getIpAddress());
	        activity.put("NetworkId", wifiInfo.getNetworkId());
	        activity.put("RSSI", wifiInfo.getRssi());
	        activity.put("LinkSpeed", wifiInfo.getLinkSpeed());
	        obj.put("activity", activity); 
	    
	        if(wifiManager.getScanResults() != null){ 
	            JSONArray available = new JSONArray();
	            for (ScanResult scanResult : wifiManager.getScanResults()) {
	                JSONObject ap = new JSONObject();
	                ap.put("BSSID", scanResult.BSSID);
	                ap.put("SSID", scanResult.SSID);
	                ap.put("frequency", scanResult.frequency);
	                ap.put("level", scanResult.level);
	                //netwrok.put("timestamp", String.valueOf(scanResult.timestamp));
	                ap.put("capabilities", scanResult.capabilities);
	                available.put(ap);
	            }
	            obj.put("available", available); 
	        }
	        
	    } catch (Exception e) {

	    }
			return obj;
		}

}
