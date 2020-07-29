// Node.js WebSocket server script
const http = require('http');
const WebSocketServer = require('websocket').server;
const server = http.createServer();
var db = require('./createDB.js');
server.listen(3000);
var id = 0;
var clients = {};
const json = require('./questions.json');

const wsServer = new WebSocketServer({
    httpServer: server
});
wsServer.on('request', function(request) {
    const connection = request.accept(null, request.origin);
	//var index = clients.push(connection) - 1;
    connection.on('message', function(message) {
		console.log('Received Message:', message.utf8Data);
		var jsonObj = JSON.parse(message.utf8Data);
		if (jsonObj.hasOwnProperty('CreateTable')) {
			console.log('create table');
			var code = Math.floor(1000 + Math.random() * 9000);
			db.createTbl(jsonObj.name,code);
			var jsonCode={'code':code};
			connection.send(JSON.stringify(jsonCode));
		} else if (jsonObj.hasOwnProperty('InsertTable')) {
			db.insertTbl(jsonObj.name,jsonObj.code);
		} else if (jsonObj.hasOwnProperty('StartGame')){
			var q = json['Questions'];
			shuffleArray(q);
			var jsonData ="{ \"Questions\" : \"Questions\",";
			for (var i=0;i<5;i++) {
				var x = q[i];
				for (key in x) {
					jsonData = jsonData +"\""+ key + "\" : \"" + x[key] + "\"";
				}
				if (i!=4) {
					jsonData = jsonData + ",";
				}
			}
			jsonData = jsonData + "}";
			console.log(jsonData);
			connection.send(jsonData);
		}
    });
    connection.on('close', function(reasonCode, description) {
        console.log('Client has disconnected.');
    });
});

function shuffleArray(array) {
    for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}



