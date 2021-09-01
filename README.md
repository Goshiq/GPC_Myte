**GPC_Myte**

Geo-position converter for Myte contest\
	https://myte.me/tasks/RtngmS04tLWEPKTfvUd0

----

You can use Docker to start the geocoder @localhost:8080
- Clone the project to the local machine.
- Execute the script:
```bash
./start_app.sh
```
- Now you can send GET-requests (examples below)

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
  	``/search/new/12 34``

  * **Code:** 200 <br />
    **Content:**
	```json
	[{"id":16,"address":"провинция Голубой Нил Судан","latitude":11.264303,"longitude":34.124114},{"id":17,"address":"Судан","latitude":17.977449,"longitude":30.296725}]
	```

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** null

* **Examples:**

  * **Searching by address:**
  ```http
	http://localhost:8080/search/new/Baker street
  ```

  * **Result:**
  ```json
    [{"id":14,"address":"улица Бакер село Кубиязы, Аскинский район, Республика Башкортостан, Россия","latitude":56.112754,"longitude":56.741618}]
  ```


  * **Searching by coordinates:**
  ```http
	http://localhost:8080/search/new/21 42
  ```

  * **Result:**
  ```json
    [{"id":33,"address":"административный округ Мекка Саудовская Аравия","latitude":21.811146,"longitude":41.436553},{"id":34,"address":"Саудовская Аравия","latitude":22.848295,"longitude":45.725533}]
  ```
* **In addition:**

  * **To monitor the application by Actuator. All metrics are enabled:**
  ```http
	http://localhost:8080/actuator
  ```

  * **To get requests history you can use:**
  ```http
	http://localhost:8080/search/show
  ```

  * **Add request-id to get the only one (if exists):**
  ```http
	http://localhost:8080/search/show/128
  ```

  * **To get all cached addresses:**
  ```http
	http://localhost:8080/place/show
  ```

  * **To get the only one cached address (if exists):**
  ```http
	http://localhost:8080/place/show/256
  ```
