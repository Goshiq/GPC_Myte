Geo-position converter for Myte contest\
https://myte.me/tasks/RtngmS04tLWEPKTfvUd0

**GPC_Myte**
----
  Returns json data with result of searching the map point by coordinates or its address.

* **URL**

  /search/new/<your request here>
  
  The request should be:
  `- a string with address;
  `
  or
  `- two numbers separated by space to search the place with coordinates.
  `

* **Method:**

  `GET`

* **URL Params**

   **Required:**

   `id=[integer]`

* **Data Params**

  None

* **Success Response for request: /search/new/23 12**

  * **Code:** 200 <br />
    **Content:** `[{"id":248,"address":"Центральный Дарфур Судан","longitude":23.496155,"latitude":12.430016},{"id":249,"address":"Судан","longitude":30.296725,"latitude":17.977449}]'

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** null

* **Examples:**

  ```javascript
    $.ajax({
      url: "/users/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
