// importing modules
const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');

const { JWT_PRIVATE_KEY, USER_HASH_LENGTH } = require('../configs/index');

const { generateHash } = require('../utilities/helpers');

// user schema
const userSchema = new Schema({
    firstName: {
        type: String,
        required: true,
    },
    lastName: {
        type: String,
        required: true,
    },
    email: {
        type: String,
        required: true,
    },
    phoneNumber: {
        type: String,
        required: true,
    },
    password: {
        type: String,
        required: true,
    },
    role: {
        type: String,
        default: 'CUST',
        enum: ['CUST', 'ADMIN'],
        required: true,
    },
    orderHistory: {
        type: [Schema.Types.ObjectId],
        ref: 'Order',
    },
    lastLogin: { type: Date, default: Date.now },
    lastActiveAt: { type: Date },
},
    { timestamps: true },
);

// function before saving
userSchema.pre("save", async function(next){
    this.firstName = String(this.firstName).toLowerCase();
    this.email = String(this.email).toLowerCase();
    if(! this.isModified("password")) return next();

    const salt = await bcrypt.genSalt(10);
    const hash = await bcrypt.hash(this.password, salt);
    this.password = hash;

    next();
});

// function to check if password is valid
userSchema.methods.isValidPwd = async (password, userPassword) => {
    console.log(password);
    const isMatchPwd = await bcrypt.compare(password, userPassword);
    return isMatchPwd;
}

// function to generate unique token
userSchema.methods.generateAuthToken = async (firstName, lastName, role, email) => {
    const token = jwt.sign(
        {
            name: `${firstName} ${lastName}`,
            email: email,
            role: role,
        },
        JWT_PRIVATE_KEY,
    );

    return token;
}


// user model
const user = mongoose.model('User', userSchema);

module.exports = user;