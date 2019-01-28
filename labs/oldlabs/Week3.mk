% AP Laboratory 3
% Dr Simon Rogers
% January 2018

# Introduction and aims

Further work on inheritance and creating an object with generics.

# Tasks

The goal here is to see how to use make an object with generics.

- Create your own Dictionary class that maintains two `ArrayList` objects of generic types, to hold objects and their definitions (e.g. people and their contact details). The class should have an `add` method to add an object/definition pair and a `get` method to return the deifnition objects for any particular input object (see example provided in class). This provides similar operation to Java's `HashMap` object.
- Create a main method that shows two of these objects being created one where both object types are `String`s, and one where one is `String` and one an `Integer`.
- You should now use this as the basis of an address book in which `people` objects (including a name) are the keys and contact detail objects are the values. Contact details can be of different types but they should all be storable in a single dictionary. As the generic definition means that all objects have to be of a single type, you'll need to create a class hierarchy (or interface) to achieve this.