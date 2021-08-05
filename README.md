## Hi it is application to find and save coordinates by addresses and get all them by coordinates from Nominatim service
### The base application url is http://localhost:8080/location/
### Commands you can use at postman or your browser
- /search?address="address" address can be written using spaces(" ") or pluses("+")
###### this url search by address coordinates and save Location to the database
- /get_all_addresses
###### returns all locations got from nominatim by saved coordinates
Also, you can use h2-console(http://localhost:8080/h2-console) to check saved entities 
on the url:jdbc:h2:mem:testdb or you can change it at application properties
###### I didn't use Authentication/Authorisation, because I have never done good working security at Spring Boot(but tried, i will try it one more time, and if it will work good I will add it to this test task)
###Thanks for attention, good luck