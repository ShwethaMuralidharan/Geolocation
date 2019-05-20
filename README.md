
Command to build: gradle build 
Command to run : gradle -t runPlayBinary

This project contains the API to enter a land owners name and address. It is implemented using the Play framework. The API returns the latitude and longitude coordinates which can be plugged into a Frontend to preview in any Maps plugin.

POST request : localhost:9000/v1/posts

with request body :

{
	"name":"Shwetha M",
	"address":"Adyar+Ananda+Bhavan,Kalyan+Nagar,+Bangalore-43"
}

returns the result : 

{
    "id": "1",
    "link": "http://localhost:9000/v1/posts/1",
    "name": "Shwetha M",
    "address": "Adyar+Ananda+Bhavan,Kalyan+Nagar,+Bangalore-43",
    "latitude": "13.0222235",
    "longitude": "77.6385476"
}

and Also saves the details in the H2 database. 

To retrieve all the saved details, call the GET API request :

localhost:9000/v1/posts
