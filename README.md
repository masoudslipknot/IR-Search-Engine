# IR-Search-Engine
<h1> Simple search engine</h1>
Minimal search engine application for information retrieval course<br>
<h3> Technologies used</h3>
Java 1.8 <br>
Lucene 6.6 <br>

<h2> Main components:</h2>

Searcher: searcher component main task is to search through indexes provided the path to the index files and the path to the query file then it prepares a list of query results.<br>

Indexer: indexer component main task is to index the documents in the given path and write the results in the given directory. <br>

Decomposer: this component takes the path to the corpus file and decompose it in to separated text files so it would be much easier to index and retrieve and save the decompostion result in the given path.<br>

NewTFIDF: this is a new tf-idf similarity scoring strategy which computes the tf-idf measurement for a term.<br>

In mysimilatri.java file you can implement your own TF-IDF algorithms. <br>


<h2> Author: </h2>
Masoud Erfani. <br>
Hope you enjoy :)
