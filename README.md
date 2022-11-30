# Remote Monitoring and Management Project

This project is responsable to store customer devices and possible services for those devices, according to cost and Operating System. It also has an endpoint that calculates monthly cost for a list of devices and services of a customer.

## Starting the Application

Run the `ProjectRMMApplication` class.

## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```
