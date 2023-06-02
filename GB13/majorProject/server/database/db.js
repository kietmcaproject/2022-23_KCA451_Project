import mongoose from 'mongoose';

const Connection = async (username, password) => {
    const URL = `mongodb://${username}:${password}@ac-uih4gis-shard-00-00.dkvcts8.mongodb.net:27017,ac-uih4gis-shard-00-01.dkvcts8.mongodb.net:27017,ac-uih4gis-shard-00-02.dkvcts8.mongodb.net:27017/?ssl=true&replicaSet=atlas-9yygsb-shard-0&authSource=admin&retryWrites=true&w=majority` ;
    try {
        await mongoose.connect(URL, { useNewUrlParser: true })
        console.log('Database connected successfully');
    } catch (error) {
        console.log('Error while connecting to the database ', error);
    }
};

export default Connection;