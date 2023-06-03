const mongoose = require('mongoose');
const { NODE_ENV, MONGO_URI } = require('./index');

// debug mongo
if(NODE_ENV === "development") mongoose.set("debug", true);

// mongoose connect
connectDB = async() => {
    try{
        await mongoose.connect(MONGO_URI, {
            useNewUrlParser: true,
			useUnifiedTopology: true,
        });
        console.info("MongoDB connected");
    }
    catch(err){
        console.info(err);
    }
};

connectDB();