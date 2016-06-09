# Wiki
Returns first paragraph of query from Wikipedia

## Approach

I developed this in a text editor (sublime text 3) first before moving over to Eclipse, where I created the Javadoc using the built in generator. I chose to use a text editor first primarily because it forces me to read the documentation of any classes I am unfamiliar with and because the project scale was relatively small. I tested each requirement of the project myself through trial-and-error before using JUnit to test a few functions towards the end of development (primarily the final return value). The test classes were ommited because they were not specified to be required in the prompt and the string values of some queries were long and blocky. 

I created the class by creating the no arg constructor first, the response from Wikipedia next, and then I attempted to remove the HTML tags with regex before using a third party library (Jsoup). From there I worked on allowing the program to prompt the user repeatedly for null queries, then the bonus command line argument constructor. I moved the Wikipedia response and HTML removal into a method from the default constructor, and then I had moved the project into Eclipse. From there, I created the Javadoc. I commented each phase as I completed them. 

Due to the scale of the project, I did not initialize the git repository until the very end. 
