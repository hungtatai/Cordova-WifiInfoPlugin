var WifiInfo= function() {
};

WifiInfo.prototype.get = function(success, fail) {
		PhoneGap.exec(success, success, 'WifiInfoPlugin', null, [] );
};

cordova.addConstructor(function() {

	if (!window.plugins) {
		window.plugins = {};
	}
	window.plugins.WifiInfo = new WifiInfo();
});