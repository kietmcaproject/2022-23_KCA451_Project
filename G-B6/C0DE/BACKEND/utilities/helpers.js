const { OK, BAD_REQUEST } = require("./statusCodes");

// function to send error
module.exports.sendError = (res, message, status ) => {
    res.status(status).json({
        message,
        error: true,
        data: null,
    });
}

// function to send success
module.exports.sendSuccess = (res, data, token) => {
    if(token){
        return res.status(OK).header("x-auth-token", token).json({
            message: "success",
            error: false,
            data,
        });
    }

    res.status(OK).json({
        message: "success",
        error: false,
        data
    });
};


// function to generate hash
module.exports.generateHash = length => {
	let chars =
		"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	let code = "";
	for (let i = 0; i < length; i++) {
		code += chars[Math.round(Math.random() * (chars.length - 1))];
	}
	return code;
};


// function to set tokens
// status: delete, set
const Token = require('../schemas/Token');
module.exports.setToken = async (token) => {
    const tkn = new Token({
        value: token,
    });

    await tkn.save()
        .then(()=>{
            console.log("Token is saved");
        })
        .catch((err)=>{
            console.log(err);
            console.log("Failed to save token");
        })
}

module.exports.checkToken = async (token) => {
    let tkn = await Token.findOne({ value: token }).lean();
    if(tkn){
        return "FOUND";
    }

    return "NOT FOUND";
}