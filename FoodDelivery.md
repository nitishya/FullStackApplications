Microservice Architecture -
![image](https://github.com/user-attachments/assets/3580195c-13e7-4d81-b0ab-dd24896c1f37)


We are using Eureka for service discovery.
Some time due to some failure of microservices when they are up they are assigned a new IP address which is dynamically assigned by cloud providers to your instances 
,Since you have a new IP address , a new location for your old microservice , you need to change all the URLs in your existing microservices who wants to communicate 
with the crashed microservice, which is now up.

Hence we are using Eureka server as a registry and discovery pattern for our microservices to communicate with each other without having to update any of the URLs or 
addresses or port whenever your instance go up or down.

![image](https://github.com/user-attachments/assets/7d48aa6a-639a-405c-9c57-a2e65ef1c2d3)
