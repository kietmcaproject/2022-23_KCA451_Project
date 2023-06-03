const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const toppingSchema = new Schema({
    name: {
        type: String,
        required: true,
    },
    price: {
        type: Number,
        required: true,
    },
    imageUrl: {
        type: String,
        required: true,
    },
    category: {
        type: String,
        enum: ["VEG", "NONVEG"],
        default: "VEG",
    }
},
{
    timestamps: true,
});

const topping = mongoose.model('Topping', toppingSchema);

module.exports = topping;