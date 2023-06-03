// importing model
const Topping = require('../../schemas/Topping');

// importing status codes
const {
    OK,
    BAD_REQUEST,
    NOT_FOUND,
} = require('../../utilities/statusCodes');

// importing error handles
const{
    sendError,
    sendSuccess
} = require('../../utilities/helpers');


// function to create new topping
const createTopping = async (req, res) => {
    const {
        name,
        price,
        imageUrl,
        category
    } = req.body;

    let topping = await Topping.findOne({name: name}).lean();

    // if topping is already present
    if(topping){
        return sendError(res, "Topping already present", BAD_REQUEST);
    }

    // if not present --> create new topping
    topping = new Topping({
        name: name,
        price: price,
        imageUrl: imageUrl,
        category: category,
    });

    await topping.save();
    return sendSuccess(res, topping);
}

// function to get topping by id
const getTopping = async (req, res) => {
    const toppingId = req.query.id;

    const topping = await Topping.findById(toppingId);

    if(!topping){
        return sendError(res, "Topping not found", NOT_FOUND);
    }

    return sendSuccess(res, topping);
}

// function to get all toppings
const getAllToppings = async (req, res) => {
    const toppingList = await Topping.find({}).lean();

    // if no topping is present
    if(toppingList.length == 0)
        return sendError(res, "Topping is not found", OK);

    return sendSuccess(res, toppingList);
}

// function to update topping
const updateTopping = async (req, res) => {
    const toppingId = req.params.id;
    let topping = await Topping.findById(toppingId).lean();

    // if topping is not present
    if(!topping){
        return sendError(res, 'Topping is not found', NOT_FOUND);
    }

    // updating values
    topping = await Topping.findByIdAndUpdate(toppingId, req.body);
    return sendSuccess(res, topping);
    
}

// function to delete topping
const deleteTopping = async (req, res) => {
    const toppingId  = req.params.id;
    let topping = await Topping.findById(toppingId).lean();

    // if topping is not found
    if(!topping)
        return sendError(res, 'Topping not found', NOT_FOUND);

    topping = await Topping.findByIdAndRemove(toppingId);
    return sendSuccess(res, topping);
}

module.exports = {
    createTopping,
    getTopping, 
    getAllToppings,
    updateTopping,
    deleteTopping,
};