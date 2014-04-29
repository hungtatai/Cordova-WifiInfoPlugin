var exec = require('cordova/exec'),
  cordova = require('cordova'),
  channel = require('cordova/channel');

function WifiInfo() {
  this.lan = {};
  this.networks = {};
  this.refresh = function () {
	$me = this;
    this._check(function (data) {
    	$me.lan = data.lan;
    	$me.networks = data.networks;
    }, function (e) {
      throw ("Error initializing Wifi Information: " + e);
    });
  };
}

WifiInfo.prototype._check = function (success, fail) {
  exec(success, fail, 'cordovaWifiInfo', 'getWifiInfo', []);
};


var me = new WifiInfo();

channel.onCordovaReady.subscribe(function () {
  me._check(function (info) {
  	me.lan = info.lan;
	me.networks = info.networks;
    },
    function (e) {
      throw ("Error initializing Network Wifi Information: " + e);
    });
});


module.exports = me;
