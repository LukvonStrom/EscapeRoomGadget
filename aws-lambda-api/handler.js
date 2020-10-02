const AWS = require('aws-sdk');
const dynamoDb = new AWS.DynamoDB.DocumentClient();

const TableName = process.env.tableName;

const isDate = function (date) {
  return (new Date(date) != "Invalid Date") && !isNaN(new Date(date));
};

module.exports.handleBegin = async event => {
  return {
    statusCode: 201,
    body: JSON.stringify(
      await begin(event),
      null,
      2
    ),
  };

};

module.exports.handleEnd = async event => {
  return {
    statusCode: 201,
    body: JSON.stringify(
      await end(event),
      null,
      2
    ),
  };

};

module.exports.handleGetAll = async event => {
  return {
    statusCode: 200,
    body: JSON.stringify(
      await getAll(),
      null,
      2
    ),
  };
};




async function begin(request) {
  const params = {
    TableName,
    Item: {
      groupName: request.body.groupName,
      startTime: (new Date()).toString(),
      endTime: "-"
    }
  };
  return await dynamoDb.put(params).promise();
}

async function end(request) {
  const params = {
    TableName,
    Key: {
      groupName: request.body.groupName
    },
    UpdateExpression: "set endTime = :r",
    ExpressionAttributeValues: {
      ":r": (new Date()).toString()
    },
    ReturnValues: "UPDATED_NEW"
  };
  return await dynamoDb.update(params).promise();
}

async function getAll() {
  let response = await dynamoDb.scan({ TableName }).promise();
  return response.Items.map(responseItem => {
    if (responseItem.startTime && isDate(responseItem.startTime) && responseItem.endTime && isDate(responseItem.endTime) && new Date(responseItem.startTime) <= new Date(responseItem.endTime)) {
      responseItem.duration = Math.abs(new Date(responseItem.endTime) - new Date(responseItem.startTime));
      delete responseItem.startTime;
      delete responseItem.endTime;
      return responseItem;
    }
  }).filter(item => item).sort((first, second) => first.duration - second.duration);

}

module.exports = {handleBegin,handleEnd,handleGetAll};
