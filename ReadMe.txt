The app relies on a postgres database containing a table called sites. Below is the SQL to create such a table along with an SQL to insert some sample data...

CREATE TABLE sites
(
	id				integer	PRIMARY KEY,
	domain			varchar(200),
	url				varchar(200),
	threat_rating	integer NOT NULL,
	malicious 		boolean	NOT NULL
);

INSERT INTO SITES (id,domain,url,threat_rating,malicious)
VALUES (1,'malware4u.com','www.malware4u.com',95,TRUE),
       (2,'bbc.co.uk','www.bbc.co.uk',5,FALSE),
       (3,'dodgysite.com','www.dodgysite.com',100,TRUE);
       
Once the app is running, it should be possible to send an HTTP GET request simalar to the one below and receive a reply...
 http://localhost:8080/urlinfo/1/bbc.co.uk/www.bbc.co.uk
 
The logic is as follows
Each table record holds a domain name field and a URL field along with a numerical threat rating and a boolean indicating whether that domain/url should be considered malicious
Code will look first for the exact url and if found, return that record.
If the exact url is not found, it will scan for an entry for the domain name and return it if found.
If no entry is found in the database table, a record will be returned with the given url and values of null and zero for the threat rating and malicious flag respectively.

