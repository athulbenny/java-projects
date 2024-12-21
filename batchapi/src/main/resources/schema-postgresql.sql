DROP TABLE IF EXISTS location;
CREATE TABLE IF NOT EXISTS location ( 
	id VARCHAR(255) PRIMARY KEY, 
    location VARCHAR(255), 
    street VARCHAR(255), 
    city VARCHAR(255),
    state VARCHAR(255),
    zipcode VARCHAR(255),
    lat VARCHAR(255), 
    lng VARCHAR(255)
);
