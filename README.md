# SimpleJsonParser
A Simple Java Json Parser

##Introduction
This is yet another java based json parser created mainly as a programming exercise to learn about parsers. Json provides a simple enough grammer to implement a parser based on mutual recursion techniques. This SimpleJsonParser can also be used in my own applications for suitable use cases where I do not want to rely on other external third party libraries. 
The parser is released under the MIT license. Refer to the license file for details.

For more information on the json format, refer to [http://json.org/](http://json.org/)


## Some Compile Instructions
Check out the sources using git clone or download the zip file

>git clone https://github.com/ngchianglin/SimpleJsonParser.git

Create a class directory outside of the repository
>mkdir class 

>cd SimpleJsonParser

>javac -d ../class sg/nighthour/json/SimpleJsonParser.java

## Java Examples 
There are 4 java examples provided, UsageExample1.java to UsageExample4.java.  Refer to these to see how the SimpleJsonParser can be used. 


## Testing

A TestCasesSimpleJsonParser.java can be used to test the parser. It uses the testsuite (json test files) available from [http://www.json.org/JSON_checker/](http://www.json.org/JSON_checker/)

* Obtain a copy of the zip testsuite. 
* Unzip the test files into a suitable directory
* Modify TestCasesSimpleJsonParser.java and change the variable _testSuitePath_ to point to the directory containing the test files.
* Compile and run TestCasesSimpleJsonParser

All the test cases should passed except for fail18.json which test for depth of nesting square brackets eg. "[[[]]]". 
SimpleJsonParser will pass this, as it currently doesn't have limit on default depth. 


A Findbugs scan has also been done on SimpleJsonParser.  No serious bug is found so far. A copy of the Findbugs result, FindBugsResults.html is in the repository. 

For more information about Findbugs , refer to 
[http://findbugs.sourceforge.net/](http://findbugs.sourceforge.net/)


## Source signature
Gpg Signed commits are used for committing the source files. 

> Look at the repository commits tab for the verified label for each commit. 

> A userful link on how to verify gpg signature in  [https://github.com/blog/2144-gpg-signature-verification](https://github.com/blog/2144-gpg-signature-verification)


