#1. For `https://api.openbrewerydb.org/breweries`
- check response status code is 200;
- check response structure to be like:

`    {
         "id": 2,
         "name": "Avondale Brewing Co",
         "brewery_type": "micro",
         "street": "201 41st St S",
         "city": "Birmingham",
         "state": "Alabama",
         "postal_code": "35222-1932",
         "country": "United States",
         "longitude": "-86.774322",
         "latitude": "33.524521",
         "phone": "2057775456",
         "website_url": "http://www.avondalebrewing.com",
         "updated_at": "2018-08-23T23:19:57.825Z"
     }`;
- check response status code is 404 for invalid url
 
example `https://api.openbrewerydb.org/brewer`

#2. Filter breweries by city

   get `https://api.openbrewerydb.org/breweries?by_city=san_diego`
- check that every response have value `"city": "San Diego"`;

use : `.extract().response().jsonPath().getList("city")`;

- check response status code is 200
- check different values:

"Birmingham" - query param is one word;

"Birmi" - query param is one word, part of the city name;

"san_diego" - query param is two words with underscore;

"san%20diego" - query param is two words with encoded space symbol;

#3. Filter breweries by state

   get `https://api.openbrewerydb.org/breweries?by_state=Florida`
- check that every response have value `"state": "Florida"`;

use : `.extract().response().jsonPath().getList("state");`

- check response status code is 200;
- check different values:
"ohio" - query param is one word, lowercase;

"Birmi" - query param is part of the state name, empty response `[]`; 

"SOUTH%20CAROLINA" - query param is two words, uppercase, with encoded space symbol;

"South_Carolina" - query param is two words with underscore;

#4. Filter breweries by postal code
(Equivalence Class Testing, Boundary Value Testing)

   get `https://api.openbrewerydb.org/breweries?by_postal=44107`
- check that every value in response contains value `"postal": "44107"`;
- check response status code is 200;
- check that it is possible to filter by 4 digits; 
- check that it is possible to filter by 5 digits (by basic); 
- check that it is possible to filter by 6 symbols ("44107-"); 
- check that it is possible to filter by 9 symbols ("44107-4020") - with hyphen;
- check that it is possible to filter by 9 symbols ("44107%204020") - with underscore;
- check that it is possible to filter by 10 symbols ("44107-40209") - should return empty response;


#5. Filter by type of brewery
(Equivalence Class Testing)

   get `https://api.openbrewerydb.org/breweries?by_type=micro`
- check that every response have value `"type": "micro"`;

   use : `.extract().response().jsonPath().getList("type");`
   
- check response status code is 200;
- check that such values can be used: 
`micro, regional, brewpub, large, planning, bar, contract, proprietor`

- check that response is empty for type NOT from the list above (ex. `"micro1"`)

#6. The offset or page of breweries to return.
- get `https://api.openbrewerydb.org/breweries?page=1` - _response1_,
  get `https://api.openbrewerydb.org/breweries?per_page=25` - take first 20 values from response _response2_,
 
  _response1_ should be equal to _response2_.

#7. Number of breweries to return each call.
(Equivalence Class Testing, Boundary Value Testing)

- get `https://api.openbrewerydb.org/breweries?by_type=micro` - count values amount in response (to check default number per page is 20);
- check that for invalid value `https://api.openbrewerydb.org/breweries?per_page=-1` amount of values in response is 20;
- `per_page=0` - empty response;
- `per_page=1` - 1 value;
- `per_page=21` - 21 values;
- `per_page=50` - 50 values;
- `per_page=51` - 50 values;

#8. Sort the results by one or more fields.
(Pairwise testing)

- `sort=-name` - sort by one sort param, desc;
- `sort=type,-street`;
- `sort=-city,state`;
- `sort=-postal_code,country`;
- `sort=-longitude,latitude`;
- `sort=-phone,website_url`;
- `sort=updated_at`;
