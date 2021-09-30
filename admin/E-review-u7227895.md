## Code Review

Reviewed by: <Yuechen Liu>, <u7227895>

Reviewing code written by: <Zane Gates> <u7334218>

Component: 
[makePlacement(String placement)](https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s/-/blob/master/src/comp1140/ass2/gui/Viewer.java#L69-128) 
[createDieMesh()](https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s/-/blob/master/src/comp1140/ass2/gui/Viewer.java#L185-223)
### Comments 

makePlacement(String placement)

The method is excellent constructed and appropriate variable names are used to clearly exhibit the meaning and purpose of each variable. The helper functions including createDieMesh and createMaterial are implemented and used logically within the method.

The javaFx subscene class is used exceptionally to enable the insertion of 3 dimensional shapes, which is much beyond the requirement standards. The javaFx methods setRotationAxis, setOnMousePressed and getSceneX are also used exceptionally to enable rotations along the y axis to optimise the game experience.

A suggestion would be that a better rotation axis could found such that the rotation goes more smoothly, and the entire chessboard would not exceed the canvas during the rotation. 

createDieMesh()

Excellent idea of making 3-dimensional die mesh such that the number on each face can be checked intuitively. Appropriate names are used for the helper functions, yet the functions seem to be a little bit repetitive and might have other pathways to achieve the purpose. This could probably be improved later if possible.




