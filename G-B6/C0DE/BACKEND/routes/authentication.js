const express = require('express');
const { catchErrors } = require('../configs/errorHandlers');
const router = express.Router();

// importing controllers
const auth = require('../controllers/authentication');

// LOGIN route
router.post('/login', catchErrors(auth.login));

// LOGOUT route
router.post('/logout', catchErrors(auth.logout));

module.exports = router;