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
 * A example showing the usage of SimpleJsonParser
 * for handling a json array containing a list of json objects. 
 *  
 *  The following input will be used
 *  [{"Customer":
 * 	  {
 *      "firstname":"Wei Ming",
 *      "lastname":"Tan", 
 *      "address":"Blk 7777 Jurong Longest Road Avenue 777  #07-777",
 *      "postalcode":"777777",
 *      "country":"Singapore",
 *      "telephone":"680008001",
 *      "email":"tanweiming@nighthour.sg",
 *      "No. of orders":389
 *    }
 *   },
 *  {"Customer":
 *   {
 *      "firstname":"Alice",
 *      "lastname":"Tay", 
 *      "address":"Bukit Timah Tallest Hill 8888  #08-8888",
 *      "postalcode":"888888",
 *      "country":"Singapore",
 *      "telephone":"680008002",
 *      "email":"alicetay@nighthour.sg",
 *      "No. of orders":20
 *   }
 *  },
 * {"Customer":
 *    {
 *       "firstname":"David",
 *       "lastname":"O'Neil", 
 *       "address":"Yishun Wide Street 661 Blk 719 #231-5672",
 *       "postalcode":"999999",
 *       "country":"Singapore",
 *       "telephone":"680008003",
 *       "email":"davidoneil@nighthour.sg",
 *       "No. of orders":32
 *    }
 *  },
 * {"Customer":
 *   {
 *      "firstname":"﻿\u660e\u6587",
 *      "lastname":"﻿\u5f20", 
 *      "address":"Punggol Northernmost Ave 67 Blk 3689 #118-0689",
 *      "postalcode":"111111",
 *      "country":"Singapore",
 *      "telephone":"680008004",
 *      "email":"mingwen@nighthour.sg",
 *      "No. of orders":137
 *   }
 * }]
 * 
 * 
 * Ng Chiang Lin
 * May 2016
 * 
 */

import sg.nighthour.json.JsonType;
import sg.nighthour.json.SimpleJsonArray;
import sg.nighthour.json.SimpleJsonObject;
import sg.nighthour.json.SimpleJsonParser;
import sg.nighthour.json.SimpleJsonValue;
import sg.nighthour.json.SimpleParseException;
import sg.nighthour.json.SimpleTokenException;

public class UsageExample3
{

    private static String jsoninput = "[{\"Customer\":" + "\n  {\n" + "     \"firstname\":\"Wei Ming\"," + "\n"
            + "     \"lastname\":\"Tan\", " + "\n"
            + "     \"address\":\"Blk 7777 Jurong Longest Road Avenue 777  #07-777\"," + "\n"
            + "     \"postalcode\":\"777777\"," + "\n" + "     \"country\":\"Singapore\"," + "\n"
            + "     \"telephone\":\"680008001\"," + "\n" + "     \"email\":\"tanweiming@nighthour.sg\",\n"
            + "     \"No. of orders\":389\n  }\n }," + "\n {\"Customer\":" + "\n  {\n" + "     \"firstname\":\"Alice\","
            + "\n" + "     \"lastname\":\"Tay\", " + "\n"
            + "     \"address\":\"Bukit Timah Tallest Hill 8888  #08-8888\"," + "\n" + "     \"postalcode\":\"888888\","
            + "\n" + "     \"country\":\"Singapore\"," + "\n" + "     \"telephone\":\"680008002\"," + "\n"
            + "     \"email\":\"alicetay@nighthour.sg\",\n" + "     \"No. of orders\":20\n  }\n },"
            + "\n {\"Customer\":" + "\n  {\n" + "     \"firstname\":\"David\"," + "\n"
            + "     \"lastname\":\"O'Neil\", " + "\n" + "     \"address\":\"Yishun Wide Street 661 Blk 719 #231-5672\","
            + "\n" + "     \"postalcode\":\"999999\"," + "\n" + "     \"country\":\"Singapore\"," + "\n"
            + "     \"telephone\":\"680008003\"," + "\n" + "     \"email\":\"davidoneil@nighthour.sg\",\n"
            + "     \"No. of orders\":32\n  }\n }," + "\n {\"Customer\":" + "\n  {\n"
            + "     \"firstname\":\"\u660e\u6587\"," + "\n" + "     \"lastname\":\"\u5f20\", " + "\n"
            + "     \"address\":\"Punggol Northernmost Ave 67 Blk 3689 #118-0689\"," + "\n"
            + "     \"postalcode\":\"111111\"," + "\n" + "     \"country\":\"Singapore\"," + "\n"
            + "     \"telephone\":\"680008004\"," + "\n" + "     \"email\":\"mingwen@nighthour.sg\",\n"
            + "     \"No. of orders\":137\n  }\n }" + "]";

    public static void main(String[] args)
    {

        System.out.println("Example parsing the following json input data:");
        System.out.println(jsoninput + "\n\n\n");

        try
        {
            parseJsonArray(jsoninput);
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
     * Initializes a SimpleJsonParser to parse a json array input and extracts
     * out the list of customer information.
     * 
     * @param json
     *            input
     * @throws SimpleTokenException
     * @throws SimpleParseException
     */
    private static void parseJsonArray(String input) throws SimpleTokenException, SimpleParseException
    {
        // Create a SimpleJsonParser object and initialize it with input
        SimpleJsonParser parser = new SimpleJsonParser(input);
        // Call the parse() method to obtain the json value.
        SimpleJsonValue val = parser.parse();

        // A Json value can contain json string, number,
        // object, array, true, false or null. The JsonType enum defines STRING,
        // NUMBER, OBJECT, ARRAY, BOOLEAN, NULL
        // that correspond to these different json values.
        // Check the SimpleJsonValue to see what it contains by calling its
        // getType() method and comparing with the specific JsonType enum.
        // In this case we are expecting a json array based on the input
        if (val.getType() == JsonType.ARRAY)
        {
            System.out.println("Parsed Json Array successfully");
            SimpleJsonArray arr = val.getJsonArray(); // Obtain the json array
                                                      // from the returned Json
                                                      // value

            for (int i = 0; i < arr.getSize(); i++)
            {

                SimpleJsonValue element = arr.getValue(i);
                if (element.getType() == JsonType.OBJECT)
                {// Each element of the array is a json value that contains the
                 // customer object
                    printInformation(i, element);
                }

            }

        }

    }

    /**
     * Obtain the person object the json array element and prints out the person
     * information
     * 
     * @param element_index
     * @param element
     */
    private static void printInformation(int element_index, SimpleJsonValue element)
    {
        System.out.println("Processing element : " + element_index + " in array");
        System.out.println("-------------------------------");
        // Obtain the customer object from the json array element
        SimpleJsonObject customer = element.getJsonObject().getValue("Customer").getJsonObject();
        if (!customer.isEmpty())
        {
            System.out.println("Firstname : " + customer.getValue("firstname"));
            System.out.println("Lastname : " + customer.getValue("lastname"));
            System.out.println("Address : " + customer.getValue("address"));
            System.out.println("Postal Code : " + customer.getValue("postalcode"));
            System.out.println("Country : " + customer.getValue("country"));
            System.out.println("Telephone No : " + customer.getValue("telephone"));
            System.out.println("Email Address : " + customer.getValue("email"));
            System.out.println("No. of orders : " + customer.getValue("No. of orders"));
        }

        System.out.println("-------------------------------\n\n");
    }

}
