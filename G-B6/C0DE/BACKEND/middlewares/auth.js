const jwt = require('jsonwebtoken');
const { sendError } = require('../utilities/helpers');
const { NOT_AUTHORIZED, FORBIDDEN } = require('../utilities/statusCodes');

// authentication for all users
module.exports.allAuth = (req, res, next) => {
    const token = req.header("x-auth-token");
    if(!token)
        return sendError(
            res, 
            "Access Denied. No token provided",
            NOT_AUTHORIZED
        );

    const decodePayload = jwt.verify(token, process.env.JWT_PRIVATE_KEY);
    req.user = decodePayload;

    return next();
}

// authentication for admin
module.exports.adminAuth = (req, res, next) => {
    const token = req.header("x-auth-token");
    if(!token)
        return sendError(
            res,
            "Access Denied. No token provided",
            NOT_AUTHORIZED
        );

    const decodePayload = jwt.verify(token, process.env.JWT_PRIVATE_KEY);
    console.log(decodePayload);
    if(decodePayload.role === 'ADMIN'){
        req.user = decodePayload;
        return next();
    }else{
        return sendError(res, "Forbidden", NOT_AUTHORIZED);
    }
};