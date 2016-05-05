/*
* MIT License
*
*Copyright (c) 2016 Ng Chiang Lin
*
*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:
*
*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.
*
*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
*
*/

/**
 * A Simple example to show how to use a SimpleJsonObject and SimpleJsonArray to output json 
 * format.  
 * 
 * For Json object the output will be 
 * {"Customer":
 *   {
 *     "firstname":"Wei Ming",
 *     "lastname":"Tan", 
 *     "address":"Blk 7777 Jurong Longest Road Avenue 777  #07-777",
 *     "postalcode":"777777",
 *     "country":"Singapore",
 *     "telephone":"680008001",
 *     "email":"tanweiming@nighthour.sg"
 *     "No of orders": 389
 *   }
 *  }
 *  
 *  For Json Array the output will be
 *  [
 *  {"Customer":
 *   {
 *     "firstname":"Wei Ming",
 *     "lastname":"Tan", 
 *     "address":"Blk 7777 Jurong Longest Road Avenue 777  #07-777",
 *     "postalcode":"777777",
 *     "country":"Singapore",
 *     "telephone":"680008001",
 *     "email":"tanweiming@nighthour.sg"
 *     "No of orders": 389
 *   }
 *  },
 *  {"Customer":
 *   {
 *     "firstname":"\u660e\u6587",
 *     "lastname":"\u5f20", 
 *     "address":"Punggol Northernmost Ave 67 Blk 3689 #118-0689",
 *     "postalcode":"111111",
 *     "country":"Singapore",
 *     "telephone":"680008004",
 *     "email":"mingwen@nighthour.sg"
 *     "No of orders": 137
 *   }
 *  }
 *  ]
 * 
 * Note the spaces, newlines etc... are removed, the name value pairs may not be in exact sequence.
 * The output will be valid json though and can be parsed properly. 
 * 
 * Ng Chiang Lin
 * May 2016
 *
 */

import sg.nighthour.json.JsonType;
import sg.nighthour.json.SimpleJsonArray;
import sg.nighthour.json.SimpleJsonObject;
import sg.nighthour.json.SimpleJsonValue;

public class UsageExample4
{

    /**
     * Inner Customer class It is declared here as a static inner class to make
     * it convenient for this to be a standalone example
     */
    public static class Customer
    {
        private String firstname;
        private String lastname;
        private String address;
        private String postalcode;
        private String country;
        private String telephone;
        private String email;
        private int orders;

        public Customer(String firstname, String lastname, String address, String postalcode, String country,
                String telephone, String email, int orders)
        {
            this.firstname = firstname;
            this.lastname = lastname;
            this.address = address;
            this.postalcode = postalcode;
            this.country = country;
            this.telephone = telephone;
            this.email = email;
            this.orders = orders;
        }

        public String getFirstname()
        {
            return firstname;
        }

        public String getLastname()
        {
            return lastname;
        }

        public String getAddress()
        {
            return address;
        }

        public String getPostalcode()
        {
            return postalcode;
        }

        public String getCountry()
        {
            return country;
        }

        public String getTelephone()
        {
            return telephone;
        }

        public String getEmail()
        {
            return email;
        }

        public int getOrders()
        {
            return orders;
        }

    }

    /**
     * Initializes 2 customer object and stringify these into json array
     * containing two json objects
     * 
     * @return json array
     */
    private String jsonCreateCustomersArray()
    {
        Customer c1 = new Customer("Wei Ming", "Tan", "Blk 7777 Jurong Longest Road Avenue 777  #07-777", "777777",
                "Singapore", "680008001", "tanweiming@nighthour.sg", 389);
        Customer c2 = new Customer("\u660e\u6587", "\u5f20", "Punggol Northernmost Ave 67 Blk 3689 #118-0689", "111111",
                "Singapore", "680008004", "mingwen@nighthour.sg", 137);

        // Create a new SimpleJsonArray
        SimpleJsonArray arr = new SimpleJsonArray();

        // Add the two Json value. The Json value is obtained by converting the
        // customer object to a SimpleJsonObject through the jsonCustomer
        // method.
        arr.addValue(new SimpleJsonValue(jsonCustomer(c1)));
        arr.addValue(new SimpleJsonValue(jsonCustomer(c2)));

        return arr.toString();
    }

    /**
     * Initializes a customer object and stringify it to a json object string
     * 
     * @return json object string
     */
    private String jsonObjectExample()
    {
        Customer c1 = new Customer("Wei Ming", "Tan", "Blk 7777 Jurong Longest Road Avenue 777  #07-777", "777777",
                "Singapore", "680008001", "tanweiming@nighthour.sg", 389);
        return jsonCustomer(c1).toString();
    }

    /**
     * Takes a customer object and convert it to a SimpleJsonObject
     * 
     * @return SimpleJsonObject
     */

    private SimpleJsonObject jsonCustomer(Customer c1)
    {
        SimpleJsonObject jsonObj = new SimpleJsonObject();
        jsonObj.setNameValue("firstname", new SimpleJsonValue(c1.getFirstname(), JsonType.STRING));
        jsonObj.setNameValue("lastname", new SimpleJsonValue(c1.getLastname(), JsonType.STRING));
        jsonObj.setNameValue("address", new SimpleJsonValue(c1.getAddress(), JsonType.STRING));
        jsonObj.setNameValue("postalcode", new SimpleJsonValue(c1.getPostalcode(), JsonType.STRING));
        jsonObj.setNameValue("country", new SimpleJsonValue(c1.getCountry(), JsonType.STRING));
        jsonObj.setNameValue("telephone", new SimpleJsonValue(c1.getTelephone(), JsonType.STRING));
        jsonObj.setNameValue("email", new SimpleJsonValue(c1.getEmail(), JsonType.STRING));
        jsonObj.setNameValue("No of orders", new SimpleJsonValue(Integer.toString(c1.getOrders()), JsonType.NUMBER));

        SimpleJsonObject outer = new SimpleJsonObject();
        outer.setNameValue("Customer", new SimpleJsonValue(jsonObj));

        return outer;
    }

    public static void main(String[] args) throws Exception
    {

        UsageExample4 obj = new UsageExample4();
        String jsonobject = obj.jsonObjectExample();
        String jsonarray = obj.jsonCreateCustomersArray();

        System.out.println("---- Json Object ----");
        System.out.println(jsonobject);
        System.out.println("\n\n");
        System.out.println("---- Json Array ----");
        System.out.println(jsonarray);

    }

}
