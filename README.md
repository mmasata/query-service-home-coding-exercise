# Assignment

## Task 1

Implement thread-safe singly linked list in your favorite
programming language with the following methods:

- push(Object o) <em>// add node to the end of list</em>
- pop()  <em>// remove last node</em>
- insertAfter(Object o, Object after) <em>// insert node next to after
  node</em>

Don’t use collection libraries (e.g. Java collections) but develop own
multi-thread implementation. Implement CRUD as REST API for the
list. Develop automated tests to verify that implementation is
deadlock free.

## Task 2

Design domain model (diagram and technical document) for Hotel
reservation system which will support the following functions:

- User will get a list of all different types of rooms.
- User selects a room type and check the room availability
  between the specified dates.
- User Makes Reservation.

## Task 3

Design (technical document) a system that processes an infinite
stream of data:

- Each record comes as a tuple(url, html content)
- Extract and store the occurrences of: urls, hosts, top-leveldomains,
  in/out links of the page

We have one machine that has enough disk space but limited
memory. Write a design document to describe the system
architecture and data structures as building blocks.


# Solution

## [Task 2](task2/README.md)
## [Task 3](task3/README.md)