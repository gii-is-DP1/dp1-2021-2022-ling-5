# dp1-2021-2022-ling-5
dp1-2021-2022-ling-5 created by GitHub Classroom

# How to play Dobble

https://youtu.be/OJ6e4XmHfgs

# How to install and run Dobble

## Install and configure MySQL

https://dev.mysql.com/downloads/installer/

After downloading and installing MySQL, enter from the terminal in the folder where it is installed:

`cd C:\Program Files\MySQL\MySQL Server 8.0\bin`

type: `mysql -u root -p`

After this, the terminal will prompt you for the password that you generated earlier in the installation and the MySQL console will be opened.

First, we have to create de database and the user with privileges:

`create database dobbledb;`

`create user 'user'@'localhost' identified by 'user';`

`grant all on dobbledb.* to 'user'@'localhost';`

## Other prerequisites

In order to run the project, Maven and Java must be previously installed on the PC (they had to be installed for the petclinic project).

Node.js, npm and yarn will also need to be installed for the React frontend.

Node.js and npm: https://nodejs.org/en/download/

After the installation, the execution of these commands should tell us the version of them:

`node -v` `npm -v`

Yarn: `npm install --global yarn`

## Run the project

In the main project folder, we type:

`mvn clean install`

`mvn spring-boot:run`

In another terminal:

`cd src/main/webapp`

`yarn start`

Finally we will have our backend server running on port 8000 and React (frontend) on port 3000. 
