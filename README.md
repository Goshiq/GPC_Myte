**GPC_Myte**

Geo-position converter for Myte contest\
	https://myte.me/tasks/RtngmS04tLWEPKTfvUd0
----

You can use Docker to start the geocoder @localhost
- Get the project to local machine.
- Start the script from root:
```bash
./Dockerize/start_app.sh
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
	[{"id":248,"address":"Центральный Дарфур Судан","longitude":23.496155,"latitude":12.430016},{"id":249,"address":"Судан","longitude":30.296725,"latitude":17.977449}]
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
	[{"id":254,"address":"улица Бакер село Кубиязы, Аскинский район, Республика Башкортостан, Россия","longitude":56.741618,"latitude":56.112754}]
  ```

  Searching by coordinates:
  ```http
	http://localhost:8080/search/new/42 21
  ```

  Result:
  ```json
	[{"id":256,"address":"административный округ Мекка Саудовская Аравия","longitude":41.436553,"latitude":21.811146},{"id":257,"address":"Саудовская Аравия","longitude":45.725533,"latitude":22.848295}]
  ```
