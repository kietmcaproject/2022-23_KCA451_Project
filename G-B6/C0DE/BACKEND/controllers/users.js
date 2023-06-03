// importing model
const user = require('../schemas/User');
const { findOneAndUpdate } = require('../schemas/User');
const User = require('../schemas/User');

// importing error handlers
const {
    sendError,
    setToken,
    sendSuccess,
    deleteToken,
} = require('../utilities/helpers');

// status codes
const {
    OK,
    BAD_REQUEST,
    NOT_FOUND,
} = require('../utilities/statusCodes');

// HASH LENGTH
const {
    USER_HASH_LENGTH
} = require('../configs/index');
const Token = require('../schemas/Token');

// POST: cb for creating user
const createUser = async (req, res) => {
    const {
        firstName,
        lastName,
        email,
        phoneNumber,
        password,
        role,
    } = req.body;

    // checking if this user is already in system
    const user = await User.findOne({
        email: email,
    });

    if (user)
        return sendError(res, "User already registered", BAD_REQUEST);

    // creating new user
    let newUser = new User({
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneNumber: phoneNumber,
        password: password,
        role: role,
    });

    // save new user in database
    newUser = await newUser.save();

    
    // generate token
    // const token = await newUser.generateAuthToken(
    //     firstName,
    //     lastName,
    //     role,
    //     email,
    // );

    // set token entry in database
    // setToken(String(newUser._id), token);

    return sendSuccess(res, 'Signup Successful');
}

// GET: cb for get user by id
const getUserById = async (req, res) => {
    const userEmail = req.user.email;

    const user = await User.findOne({email: email}).lean();

    // if user is not found
    if (!user)
        return sendError(res, "User not found", NOT_FOUND);

    return sendSuccess(res, user);
}

// GET: cb for getting all users
const getAllUsers = async (req, res) => {
    const userList = await User.find({}).lean();

    // if there is no user present
    if (userList.length == 0)
        return sendError(res, "No user found", OK);

    return sendSuccess(res, userList);
}

// UPDATE: cb for updating user
const updateUser = async (req, res) => {
    const userId = req.params.id;
    let user = await User.findById(userId).lean();

    // if user is not found
    if (!user)
        return sendError(res, "User not found", NOT_FOUND);

    user = await User.findByIdAndUpdate(userId, req.body);
    return sendSuccess(res, user);
}

// DELETE: cb for deleting user
const deleteUser = async (req, res) => {
    const userId = req.params.id
    let user = await User.findById(userId).lean();

    // check if user exists
    if (!user)
        return sendError(res, "User not found", NOT_FOUND);

    // delete token if its there
    if (deleteToken(userId) != "NOT FOUND") {
        console.log('Token is deleted');
    }else{
        console.log('Token is not deleted');
    }

    // delete the user
    user = await User.findByIdAndRemove(userId);
    return sendSuccess(res, user);
}

const makeAdmin = async (req, res) => {
    const userId = req.params.id;
    let user = await User.findById(userId).lean();

    if(!user){
        return sendError(res, 'User not found', NOT_FOUND);
    }

    if(user.role === 'ADMIN'){
        return sendError(res, 'User is already ADMIN', BAD_REQUEST);
    }

    user = await User.findByIdAndUpdate(userId, {
        role: 'ADMIN'
    });

    await user.save();

    sendSuccess(res, 'Role Changed');
}

module.exports = {
    createUser,
    getUserById,
    getAllUsers,
    updateUser,
    deleteUser,
    makeAdmin
}