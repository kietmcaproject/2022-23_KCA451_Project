// importing schemas
const User = require('../schemas/User');
const { sendError, sendSuccess, setToken, checkToken } = require('../utilities/helpers');
const {
    BAD_REQUEST
    , NOT_FOUND,
    FORBIDDEN
} = require('../utilities/statusCodes');


// cb: to login user
module.exports.login = async (req, res, next) => {
    const {
        email,
        password,
    } = req.body;

    const user = await User.findOne({ email: email });

    // check if user exists
    if (user) {
        // check is password is correct
        const validPassword = await user.isValidPwd(password, user.password);
        if (!validPassword) {
            return sendError(res, 'Wrong email or password', BAD_REQUEST);
        } else {
            // updating the last login time and date
            await User.findByIdAndUpdate(user._id, {
                lastLoginAt: new Date(Date.now()).toISOString(),
            })

            // generate new token if expired
            const newToken = await user.generateAuthToken(
                user.firstName,
                user.lastName,
                user.role,
                user.email,
            );

            console.log('User is logged in');
            return sendSuccess(res, newToken, newToken);
        }

    } else {
        return sendError(res, 'User not found', NOT_FOUND);
    }
}


// cb: logout controller
module.exports.logout = async (req, res) => {
    const token = req.header('x-auth-token')

    // delete token
    const tokenStatus = checkToken(token);

    // if token is present
    if (tokenStatus !== 'NOT FOUND') {
        setToken(token);
        console.log('User logged out');
        return sendSuccess(res, "");
    } else {
        return sendError(res, 'Already LoggedOut. Please Login.', BAD_REQUEST);
    }
}
