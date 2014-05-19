var exec = require('cordova/exec');

var callbacks = {},
	num = 0, seed = 0;

var applySuccess = function() {
	for (var cb in callbacks) {
		if(callbacks.hasOwnProperty(cb)) {
			callbacks[cb].success.apply(this, arguments);
		}
	}
};

var applyError = function() {
	for (var cb in callbacks) {
		if(callbacks.hasOwnProperty(cb) && typeof callbacks[cb].error === 'function') {
			callbacks[cb].error.apply(this, arguments);
		}
	}
};

var wifi = {
	getWifiInfo: function(success, error, options) {
		if (typeof success === 'function') {
			error = (typeof error === 'function') ? error : function(){};
			exec(success, error, 'WifiInfo', 'getWifiInfo', [options]);
		}
	},

	watchWifiInfo: function(success, error, options) {
		if (typeof success === 'function') {
			error = (typeof error === 'function') ? error : null;

			if(num === 0) {
				exec(applySuccess, applyError, 'WifiInfo', 'watchWifiInfo', [options]);
			}

			++num;
			++seed;

			var id = "cb_" + seed;
			callbacks[id] = {
				success: success,
				error: error
			};

			return seed;
		}
	},

	clearWatch: function(id) {
		id = "cb_" + id;
		if(callbacks[id] != null) {
			delete callbacks[id];

			--num;
			if(num === 0) {
				exec(function(){}, function(){}, 'WifiInfo', 'clearWatch', []);
			}
		}
	}
};

module.exports = wifi;
