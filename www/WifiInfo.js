var exec = require('cordova/exec');
	
var callbacks = {},
	num = 0;
	
var applyCallback = function() {
	for (var cb in callbacks) {
		if(callbacks.hasOwnProperty(cb) && typeof callbacks[cb] === 'function') {
			callbacks[cb].apply(this, arguments);
		}
	}
}
	
var wifi = {
	getWifiInfo: function(callback) {
		exec(function(data) {
			if(callback != null) callback(data);
		}, function(error) {
			if(callback != null) callback({ error: "Internal Error" });
		}, 'WifiInfo', 'getWifiInfo', []);
	},
	
	watchWifiInfo: function(callback) {
		if(num === 0) {
			exec(function(data) {
				applyCallback(data);
			}, function(error) {
				applyCallback({ error: "Internal Error" });
			}, 'WifiInfo', 'watchWifiInfo', []);
		}
	
		++num;
		var id = "cb_" + num;
		callbacks[id] = callback;
		return id;
	},
	
	clearWatch: function(id) {
		if(callbacks[id] != null) {
			--num;
			delete callbacks[id];
			
			if(num === 0) {
				exec(function(){}, function(){}, 'WifiInfo', 'clearWatch', []);
			}
		}
	}
}

module.exports = wifi;
