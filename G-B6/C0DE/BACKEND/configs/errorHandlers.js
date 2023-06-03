const { sendError } = require("../utilities/helpers");
const{
    NOT_FOUND,
    SERVER_ERROR
} = require('../utilities/statusCodes');

// function to catch errors
// it takes another function as a parameter
module.exports.catchErrors = middlewareFunction => {
    return async (req, res, next) => {
        try{
            await middlewareFunction(req, res, next);
        }
        catch(err){
            next(err);
        }
    };
};

// not found routes
module.exports.notFound = (req, res) => {
    return sendError(res, "Route doesn't exist", NOT_FOUND);
};


// custom made error handler
module.exports.sendErrors = (err, req, res, next) => {
    // logging on console backend
    console.log(err);

    // sending to user on frontend
    return sendError(res, "Something went wrong", err.status || SERVER_ERROR);
}