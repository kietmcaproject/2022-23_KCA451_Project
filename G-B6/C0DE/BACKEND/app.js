const express = require("express");
const compression = require("compression");
const morgan = require("morgan");
const helmet = require("helmet");

const { NODE_ENV, PORT } = require('./configs/index');
const { notFound, sendErrors } = require("./configs/errorHandlers");

const app = express();

const cors = require("cors");
require("dotenv").config();
require("./configs/dbConnection");


module.exports = () => {
    app.use(compression());
    app.use(morgan("dev"));
    app.use(helmet());
    app.use(cors({ exposedHeaders: "x-auth-token" }));
    
    app.use(express.urlencoded({
        limit: "50mb",
        extended: true,
        parameterLimit: 1000000,
    }));

    app.use(express.json({
        limit: "50mb",
        extended: true,
        parameterLimit: 1000000,
    }));

    // setting production errors
    if(NODE_ENV === "production")
        console.log = console.warn = console.error = () => {};

    // setting up routes
    app.use('/users', require('./routes/users'));
    app.use('/items/toppings', require('./routes/Items/toppings'));
    app.use('/items/pizzas', require('./routes/Items/pizzas'));
    app.use('/orders', require('./routes/orders'));
    app.use('/auth', require('./routes/authentication'));
    app.use('/cart', require('./routes/cart'));
    app.use('/test', require('./routes/test'));
    
    app.use('*', notFound);   // 404 route: NOT FOUND

    // error handlers
    app.use(sendErrors);

    // allowing headers
    app.use((req, res, next) => {
        res.header('Access-Control-Allow-Origin', '*');
        res.header("Access-Control-Allow-Origin", "*");
		res.header(
			"Access-Control-Allow-Headers",
			"Origin, X-Requested-With, Content-Type, Accept"
		);
		res.header("Access-Control-Allow-Credentials", true);
		res.header(
			"Access-Control-Allow-Methods",
			"GET, POST, PUT, DELETE, PATCH"
		);
		next();
    });

    // server function 
    const startServer = async () => {
        try{
            app.listen(PORT || 3000);
            console.info(`NODE_ENV: ${NODE_ENV} server is up and runnning on PORT: ${PORT}`);
        }
        catch(err){
            console.info("Error in running server.");
        }
    } 

    // starting server
    startServer();
}