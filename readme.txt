
This is a basic Jersey web api using Mongodb. It is developed as an Intellij project. However you may compile and run it on command prompt.

--------- TOMCAT APPLICATION SERVER -------------

You can start integrated Tomcat Server with following command
../NumbersDemo> mvn tomcat:run-war -DskipTests

Tomcat server will start and be active on http://localhost:8080/NumbersDemo . This is the {{url}} for following commands below.

--------- UNIT TESTS -------------

You can run unit tests with following command
../NumbersDemo> mvn test

--------- MONGODB -------------

You have to install MongoDb, create a database "mydb" and a collection "employees" using following commands
 use mydb
 db.createCollection("employees")


--------- COMMANDS -------------

1. Add an Employee:

Content-Type = application/json

POST {{url}}/employees
{
	"number":"123",
	"name":"ali"
}

Response will be
201 Created
{
    "type": "employee",
    "insertDate": "2018-05-17 06:13:24",
    "number": 123,
    "name": "ali"
}

2. Get a single Employee

GET {{url}}/employees/123

Response will be
200 OK
{
    "type": "employee",
    "insertDate": "2018-05-17 06:13:24",
    "number": 123,
    "name": "ali"
}

3. Get all Employees in order (asc/desc)

GET {{url}}/employees?order=desc

Response will be
200 OK
[
    {
        "type": "employee",
        "insertDate": "2018-05-17 06:15:59",
        "number": 258,
        "name": "ahmet"
    },
    {
        "type": "employee",
        "insertDate": "2018-05-17 06:16:24",
        "number": 222,
        "name": "fatma"
    },
    {
        "type": "employee",
        "insertDate": "2018-05-17 06:13:24",
        "number": 123,
        "name": "ali"
    }
]

4. Get the employee who has the minimum id/number

GET {{url}}/employees/min

Response will be
200 OK
{
    "type": "employee",
    "insertDate": "2018-05-17 06:13:24",
    "number": 123,
    "name": "ali"
}

5. Get the employee who has the maximum id/number

GET {{url}}/employees/max

Response will be
200 OK
{
    "type": "employee",
    "insertDate": "2018-05-17 06:15:59",
    "number": 258,
    "name": "ahmet"
}

6. Delete an employee

DELETE {{url}}/employees/222

Response will be
200 OK
{
    "type": "employee",
    "insertDate": "2018-05-17 06:16:24",
    "number": 222,
    "name": "fatma"
}


