const express = require('express');
const router = express.Router();

// importing middlewares
const { catchErrors } = require('../configs/errorHandlers');

// importing authentication middlewares
const{
    allAuth,
    adminAuth
} = require('../middlewares/auth');

// importing controllers
const user = require('../controllers/users');


// POST: route to create new user
router.post('/create', catchErrors(user.createUser));

// GET: route to get user using id
router.get('/get', allAuth, catchErrors(user.getUserById));

// GET: route to get all users 
router.get('/getAll', adminAuth, catchErrors(user.getAllUsers));

// PUT: route to update user data
router.put('/:id/update', allAuth, catchErrors(user.updateUser));

// DELETE: route to delete user data
router.delete('/:id/delete', allAuth, catchErrors(user.deleteUser));

// PUT: route to make user admin
router.put('/:id/makeAdmin', adminAuth, catchErrors(user.makeAdmin));

module.exports = router;