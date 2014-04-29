var exec = require('cordova/exec'),
  cordova = require('cordova'),
  channel = require('cordova/channel');

function WifiInfo() {
  this.lan = {};
  this.networks = {};
  this.refresh = function (cb) {
    this._check(function (data) {
  	console.log("got refreshed data " + JSON.stringify(data.lan));
    	this.lan = data.lan;
    	this.networks = data.networks;
        if (cb) cb();
    }.bind(this), function (e) {
      throw ("Error initializing Wifi Information: " + e);
    });
  }.bind(this);
}

WifiInfo.prototype._check = function (success, fail) {
  exec(success, fail, 'cordovaWifiInfo', 'getWifiInfo', []);
};


var me = new WifiInfo();

channel.onCordovaReady.subscribe(me.refresh);


module.exports = me;
