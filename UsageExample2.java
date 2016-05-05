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
 * An example showing the usage of SimpleJsonParser
 * for handling a json object input.  
 * 
 * The following json input will be used, a json object with a name pair.  
 * 
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
 * 
 * Ng Chiang Lin
 * May 2016
 * 
 */

import sg.nighthour.json.JsonType;
import sg.nighthour.json.SimpleJsonObject;
import sg.nighthour.json.SimpleJsonParser;
import sg.nighthour.json.SimpleJsonValue;
import sg.nighthour.json.SimpleParseException;
import sg.nighthour.json.SimpleTokenException;

public class UsageExample2
{

    private static String jsoninput = "{\"Customer\":" + "\n  {\n" + "     \"firstname\":\"Wei Ming\"," + "\n"
            + "     \"lastname\":\"Tan\", " + "\n"
            + "     \"address\":\"Blk 7777 Jurong Longest Road Avenue 777  #07-777\"," + "\n"
            + "     \"postalcode\":\"777777\"," + "\n" + "     \"country\":\"Singapore\"," + "\n"
            + "     \"telephone\":\"680008001\"," + "\n" + "     \"email\":\"tanweiming@nighthour.sg\",\n"
            + "     \"No. of orders\":389" + "\n   }\n}";

    public static void main(String[] args) throws Exception
    {
        System.out.println("Example parsing the following json input data:");
        System.out.println(jsoninput + "\n\n\n");

        try
        {
            parseJsonObject(jsoninput);
        }
        catch (SimpleTokenException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SimpleParseException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Initializes the SimpleJsonParser with json input and parses the json
     * input to extract the customer information and print this out
     *
     * @param json
     *            input to be parsed
     */
    private static void parseJsonObject(String input) throws SimpleTokenException, SimpleParseException
    {

        // Create a simple json parser object, giving it the input
        SimpleJsonParser parser = new SimpleJsonParser(input);

        // Parse the input using parse() method. The parser will throw
        // SimpleTokenException and SimpleParseException
        // if the input is not properly formatted json
        // parse() returns a SimpleJsonValue if the parsing is successful.
        SimpleJsonValue val = parser.parse();

        SimpleJsonObject jsonobject = null;
        SimpleJsonObject customer = null;

        // A Json value can contain a json string, number,
        // object, array, true, false or null. The JsonType enum defines STRING,
        // NUMBER, OBJECT, ARRAY, BOOLEAN, NULL
        // that corresponds to these different types of json value.
        // Check the SimpleJsonValue to see what it contains by calling its
        // getType() method and comparing with the specific JsonType enum.
        // In this case we are expecting a json object based on the input
        if (val.getType() == JsonType.OBJECT)
        {
            System.out.println("Parsed Json Object successfully");
        }
        else
        {
            System.err.println("Input doesn't contain Json Object. Expecting a Json object!");
            System.exit(1);
        }

        // Get the outermost json object which contains the name pair for a
        // customer
        // Use the getJsonObject() method to obtain the SimpleJsonObject from
        // the SimpleJsonValue
        jsonobject = val.getJsonObject();

        // The isEmpty() method can be used to check if the json object contains
        // any name value pairs.
        if (jsonobject.isEmpty())
        {
            System.err.println("Json object is empty! "
                    + "Expecting a name value pair with Customer as key and a Customer object as value!");
            System.exit(1);
        }

        System.out.println("Obtaining the customer value");

        // Use the getValue() method passing it the name
        // to obtain the json value corresponding to the name.
        SimpleJsonValue pvalue = jsonobject.getValue("Customer");

        if (pvalue.getType() == JsonType.OBJECT)
        {
            // Obtain the customer object from the json value
            customer = pvalue.getJsonObject();
            if (!customer.isEmpty())
            {
                System.out.println("Printing Customer info");
                System.out.println("Firstname : " + customer.getValue("firstname"));
                System.out.println("Lastname : " + customer.getValue("lastname"));
                System.out.println("Address : " + customer.getValue("address"));
                System.out.println("Postal Code : " + customer.getValue("postalcode"));
                System.out.println("Country : " + customer.getValue("country"));
                System.out.println("Telephone No : " + customer.getValue("telephone"));
                System.out.println("Email Address : " + customer.getValue("email"));
                System.out.println("No. of orders : " + customer.getValue("No. of orders"));

            }
            else
            {
                System.out.println("An empty person object : " + customer);
            }
        }

    }

}
