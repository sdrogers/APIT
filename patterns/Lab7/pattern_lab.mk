% AP(IT) - Design Patterns Lab 1
% Simon Rogers
% 25th February 2015

# Introduction and aims

In this lab you'll play around with two design patterns - iterators and the composite pattern.

# Tasks

#. Write a function that returns the maximum value in a collection. Think of maximum in an abstract way - an object is the maximum if when compared to the other objects it is always bigger (i.e. compareto(o) returns a positive value).
#. Test your function with by building your own iterator based on an array of Integers and an array of Strings.
#. A new University course structure is being introduced that allows students to take individual modules, or blocks of modules. Each module has a name, and a grade. The grade of a block of modules is defined as the maximum grade of the modules inside the block. Design and implement the classes required to represent this data within the using the composite structure. Create toString() methods in all classes. 
#. Modify your code to allow hierarchies of any depth (e.g. any block can contain other blocks or modules)