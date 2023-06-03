const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const tokenSchema = new Schema({
    value: {
        type: String,
        required: true,
    },
});

const Token = mongoose.model('Token', tokenSchema);

module.exports = Token;