var exec = require('cordova/exec'),
    cordova = require('cordova'),
		channel = require('cordova/channel');
function WifiInfo() {
	this.info = {};
  this.refresh = function (){
		this._check(function(data){
			this.info = data;
		}, function (e){
			throw ("Error initializing Wifi Information: " + e);
		});
	}
}

WifiInfo.prototype._check = function(success, fail) {
		exec(success, fail, 'cordovaWifiInfo', 'getWifiInfo',  [] );
};


var me = new WifiInfo();

channel.createSticky('onCordovaConnectionReady');
channel.waitForInitialization('onCordovaConnectionReady');

channel.onCordovaReady.subscribe(function() {
    me._check(function(info) {
        me.info = info;
        // should only fire this once
        if (channel.onCordovaConnectionReady.state !== 2) {
            channel.onCordovaConnectionReady.fire();
        }
    },
    function (e) {
        // If we can't get the network info we should still tell Cordova
        // to fire the deviceready event.
        if (channel.onCordovaConnectionReady.state !== 2) {
            channel.onCordovaConnectionReady.fire();
        }
        throw ("Error initializing Network Wifi Information: " + e);
    });
});


module.exports = me;
