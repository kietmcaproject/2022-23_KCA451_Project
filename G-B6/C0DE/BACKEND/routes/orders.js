// importing modules
const express = require('express')
const router = express.Router();

// importing middlewares
const { catchErrors } = require('../configs/errorHandlers');

// importing authentication middlewares
const {
    adminAuth,
    allAuth
} = require('../middlewares/auth');

// importing controllers
const order = require('../controllers/orders');

// create new order
router.post('/create', allAuth, catchErrors(order.createOrder));

// payment verification
router.post('/payment/verify', allAuth, catchErrors(order.verifyPayment));

module.exports = router;