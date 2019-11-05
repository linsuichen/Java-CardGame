Reviewer: Xueting Ren (u6528362)
Component: <...>
Author: Daniel Levy (u6996577)

Review Comments:
Review method: Tile.java from line 793 to line 965 (method boardStringToRouteArray)

1. This method is commented well so codes are easy to read and comprehend. 
2. The best feature of this code is that it stores different tile placements
in corresponding(well-named) arrayList. So dealing with if statements and
operations for these arrayLists can be convenient to handle for different
purposes.
3. the time consuming is much higher than using HashMap data structure. 
I'd personally recommend HashMap so we can split boaraString into route 
array more efficiently. For example, we can put a value "A01" with the key
"B0" to represent a tile placement "A0B01".
4. this method appropriately uses upstream codes from task6 and decompose
hardest part into some helper methods that can be used in future tasks, which is great.
5. Overall, this method is complied well and used in task8 which passes all tests.
Hence I have only one suggestion about data structure this method used. Others
are pretty good and well-formed.