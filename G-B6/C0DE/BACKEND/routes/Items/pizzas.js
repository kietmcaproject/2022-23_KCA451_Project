const express = require('express');
const router = express.Router();

// importing middlewares
const { catchErrors } = require('../../configs/errorHandlers');

// importing authentication middlewares
const {
    allAuth,
    adminAuth,
} = require('../../middlewares/auth');

// importing controllers
const pizza = require('../../controllers/Items/pizzas');


// POST: create new pizza
router.post('/create', adminAuth, catchErrors(pizza.createPizza));

// GET: get pizza
router.get('/get', allAuth, catchErrors(pizza.getPizza));

// GET: get all pizzas
router.get('/getAll', allAuth, catchErrors(pizza.getAllPizzas));

// PUT: update pizza
router.put('/:id/update', adminAuth, catchErrors(pizza.updatePizza));

// DELETE: delete pizza
router.delete('/:id/delete', adminAuth, catchErrors(pizza.deletePizza));

module.exports = router;