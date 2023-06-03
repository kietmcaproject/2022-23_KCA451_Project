// importing model
const Pizza = require('../../schemas/Pizza');
const Topping = require('../../schemas/Topping');

// importing errors handlers
const {
    sendError,
    sendSuccess,
} = require('../../utilities/helpers');

// importing status codes
const {
    OK,
    BAD_REQUEST,
    NOT_FOUND
} = require('../../utilities/statusCodes');


const CRUST = {
    SMALL: 100,
    MEDIUM: 150,
    LARGE: 200
}

// function to create pizza
const createPizza = async (req, res) => {
    const {
        name,
        toppings,
        imageUrl,
        crustType,
    } = req.body;

    // find if there is already a pizza present
    const pizza = await Pizza.findOne({ name: name });
    if (pizza)
        return sendError(res, 'Pizza is already present', BAD_REQUEST);

    // getting all toppings price
    const toppingsList = [];
    let price = 0;
    for (let i = 0; i < toppings.length; i++) {
        const topping = await Topping.findById(toppings[i]);
        price += topping.price;
        toppingsList.push(topping);
    }

    // adding crust price
    if (crustType === 'SMALL') {
        price += CRUST.SMALL;
    } else if (crustType === 'MEDIUM') {
        price += CRUST.MEDIUM;
    } else {
        price += CRUST.LARGE;
    }

    // creating new pizza
    const newPizza = new Pizza({
        name: name,
        toppings: toppings,
        style: "ADMIN",
        imageUrl: imageUrl,
        price: price,
        crustType: crustType,
    });

    // saving pizza
    await newPizza.save();

    return sendSuccess(res, newPizza);
};

// function to update pizza
const updatePizza = async (req, res) => {
    const pizzaId = req.params.id;

    // check if pizza exists
    let pizza = await Pizza.findById(pizzaId);
    if (!pizza)
        return sendError(res, 'Pizza is not found', NOT_FOUND);

    const {
        toppings,
        crustType
    } = req.body;

    let price = 0;
    // getting all toppings price
    if (toppings.length != 0) {
        const toppingsList = [];
        for (let i = 0; i < toppings.length; i++) {
            const topping = await Topping.findById(toppings[i]);
            price += topping.price;
            toppingsList.push(topping);
        }
    }


    // if crust is given
    if(pizza.crustType != null){
        if (crustType === 'SMALL') {
            price += CRUST.SMALL;
        } else if (crustType === 'MEDIUM') {
            price += CRUST.MEDIUM;
        } else {
            price += CRUST.LARGE;
        }
    }
   

    // update the pizza
    pizza = await Pizza.findByIdAndUpdate(pizzaId, {
        toppings: toppings,
        price: price,
    });

    return sendSuccess(res, pizza);
};

// function to delete pizza
const deletePizza = async (req, res) => {
    const pizzaId = req.params.id;

    // check if pizza is present
    let pizza = await Pizza.findById(pizzaId);
    if (!pizza)
        return sendError(res, 'Pizza is not present', NOT_FOUND);

    // delete pizza
    pizza = await Pizza.findByIdAndRemove(pizzaId);
    return sendSuccess(res, pizza);
};

// function to get pizza
const getPizza = async (req, res) => {
    const pizzaId = req.query.id;

    // check if pizza exits
    const pizza = await Pizza.findById(pizzaId);
    if (!pizza)
        return sendError(res, 'Pizza is not found', NOT_FOUND);

    return sendSuccess(res, pizza);
};

// function to getAll pizzas
const getAllPizzas = async (req, res) => {
    const pizzaList = await Pizza.find({}).populate('toppings');

    // check if pizza is present
    if (pizzaList.length === 0)
        return sendError(res, 'No pizza found', OK);

    return sendSuccess(res, pizzaList);
};

module.exports = {
    createPizza,
    updatePizza,
    deletePizza,
    getPizza,
    getAllPizzas,
};