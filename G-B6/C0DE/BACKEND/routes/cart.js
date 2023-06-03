// importing modules
const express = require('express');
const router = express.Router();

// importing controllers
const cart = require('../controllers/cart');

// importing authentication middlewares
const {
    adminAuth,
    allAuth
} = require('../middlewares/auth');

// POST: route to add items in cart
router.post('/add', allAuth, cart.addItems);

// GET: route to get items from cart
router.get('/getAll', allAuth, cart.getItems);

// DELETE: route to clear whole cart
router.delete('/clearAll', allAuth, cart.clearAll);

// PUT: route to update items in cart

// DELETE: route to delete items from cart
router.delete('/delete', allAuth, cart.deleteItem);


module.exports = router;