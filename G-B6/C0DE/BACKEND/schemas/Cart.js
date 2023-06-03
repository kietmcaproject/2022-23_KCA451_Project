const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const cartSchema = new Schema({
    sessionId: {
        type: String,
        required: true,
    },
    item: {
        type: Schema.Types.ObjectId,
        ref: 'Pizza',
        required: true,
    },
    quantity: {
        type: Number,
        required: true,
    }
},
{
    timestamps: true,
});

const cart = mongoose.model('Cart', cartSchema);

module.exports = cart;