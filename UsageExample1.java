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
 * A example showing how to initialize a SimpleJsonParser to
 * parse a json input
 * 
 * The following input will be used
 * 
 * {"name1":"hello world!"}
 * 
 * Ng Chiang Lin
 * Apr 2016
 *
 */

import sg.nighthour.json.SimpleJsonParser;
import sg.nighthour.json.SimpleJsonValue;
import sg.nighthour.json.SimpleParseException;
import sg.nighthour.json.SimpleTokenException;

public class UsageExample1
{

    public static void main(String[] args)
    {
        String jsoninput = "{\"name1\":\"hello world!\"}";
        System.out.println("Example parsing the following json input data:");
        System.out.println(jsoninput + "\n\n\n");

        // Initialize the SimpleJsonParser with the input
        SimpleJsonParser parser = new SimpleJsonParser(jsoninput);

        try
        {
            // Parses the input
            SimpleJsonValue val = parser.parse();
            // Print out the hello world ! value in the json input
            System.out.println(val.getJsonObject().getValue("name1"));
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

}
