# graph-lib
# graph-lib
Some design notes:
1. The library currently supports non-weighted directed and undirected graphs with common interfaces. 
Weighted graphs can be added by extending 'Edge' class and implementing new graph class which uses extended class as the generic type for edges.

2. Path finding is implemented with breadth-first search. For non-weighted graphs it provides also the shortest path. For weighted graphs the path is not be the shortest one, but it will work.
Applying the traverse function can be added by reusing breadth-first search functionality with applying function to each node.

3. Graphs are implemented to be thread-safe. Concurrency-related functionality is implemented in AbstractBaseGraph class.
I also published methods to enable/disable graph editing as I think it can be useful to disable changes while some calculations are performed (like path searching). But it is more up to bussiness logic.

4. Library is covered by tests including the concurrency testing.

5. I had no  information about expected usage cases for the library and expected amount of data in graphs, so some architecture decisions and collections, which are used to store data, can be discussed.
