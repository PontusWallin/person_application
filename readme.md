# Person Application
This is a spring boot application which lets you store and search peoples names from a postgres database using REST endpoints.
The application runs on port 8888.
## Example
To add a person use POST endpoint localhost:8888/person with the following json data in request body (or similar data with same format):

{ 
    "name" : "Pontus Wallin"
}

To search for people in the database use GET endpoint localhost:8888/person?search={searchTerm}
The endpoint returns a list of all Persons with a name which contains the search term.

E.g: localhost:8888/person?search=Pontus

Might return:

{
"name" : "Pontus Wallin"
}

## How to run from command line:
1. Setup the postgress database inside a docker container by running the postgres-docker.yml file.
2. Find the runnable .jar file in the runnable folder.
3. Open command line, navigate to the folder and run  ```java -jar app.jar ```.
4. Use postman, or similar application, to verify that the endpoints work.
