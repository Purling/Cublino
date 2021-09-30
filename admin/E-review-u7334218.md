## Code Review

Reviewed by: Zane Gates, u7334218

Reviewing code written by: Ziling Ouyang, u7312578

Component: [PurCublino.applyStep](https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s/-/blob/master/src/comp1140/ass2/GameLogic/PurCublino.java#L23-50)

### Comments 

The code is elegantly written and easy to understand, elevated by the use of enums and unambiguous function and variable names. However, the role of the variables `tipDistance` and `jumpDistance` could be clarified with a modifier like `final`, by writing them in uppercase, or by making them static class fields.

Unfortunately, the code contains zero documentation in the form of comments or javadocs, which is the primary issue with this function. Adding minor general comments could improve readability, in particular describing that this function also adds steps to an array for the purpose of implementing take-backs. Conversely, the most impressive aspect of the code, to me, is the comphrehensiveness and range of functionality offered by this function, in terms of both logging step in an array, and executing the step, on top of minor error handling.

Spacing and formatting is standard and consistent, excluding missing spaces around the condition on line 28; and segmentation of the functionality into other classes and methods is appropriate -- particularly referencing methods from `Boards.java` and impressive class abstraction to `Game.java`.

The code needlessly converts back and forth between a `String` and `int`s for storing the position of the die. Concordant types for the parameters of this method could help give this method a clearer purpose as either a high-level or low-level function. Additionally, the first `if` clause could be refactored into a longer string of conditionals, decreasing its maximum indentational level. I suspect this function lacks complete error checking coverage. In particular, small diagonal moves (e.g. from `d3` to `e2`) would be registered as of type `MoveType.JUMP` when they are `MoveType.INVALID`.

Overall, the code is competently written and highly applicable, although occasionally obtuse and featuring some unusual design choices.
