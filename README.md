# dp1-2021-2022-ling-5
dp1-2021-2022-ling-5 created by GitHub Classroom

# How to play Dobble

https://youtu.be/OJ6e4XmHfgs

# How to install and run Dobble

## Install and configure MySQL

https://dev.mysql.com/downloads/installer/

Once MySQL has been downloaded and installed, in the folder where it is installed, enter:

`cd C:\Program Files\MySQL\MySQL Server 8.0\bin`

Use the MySQL command line with the following options: `mysql -u root -p`

The terminal will then ask you for the password you generated during the installation, and the MySQL console will be opened.

It is first necessary to create the database and the user with access to it:

`create database dobbledb;`

`create user 'user'@'localhost' identified by 'user';`

`grant all on dobbledb.* to 'user'@'localhost';`

## Other prerequisites

It is required that Maven and Java be installed on the PC in order to run the project (they had to be installed for the petclinic project).

The React frontend also requires Node.js, npm, and yarn to be installed.

Node.js and npm: https://nodejs.org/en/download/

Following the installation, we should be able to determine the version of these programs by executing these commands:

`node -v` `npm -v`

Yarn: `npm install --global yarn`

## Run the project

We type the following in the main project folder:

`mvn clean install`

`mvn spring-boot:run`

In another terminal:

`cd src/main/webapp`

`yarn start`

Finally, our backend server will run on port 8000 and React (frontend) on port 3000.
