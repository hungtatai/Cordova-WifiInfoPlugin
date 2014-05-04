package org.apache.cordova.wifiinfo;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.Arrays;
import java.lang.StringBuilder;

public class CordovaWifiInfo extends CordovaPlugin {

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent){
			CordovaWifiInfo.this.returnResults(context);
		}
	};
	
	private CallbackContext callbackContext;

	public CordovaWifiInfo() {}
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Context context = cordova.getActivity().getApplicationContext();
		
		if (action.equals("getWifiInfo")) {
			callbackContext.success(this.loadData(context));
			return true;
		} else if(action.equals("watchWifiInfo")){
			this.callbackContext = callbackContext;
			context.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
			this.returnResults(context);
			return true;
		} else if (action.equals("clearWatch")){
			this.callbackContext = null;
			context.unregisterReceiver(receiver);
			callbackContext.success();
			return true;
		}
		
		return false;
	}
	
	private void returnResults(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		wifiManager.startScan();
		PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, this.loadData(context));
		pluginResult.setKeepCallback(true);
		this.callbackContext.sendPluginResult(pluginResult);
	}
	
	private String parseIP (int intIP) {
		try {
			byte[] bytes = BigInteger.valueOf(intIP).toByteArray();
			int i = 0;
			int j = bytes.length - 1;
			byte tmp;
			while (j > i) {
				tmp = bytes[j];
				bytes[j] = bytes[i];
				bytes[i] = tmp;
				j--;
				i++;
			}
		
			return InetAddress.getByAddress(bytes).toString().replace("/", "");
		} catch (Exception e) {
			return "";
		}
	}
	
	private JSONObject loadData(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		JSONObject obj = new JSONObject();
		try {
			JSONObject connection = new JSONObject();
			connection.put("BSSID", wifiInfo.getBSSID());
			connection.put("HiddenSSID", wifiInfo.getHiddenSSID());
			connection.put("SSID", wifiInfo.getSSID().replace("\"", ""));
			connection.put("MacAddress", wifiInfo.getMacAddress());
			connection.put("IpAddressInt", wifiInfo.getIpAddress());
			connection.put("IpAddress", parseIP(wifiInfo.getIpAddress()));
			connection.put("NetworkId", wifiInfo.getNetworkId());
			connection.put("RSSI", wifiInfo.getRssi());
			connection.put("LinkSpeed", wifiInfo.getLinkSpeed());
			obj.put("connection", connection); 
			
			if(wifiManager.getScanResults() != null){ 
				JSONArray networks = new JSONArray();
				for (ScanResult scanResult : wifiManager.getScanResults()) {
					JSONObject ap = new JSONObject();
					ap.put("BSSID", scanResult.BSSID);
					ap.put("SSID", scanResult.SSID);
					ap.put("frequency", scanResult.frequency);
					ap.put("level", scanResult.level);
					//ap.put("timestamp", String.valueOf(scanResult.timestamp));
					ap.put("capabilities", scanResult.capabilities);
					networks.put(ap);
				}
				obj.put("networks", networks);
			}
		} catch (Exception e) {}
		
		return obj;
	}
}
