const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const pizzaSchema = new Schema({
    name: {
        type: String,
        required: true,
    },
    toppings: {
        type: [
            {
                type: Schema.Types.ObjectId,
                ref: 'Topping'
            }
        ],
        required: true,
    },
    imageUrl: {
        type: String,
        required: true,
    },
    style: {
        type: String,
        required: true,
        enum: ["CUSTOM", "ADMIN"],
        default: "ADMIN",
    },
    price: {
        type: Number,
        required: true,
    },
    crustType: {
        type: String,
        required: true,
        enum: ["SMALL", "MEDIUM", "LARGE"]
    }
},
{
    timestamps: true,
});

const pizza = mongoose.model('Pizza', pizzaSchema);

module.exports = pizza;