// importing modules
const Razorpay = require('razorpay');
const crypto = require('crypto');

// importing schemma
const Order = require('../schemas/Order');
const User = require('../schemas/User');

// importing constants
const {
    RZP_KEY_ID,
    RZP_KEY_SECRET
} = require('../configs/index');

// importing error handlers
const {
    sendError, sendSuccess
} = require('../utilities/helpers');

// importing status codes
const {
    BAD_REQUEST, NOT_FOUND, SERVER_ERROR
} = require('../utilities/statusCodes');


// function to create new order
const createOrder = async (req, res) => {
    const customer = await User.findOne({ email: req.user.email });
    const { orderPrice } = req.query;

    // check if customer exists
    if (!customer) {
        return sendError(res, 'Customer not found', NOT_FOUND);
    }

    // creating razorpay instance
    const instance = new Razorpay({
        key_id: RZP_KEY_ID,
        key_secret: RZP_KEY_SECRET,
    });

    const options = {
        amount: (orderPrice * 100).toString(),
        currency: 'INR',
        receipt: 'order_rcptid_1',
    }
    // creating an order
    instance.orders.create(options, function (err, order) {
        if (err) {
            return sendError(res, 'Order not placed', BAD_REQUEST);
        }

        return sendSuccess(res, order);
    })
}


// function to verify payment
const verifyPayment = async (req, res) => {
    const { razorpayOrderId, razorpayPaymentId, razorpaySignature } = req.body;
    const customer = await User.findOne({email: req.user.email});

    // creating signature
    let body = razorpayOrderId + "|" + razorpayPaymentId;
    var expectedSignature = crypto.createHmac('sha256', RZP_KEY_SECRET).update(body.toString()).digest('hex');

    // checking if signature is valid
    if (expectedSignature !== razorpaySignature) {
        return sendError(res, 'Signature not valid', BAD_REQUEST);
    }

    // saving order details in database
    const newOrder = new Order({
        customerId: customer._id,
        orderId: razorpayOrderId,
        paymentId: razorpayPaymentId,
        signatureId: razorpaySignature,
    });

    // adding order id into user db
    customer.orderHistory.push(newOrder._id);
    await customer.save(); // save
    
    // saving order in db
    await newOrder.save();

    return sendSuccess(res, { "signatureIsValid": "true" });
}

module.exports = {
    createOrder,
    verifyPayment
};