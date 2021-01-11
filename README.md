# Stock

**Add New Record**
----
  Record is added in Db

* **URL**

  /stockmanagement/addStockData

* **Method:**

  `POST`
  
*  **URL Params**


* **Sample Request**

   '''{
    "low": "$7.78",
	"open": "$78.8"
    "stock": "NA"
    }'''

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
  
  **Retrieve Stock Data by Stock Name**
----
  Returns json data about a all stock matching to stock name passed.

* **URL**

  /stockmanagement/retrieveStockData/:stock

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `stock=[String]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{
    "stocks": [
         {
            "quarter": 1,
            "stock": "AA",
            "date": "1/7/2011",
            "open": "$15.82",
            "high": "$16.72",
            "low": "$15.78",
            "close": "$16.42",
            "volume": 239655616,
            "percent_change_price": "3.79267",
            "percent_change_volume_over_last_wk": "",
            "previous_weeks_volume": "",
            "next_weeks_open": "$16.71",
            "next_weeks_close": "$15.97",
            "percent_change_next_weeks_price": "-4.42849",
            "days_to_next_dividend": "26",
            "percent_return_next_dividend": "0.182704"
        }
    ]
}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ "message": "Data not found for stockNG9" }`


* **Sample Call:**

  ```
      url: "/stockmanagement/retrieveStockData/AA",
      dataType: "json",
      type : "GET",
     
  ```

**Upload File**
----
  Upload File and create data in DB.

* **URL**

  /stockmanagement/uploadStockData

* **Method:**

  `POST`
  
*  **URL Params**

   `NONE`

* **Data Params**

  Form-Data to provide file
  
  Key:File
  Value: Browse and select File

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"message": "Uploaded the file successfully: dow_jones_index.csv"}`
 
* **Error Response:**

  * **Code:** 404 BAD Request <br />
    **Content:** `{
    "message": "Please upload a csv file!"
}`
