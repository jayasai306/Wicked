var mysql = require('mysql');  
var con = mysql.createConnection({  
host: "localhost",  
user: "root",  
password: ""  
});
con.connect(function(err) {  
	if (err) throw err;  
	console.log("Connected!");  
	con.query("CREATE DATABASE IF NOT EXISTS Wicked", function (err, result) {  
		if (err) throw err;  
		console.log("Database created");  
		
	});  
});

var createTbl = function createTable(name,code) {
	if(con.state === 'authenticated') {
		con.changeUser({database : 'Wicked'}, function(err) {
			if (err) throw err;
		});
		var sql = "CREATE TABLE IF NOT EXISTS game_"+code+" (name VARCHAR(255), code VARCHAR(255))";
		con.query(sql, function (err, result) {
			if (err) throw err;
			console.log("Table created");
		});
		var sql = "INSERT INTO game_"+code+" VALUES ('"+name+"','"+code+"')";
		con.query(sql, function (err, result) {
			if (err) throw err;
			console.log("row inserted");
		});
	} else {
		console.log("Table not created");
	}
}

var insertTbl = function insertTable(name,code) {
	if(con.state === 'authenticated') {
		con.changeUser({database : 'Wicked'}, function(err) {
			if (err) throw err;
		});
		var sql = "INSERT INTO game_"+code+" VALUES ("+name+","+code+")";
		con.query(sql, function (err, result) {
			if (err) throw err;
			console.log("row created");
		});
	} else {
		console.log("row not created");
	}
}

module.exports = {con,createTbl,insertTbl};