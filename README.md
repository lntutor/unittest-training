# Writing Unit Tests for a REST 


## Useful Dependencies

* Hamcrest (hamcrest-all). We use Hamcrest matchers when we are writing assertions for the responses.
* Junit. We need to exclude the hamcrest-core dependency because we already added the hamcrest-all dependency.
* Mockito (mockito-core). We use Mockito as our mocking library.
* Spring Test
* JsonPath (json-path and json-path-assert). We use JsonPath when we are writing assertions for JSON documents returned by our REST .

## Unit Test Specs:

### Goal: Lines and Method test coverage > 90%

### 1. GET Student Entries:
#### Expected Behavior
The controller method which returns all students entries stored to the database is implemented by following these steps:

It processes GET requests send to url ‘/student’.
It gets a list of Student objects by calling the findAll() method of the StudentRepository interface. This method returns all Student entries which are stored to the database. These Student entries are always returned in the same order.

We can write an unit test for this controller method by following these steps:

1. Create the test data which is returned when the findAll() method of the StudentService interface is called.
2. Configure our mock object to return the created test data when its findAll() method is invoked.
3. Execute a GET request to url ‘/students’.
4. Verify that the HTTP status code 200 is returned.
5. Verify that the content type of the response is ‘application/json’ and its character set is ‘UTF-8’.
6. Get the collection of students entries by using the JsonPath expression $ and ensure that that two students entries are returned.
7. Get the id, name, and passportNumber of the first students entry by using JsonPath expressions $[0].id, $[0].name, and $[0].passportNumber. Verify that the correct values are returned.
8. Get the id, name, and passportNumber of the second students entry by using JsonPath expressions $[1].id, $[1].name, and $[1].passportNumber. Verify that the correct values are returned.
9. Verify that the findAll() method of the StudentRepository interface is called only once.
10. Ensure that no other methods of our mock object are called during the test.


### 2. GET Single Student Entry:

The second controller method which we have to test returns the information of a single students entry. Let’s find out how this controller method is implemented.

#### Expected Behavior
The controller method which returns the information of a single students entry is implemented by following these steps:

1. It processes GET requests send to url ‘/student/{id}’. The {id} is a path variable which contains the id of the requested students entry.
2. It obtains the requested students entry by calling the findById() method of the StudentRepository interface and passes the id of the requested students entry as a method parameter. This method returns the found students entry. If no students entry is found, this method throws a StudentNotFoundException.

##### Test 1: Student Entry Is Not Found
First, we must ensure that our application is working properly when a student entry is not found. We can write an unit test which ensures this by following these steps:

Configure our mock object to throw a studentNotFoundException when its findById() method is called and the id of the requested student entry is 1L.
Execute a GET request to url ‘/student/1’.
Verify that the HTTP status code 404 is returned.
Ensure that the findById() method of the StudentRepository interface is called only once by using the correct method parameter (1L).
Verify that no other methods of the StudentRepository interface are called during this test.

##### Test 2: student Entry Is Found

Second, we must write a test which ensures that the correct data is returned when the requested student entry is found. We can write a test which ensures this by following these steps:

1. Create the student object which is returned when our service method is called. We create this object by using our test data builder.
2. Configure our mock object to return the created student object when its findById() method is called by using a method parameter 1L.
3. Execute a GET request to url ‘/student/1’.
4. Verify that the HTTP status code 200 is returned.
5. Verify that the content type of the response is ‘application/json’ and its character set is ‘UTF-8’.
6. Get the id of the student entry by using the JsonPath expression $.id and verify that the id is 1.
7. Get the name of the student entry by using the JsonPath expression $.name and verify that the name.
8. Get the passportNumber of the student entry by using the JsonPath expression $.passportNumber and verify that the passportNumber.
9. Ensure that the findById() method of the StudentRepository interface is called only once by using the correct method parameter (1L).
10. Verify that the other methods of our mock object are not called during the test.

### 3. POST: Add New student Entry:

The third controller method adds a new student entry to the database and returns the information of the added student entry. Let’s move on and find out how it is implemented.

* Expected Behavior

The controller method which adds new student entries to the database is implemented by following these steps:

1. It processes POST requests send to url ‘/student’.
2. It validates the Student object given as a method parameter.

If the validation fails, a TransactionSystemException is thrown. The HTTP status code 400 is returned to the client.
  1. The maximum length of the name is 100 characters.
  2. The maximum length of the passportNumber is 15 characters.
  3. The name/passportNumber of a Student entry cannot be empty.
  
3. It Adds a new student entry to the database and return http response status code = 201


##### Test 1: Validation Fails
Our first test ensures that our application is working properly when the validation of the added Student entry fails. We can write this test by following these steps:

1. Create a name which has 100 characters.
2. Create a passportNumber which has 100 characters.
3. Create a new Student object. Set the name and the passportNumber of the object.
4. Execute a POST request to url ‘/Student’. Set the content type of the request to ‘application/json’. Set the character set of the request to ‘UTF-8’.
5. Verify that the HTTP status code 400 is returned.
6. Verify that the content type of the response is ‘application/json’ and its content type is ‘UTF-8’.
7. Verify that the methods of our mock object are not called during our test.

##### Test 2: Validation pass, similar to failed case but return http response status code = 201


### 4. DELETE Student entry by ID
* Expected Behavior
1. Student found, delete student entry and return http code 200
2. Student not found, return 404
* Test
1. Execute a DELETE request to url ‘/student/<id>’. Set the content type of the request to ‘application/json’. Set the character set of the request to ‘UTF-8’.\
...


### 5. PUT Update Student entry by ID

Similar to Add new Student cases

