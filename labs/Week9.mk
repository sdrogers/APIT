% AP(IT) -- Composite Design Pattern Laboratory
% Simon Rogers
% 29th Feb 2016

# Aims

To get experience of designing and implementing a system using the `composite` design pattern.

# Background

You are building a computer operating system. Within your system you have files and directories. Directories can include other directories and/or files. All files and directories are within one top directory. The operating system needs to be able to do three things:

 - Get the number of files inside any directory (including the number of files within sub-directories). Directories do not count as files.
 - Get the total size of the files within a directory (the size of a directory is equal to the size of its contents)
 - Display a directory and all of its contents as in the following example (the number in brackets is the total size for that directory). In this example, there are directories `root`, `pictures`, `music`, `jazz` and `classical` and various files. Directory contents should be indented.

~~~~

root (886)
	Settings (10)
	pictures (120)
		portrait (120)
	music (756)
		jazz (335)
			Kind of Blue (201)
			Giant Steps (134)
		classical (421)
			Beethoven, Symphony no 6 (421)

~~~~

# Tasks

 - Sketch out the three components you will need to build (an `interface` and two concrete classes)
 - Create the three components. Remember to ensure that Directories should be able to include other directories.
 - Create another class with a main that demonstrates the system.
