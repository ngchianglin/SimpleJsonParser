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
 * Simple App to run through a set of test cases for SimpleJsonParser.
 * It uses a set of json test files from http://www.json.org/JSON_checker/  
 * 
 * The url has a C language Json checker and a
 * a set of json files that can be used to test a json parser. 
 * 
 * This simple app run through the json test files against SimpleJsonParser 
 * Brief instruction for using this test app
 *  1) Obtain the json test files from http://www.json.org/JSON_checker , unzip the test files into a test directory. 
 *  2) Set the String variable testSuitePath to the directory containing the test files. 
 *  3) Compile and run the Test. 
 * 
 * SimpleJsonParser doesn't have a default depth setting, fail18.json from the test files which tests the depth of nesting [[[]]] will parse successfully.
 * 
 * A maximum input String size setting can be specified for SimpleJsonParser to reject input that exceed certain size. 
 * The rest of the tests should all pass successfully. 
 * 
 * Ng Chiang Lin
 * Apr 2016
 * 
 * 
 */
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import sg.nighthour.json.SimpleJsonParser;
import sg.nighthour.json.SimpleJsonValue;
import sg.nighthour.json.SimpleParseException;
import sg.nighthour.json.SimpleTokenException;

public class TestCasesSimpleJsonParser
{
    // directory holding the test files, note directory separator \\ is required
    // at the end of the path
    private static final String testSuitePath = "C:\\tmp\\jsontestfiles\\";

    // The number of valid json files that should be parsed successfully
    private static final int passfileno = 3;

    // The number of invalid json files that should not be parsed successfully
    private static final int failfileno = 33;

    public static void main(String[] args) throws Exception
    {
        String input = "[{\"a\":{\"b'd e\":{}},\"c\":12.98,\"d\":-778,\"e\":-7.01E+100,\"f\":[1,2,[\"array1 \\\" \\u09cA \", 123],[],[]],\"g\":[{},{},{\"h\":[1,2,3]}]},1234,\"another string\"]";
        String input1 = "{\"a\":{\"b'd e\":{}},\"c\":12.98,\"d\":-778,\"e\":-7.01E+100,\"f\":[1,2,[\"array1 \\\" \\u09cA \", 123],[],[]],\"g\":[{},{},{\"h\":[1,2,3]}]}";

        System.out.println("Simple internal tests");
        System.out.println("---------------------");
        SimpleJsonParser parser = new SimpleJsonParser(input);
        SimpleJsonValue val = parser.parse();
        System.out.println("Result of parsing");
        System.out.println(val);

        parser = new SimpleJsonParser(input1);
        val = parser.parse();
        System.out.println("Result of parsing");
        System.out.println(val);
        System.out.println("---------------------");

        System.out.println("\n\nStarting Test Suite\n");
        TestCasesSimpleJsonParser tsuite = new TestCasesSimpleJsonParser();
        System.out.println("******************************");
        System.out.println("Testing Pass json files");
        System.out.println("******************************\n");
        tsuite.startTestSuite(true);
        System.out.println("\n\n\n");
        System.out.println("******************************");
        System.out.println("Testing Fail json files");
        System.out.println("******************************\n");
        tsuite.startTestSuite(false);

    }

    /**
     * Starts the tests.
     * 
     * @param type
     *            indicates whether the type of test files. true for pass files
     *            that contain json that should parse successfully. false for
     *            fail files that contain json that should not parse
     *            successfully.
     */
    private void startTestSuite(boolean type)
    {
        String filenameprefix;
        int loop = 0;
        if (type)
        {
            filenameprefix = "pass";
            loop = passfileno;
        }
        else
        {
            filenameprefix = "fail";
            loop = failfileno;
        }

        for (int i = 1; i <= loop; i++)
        {
            boolean failtestresult = true;
            String filename = filenameprefix + i + ".json";
            SimpleJsonValue val = null;
            try
            {
                SimpleJsonParser parser = new SimpleJsonParser(readBinaryFile(filename));
                val = parser.parse();
                if (type)
                {
                    System.out.println(filename + " : passed ");
                    System.out.println("------Parse result: " + filename + "--------");
                    System.out.println(val);
                    System.out.println("--------------------------------------\n");
                }

                failtestresult = false; // come here if a fail file passes
            }
            catch (SimpleParseException e)
            {
                if (type)
                {
                    System.out.println(filename + " : failed");
                }
                else
                {
                    System.out.println(filename + " : passed");
                }
            }
            catch (SimpleTokenException e)
            {
                if (type)
                {
                    System.out.println(filename + " : failed");
                }
                else
                {
                    System.out.println(filename + " : passed");
                }
            }

            if (type == false && failtestresult == false)
            {
                System.out.println(filename + ": failed ");
                System.out.println("------Parse result: " + filename + "--------");
                System.out.println(val);
                System.out.println("--------------------------------------\n");

            }
        }

    }

    /**
     * Read in a json test file in bytes and construct a String from the bytes
     * 
     * @param filename
     *            of the json test file
     * @return json string
     */
    private String readBinaryFile(String filename)
    {
        int BUFMAX = 4096;
        byte[] buffer = new byte[BUFMAX];
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        String ret = null;

        try
        {
            try
            {
                out = new ByteArrayOutputStream(BUFMAX * 2);
                in = new FileInputStream(testSuitePath + filename);
                int read = 0;
                while (read != -1)
                {
                    read = in.read(buffer, 0, BUFMAX);
                    if (read != -1)
                    {
                        out.write(buffer, 0, read);
                    }

                }

                ret = out.toString("UTF-8");

            }
            finally
            {
                if (in != null)
                {
                    in.close();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return ret;

    }

}
