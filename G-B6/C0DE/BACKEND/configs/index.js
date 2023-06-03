require('dotenv').config();

module.exports = {
    NODE_ENV: process.env.NODE_ENV,
    PORT: process.env.PORT,
    MONGO_URI:
        process.env.NODE_ENV === "production"
                    ? process.env.MONGO_URI_PROD
                    : process.env.MONGO_URI_DEV,
    JWT_PRIVATE_KEY: process.env.JWT_PRIVATE_KEY,
    USER_HASH_LENGTH: 10,
    EMAIL_USER: process.env.EMAIL_USER,
    EMAIL_PASS: process.env.EMAIL_USER,
    EMAIL_HOST: process.env.EMAIL_HOST,
    SENDER_MAIL: process.env.SENDER_MAIL,
    RZP_KEY_ID: process.env.RZP_KEY_ID,
    RZP_KEY_SECRET: process.env.RZP_KEY_SECRET,
};