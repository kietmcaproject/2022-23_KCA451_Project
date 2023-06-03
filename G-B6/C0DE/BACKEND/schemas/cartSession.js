const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const sessionSchema = new Schema({
    customerId: {
        type: String,
        required: true,
    },
},
{
    timestamps: true,
});

const cartSession = mongoose.model('cartSession', sessionSchema);

module.exports  = cartSession;