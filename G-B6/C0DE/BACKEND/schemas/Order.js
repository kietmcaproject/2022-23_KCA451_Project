const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const orderSchema = new Schema({
    customerId: {
        type: Schema.Types.ObjectId,
        required: true,
    },
    orderId: {
        type: String,
        required: true,
    },
    paymentId: {
        type: String,
        required: true,
    },
    signatureId: {
        type: String,
        required: true,
    },
},
{
    timestamps: true,
});

const order = mongoose.model('Order', orderSchema);

module.exports = order;