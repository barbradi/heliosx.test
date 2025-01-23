## Run the application
./gradlew bootRun

## Test the application
Once the application is running go to 

http://localhost:8080/swagger-ui/index.html

The API is documented here, and request bodies and parameters are already set with default values. Use the GET end point to retrieve the questions, and the post end point to provide the answers.

Answering yes to question with id1 will provide a not allowed result

```json
{
  "userId": "userId",
  "userEmail": "userEmail",
  "conditionId": "conditionId",
  "condition": "condition",
  "answers": [
    {
      "id": "1",
      "answer": "yes"
    },
    {
      "id": "otherId",
      "answer": "otherAnswer"
    }
  ]
}
```


Answering no to question with id1 will provide an allowed result

```json
{
  "userId": "userId",
  "userEmail": "userEmail",
  "conditionId": "conditionId",
  "condition": "condition",
  "answers": [
    {
      "id": "1",
      "answer": "no"
    },
    {
      "id": "otherId",
      "answer": "otherAnswer"
    }
  ]
}
```

## Assumptions

When the user provide the answers to the post end point a doctor answer is provided immediately by the Doctor service class, and a json response is produced immediately.

```json
{
  "userId": "userId",
  "userEmail": "userEmail",
  "conditionId": "conditionId",
  "condition": "condition",
  "allowed": false,
  "explanation": "you are alcoholic"
}
```

That was asimplification, in a real implementation, this response should be async and provided once the doctor has reviewed the answers.