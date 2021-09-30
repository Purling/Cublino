## Code Review

Reviewed by: Ziling Ouyang, u7312578

Reviewing code written by: Yuechen Liu u7227895

Component: [isGameOverContra()](https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s/-/blob/master/src/comp1140/ass2/Cublino.java#L296-311), [getWinner()](https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s/-/blob/master/src/comp1140/ass2/GameLogic/PurCublino.java#L110-137)

### Comments 

isGameOverContra()

Efficient method to loop over the state String and return if any player's die has caused a game over state. Easy to read and understand, although "s" might not be the best variable name because it does not convey much information. Program decomposition is appropriate and style is consistent.

getWinner()

Well reasoned, logical way to approach the problem and program decomposition makes sense. However, many of the variables have similar non-explicit names which makes understanding their use a bit harder. Code conventions are followed and style is consistent.

String splitting and interpreting the split String is used in both of these methods. Although this is a completely valid way to tackle the problem, wouldn't it easier to create a Boards object using the "state" String? This could reduce the amount of duplicated code and allow traversal over the dice object of both players directly.
