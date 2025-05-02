# YourCarYourWay App

Clone project:

> git clone https://github.com/Shikizzz/ChatApp.git

## Front-end 

Go inside the frontend folder:

> cd frontend

Install dependencies:

> npm install

Launch Front-end:

> npm run start;


## Database

Database installation :
If you don't have MySQL in your machine, here is the installation doc : https://dev.mysql.com/doc/refman/8.4/en/installing.html
Database connection :

Open a terminal, and use this command :
$ mysql -u {yourUsername} -p
Then you have to enter your password, and should be successfully connected tu MySQL DB. 
Then this command :
$ SOURCE /path/to/project/resources/schemas.sql

Now, Database is successfully imported.
In the backend's application.properties, you have to add the property spring.datasource.password={yourPassword} (or add this variable in your environment variables)
You may have to change spring.datasource.username property to fit your MySQL username.


## Back-end

Install dependencies:

> mvn clean install

Launch Back-end:

>  mvn spring-boot:run


