# radix-tree-search
Mapping the occurrence of words in a text using a Radix Tree (PATRICIA).
This program was made for an assigment of my Algorithms and Data Structures course.
Although some changes were made, the following classes were extracted from the book "Projeto de Algoritmos: Implementações em Java e C++" written by Nivio Ziviani, Ph.D.: 'ArvorePatricia', 'ExtraiPalavra';

Running:
On the main folder run the following code in the terminal:

javac word_mapping/*.java && java word_mapping.WordMap

Description:
The code gets the text from the teste.txt file, splits the text into words (using the delimitators set in the delimitadores.txt file) and saves them and their occurrence in a Radix Tree (PATRICIA).
The tree can then be searched for any words contained or not in the text.



