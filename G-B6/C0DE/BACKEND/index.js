const cluster = require('cluster');
const os = require("os");
const runServer = require("./app");


// check if current process is master
if(cluster.isMaster){
    if(process.env.NODE_ENV === 'production'){
        // GET total CPU cores
        const cpuCount = os.cpus().length;
        console.log(cpuCount);
        // spawn a worker for every core.
        for(let j=0; j<cpuCount; j++){
            cluster.fork();
        }
    }else{
        cluster.fork();
    }
}else{
    runServer();
}

// Cluster API has a variety of events.
// Here we are creating a new process if a worker die.
cluster.on("exit", function (worker) {
	console.log(`Worker ${worker.id} died'`);
	console.log(`Staring a new one!`);
	cluster.fork();
});