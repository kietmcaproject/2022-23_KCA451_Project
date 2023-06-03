// importing modules
const express = require('express');
const router = express.Router();

// importing authenticaton middlewares
const {
    allAuth,
    adminAuth
} = require('../../middlewares/auth');

// importing middlewares
const { catchErrors } = require('../../configs/errorHandlers');

// importing controller
const topping = require('../../controllers/Items/toppings');

// POST: create new topping
router.post('/create', adminAuth, catchErrors(topping.createTopping));

// GET: get topping
router.get('/get', allAuth, catchErrors(topping.getTopping));

// GET: get all toppings
router.get('/getAll', allAuth, catchErrors(topping.getAllToppings));

// PUT: update topping
router.put('/:id/update', adminAuth, catchErrors(topping.updateTopping));

// DELETE: delete topping
router.delete('/:id/delete', adminAuth, catchErrors(topping.deleteTopping));

module.exports = router;