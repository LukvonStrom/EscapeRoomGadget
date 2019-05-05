const ApiBuilder = require('claudia-api-builder'),
    AWS = require('aws-sdk');
const api = new ApiBuilder(),
    dynamoDb = new AWS.DynamoDB.DocumentClient();

const TableName = 'escapeRoomTop';

const isDate = function (date) {
    return (new Date(date) !== "Invalid Date") && !isNaN(new Date(date));
};

function begin(request) {
    const params = {
        TableName : TableName,
        Item : {
            groupName : request.body.groupName,
            startTime : (new Date()).toString(),
            endTime : "-"
        }
    };
    return dynamoDb.put(params).promise();
}

function end(request) {
    const params = {
        TableName : TableName,
        Key : {
            groupName : request.body.groupName
        },
        UpdateExpression : "set endTime = :r",
        ExpressionAttributeValues : {
            ":r" : (new Date()).toString()
        },
        ReturnValues : "UPDATED_NEW"
    };
    return dynamoDb.update(params).promise();
}

function getAll(request) {
    return dynamoDb.scan({TableName : TableName}).promise().then(response => {
        return response.Items.map(responseItem => {
            if (responseItem.startTime && isDate(responseItem.startTime) && responseItem.endTime && isDate(responseItem.endTime) && new Date(responseItem.startTime) <= new Date(responseItem.endTime)) {
                responseItem.duration = Math.abs(new Date(responseItem.endTime) - new Date(responseItem.startTime));
                delete responseItem.startTime;
                delete responseItem.endTime;
                return responseItem;
            }
        }).filter(item => item).sort((first, second) => first.duration - second.duration);

    })
}

api.post('/escape/begin', begin, {success : 201});
api.post('/escape/end', end, {success : 201});
api.get('/escape', getAll);

module.exports = api;
