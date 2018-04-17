/*
A hashcode is a number generated from any object. This is what allows objects to
be stored/retrieved quickly in a Hashtable.
Imagine the following simple example:
On the table in front of you you have nine boxes, each marked with a number 1 to
9. You also have a pile of wildly different objects to store in these boxes, but
once they are in there you need to be able to find them as quickly as possible.
What you need is a way of instantly deciding which box you have put each object 
in. It works like an index; you decide to find the cabbage so you look up which 
box the cabbage is in, then go straight to that box to get it.
Now imagine that you don't want to bother with the index, you want to be able to
find out immediately from the object which box it lives in.
In the example, let's use a really simple way of doing this - the number of 
letters in the name of the object. So the cabbage goes in box 7, the pea goes in
box 3, the rocket in box 6, the banjo in box 5 and so on. What about the 
rhinoceros, though? It has 10 characters, so we'll change our algorithm a little
and "wrap round" so that 10-letter objects go in box 1, 11 letters in box 2 and 
so on. That should cover any object.
Sometimes a box will have more than one object in it, but if you are looking for
a rocket, it's still much quicker to compare a peanut and a rocket, than to 
check a whole pile of cabbages, peas , banjos and rhinoceroses.
That's a hash code. A way of getting a number from an object so it can be stored
in a Hashtable. In Java a hash code can be any integer, and each object type is
responsible for generating its own. Lookup the "hashCode" method of Object.
 */
package com.tobycc.java.learning.basics;

/**
 *
 * @author Toby
 */
public class WhatIsAHashCode {
    
}
