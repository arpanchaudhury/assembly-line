# AssemblyLine

A console based Scala application to process stream of input data through a network of tasks.

Assumptions
-----------

1. Network has single inlet and outlet for the input stream.
2. Each input constituting the input stream is treated as atomic entities which are processed independently.


Running AssemblyLine
--------------------

* Unzip ```assembly-line.zip``` in local directory.
* Open terminal and go to assembly-line folder.
* Check permissions of sbt file inside assembly-line folder. It should be executable.
* Run application ```./sbt run```
* Run all tests ```./sbt test```


Dependencies
------------

* sbt   ~ 0.13.11
* scala ~ 2.11.8
* spec2 ~ 3.7.2
