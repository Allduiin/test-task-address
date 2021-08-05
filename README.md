## Hi it is application to find and save coordinates by addresses and get all them by coordinates from Nominatim service
### The base application url is http://localhost:8080/location/
### Commands you can use at postman or your browser
- /search?address="address" address can be written using spaces(" ") or pluses("+")
###### this url search by address coordinates and save Location to the database
- /get_all_addresses
###### returns all locations got from nominatim by saved coordinates
