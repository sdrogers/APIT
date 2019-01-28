% APIT, Week 1 Laboratory
% Dr. Simon Rogers
% Jan 2017

# Introduction and aims

 - Setup your own git repository and commit some changes
 - Clone the course git repository
 - Compile and run some Java code from the command line

# Tasks

 1. Setting up a new git repository
 	- Open a command prompt, navigate to your home space, make a new folder and, from within it, type `git init`
 	- Create a file in this folder (e.g. a text file)
 	- Add the file to the repository (see slides)
 	- Check the Status
 	- Commit the changes
 	- Make a new branch and within it, add a new file and commit.
 	- Switch back to the original (`master`) branch. What happends to the folder?

 2. Clone the course git repository
 	- Create a folder in your home space in which you want the material to be stored
 	- Clone the repo: `git clone https://github.com/sdrogers/APIT.git`
 	- The repo will be updated throughout the course, to get the latest version: `git pull origin master`

 3. Compiling Java on the command line
 	- Open a text editor (e.g. notepad++) and write a short Java programme with a main method that prints `Hello World`. e.g.


~~~~{.java}
public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
~~~~


- Save it as `HelloWorld.java`
- Open a command prompt
- Navigate to the folder comtaining this file
- Compile the code `javac HelloWorld.java` (this will create a HelloWorld.class file)
- Run the code `java HelloWorld` (note `java` and not `javac` and no extension after the filename)

