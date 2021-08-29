**GPC_Myte**

Geo-position converter for Myte contest\
	https://myte.me/tasks/RtngmS04tLWEPKTfvUd0

----

You can use Docker to start the geocoder @localhost:8080
- Clone the project to the local machine.
- Execute the script from directory <b>Dockerize</b>:
```bash
./start_app.sh
```
- Now you can send GET-requests

If you need to update *.jar file:
- Execute the script:
```bash
./update_jar_and_start.sh
```

----
**GPC_Myte API**
----
  Returns json data with result of searching the map point by coordinates or its address.

* **URL**

  /search/new/`your_request_here`

* **Method:**

  `GET`

* **URL Params**

   **Required:**

  `- a string with address;
  `

  or\
  `- two numbers separated by space to search the place with coordinates.
  `

* **Data Params**

  None

* **Success Response:**

  * **Request:**
  	``/search/new/23 12``

  * **Code:** 200 <br />
    **Content:**
	```json
	[{"address":"Уаддаи Чад","longitude":21.274999,"latitude":12.799513},{"address":"Чад","longitude":18.724699,"latitude":15.339032}]
	```

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** null

* **Examples:**

  Searching by address:
  ```http
	http://localhost:8080/search/new/Baker street
  ```

  Result:
  ```json
  	[{"address":"улица Бакер село Кубиязы, Аскинский район, Республика Башкортостан, Россия","longitude":56.741618,"latitude":56.112754}]
  ```


  Searching by coordinates:
  ```http
	http://localhost:8080/search/new/42 21
  ```

  Result:
  ```json
  	[{"address":"административный округ Мекка Саудовская Аравия","longitude":41.436553,"latitude":21.811146},{"address":"Саудовская Аравия","longitude":45.725533,"latitude":22.848295}]
  ```
* **In addition:**

  You can monitor the applicaton by Actuator. All metrics are enabled:
  ```http
	http://localhost:8080/actuator
  ```

  To get requests history you can use:
  ```http
	http://localhost:8080/search/show
  ```

  Add request-id to get the only one (if exists):
  ```http
	http://localhost:8080/search/show/128
  ```

  To get all cached addresses:
  ```http
	http://localhost:8080/place/show
  ```

  To get the only one cached address (if exists):
  ```http
	http://localhost:8080/place/show/256
  ```
