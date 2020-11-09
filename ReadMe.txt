The app can be imported into an IDE e.g. IntelliJ Idea as a Maven project. It can then be built using mvn clean, install and then it should be possible to run it. It was compiled using Java version 13 (13.0.2).

This app was designed to connect to a (Postgres) database named sitelog containing a table called sites. It is possible to run it without a database installed and a separate endpoint is available to call the app without a database being present. The url for the non-database version is in the form urlinfo/1/nodb/{domain}/{url}.
Also, tests can be run without a database available as they will mock the data required. 

Once the app is running, it should be possible to send an HTTP GET request similar to the one below and receive a reply...
 http://localhost:8080/urlinfo/1/bbc.co.uk/http://www.bbc.co.uk
 
If you are using the version without the database configured, then the equivalent request would be...
 http://localhost:8080/urlinfo/1/nodb/bbc.co.uk/http://www.bbc.co.uk

Below are the SQL scripts required to create the sites table and populate it with some sample data...

CREATE TABLE sites
(
	id			integer	PRIMARY KEY,
	domain			varchar(200),
	url			varchar(200),
	rating			integer NOT NULL,
	malicious 		boolean	NOT NULL
);

INSERT INTO SITES (id,domain,url,rating,malicious)
VALUES (1,'malware4u.com','www.malware4u.com?verydodgy=true&containsnasties=true',95,TRUE),
       (2,'bbc.co.uk','www.bbc.co.uk',5,FALSE),
       (3,'dodgysite.com','www.dodgysite.com',100,TRUE);
       
Database connection details can be viewed in the application.properties file (coding-challenge\src\main\resources\application.properties)
        
The logic is as follows:-
 Each table record holds a domain name field and a URL field along with a numerical threat rating and a boolean indicating whether that domain/url should be considered malicious.
 Code will look for a site record with the exact url or a matching domain name and return it if found.
 If no entry is found in the database table, a record will be returned with the given domain and url and values of null and zero for the threat rating and malicious flag respectively.

