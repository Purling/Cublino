# Assignment Two Feedback

## Group  tue12s
u7312578 Ziling Ouyang
u7227895 Yuechen Liu
u7334218 Zane Gates

## Tutor Comment

Top notch submission! It's great to see that you got the project working so well. Your
code base is excellently designed and the GUI is super intuitive and easy to use.

You have effectively used many object oriented features throughout the project,
and this makes your code concise and readable. Your classes all have clear and
distinct purposes. This makes it easy to maintain and update your code. The descriptive
JavaDoc comments also improve the readability of your code. Your coding style
throughout the project is generally good, although you have some very long lines.
Lines over 100 characters should generally be broken up or wrapped around.

The GUI is very intuitive to use and looks great.
The 3D aspects work flawlessly, and are a great way of giving the player access to the same
information they would have in a real game.
The animations look great and make the game feel very professional. I  like the touch of
being able to play the game in different backgrounds too. I did notice that when the game 
ends the program just sort of stops, with no more move possible. It could be nice to have
something occur when this happens, such as letting the player know and giving them a chance
to restart. Otherwise it might make them wonder if the game is actually over.

It's a nice option for the user to have the choice of two AIs. It's great to see that you got the
Monte Carlo AI working, and it provides an interesting alternative to the easier greedy. You've used
your OO structure effectively to smoothly switch between AIs. I did notice that sometimes your difficult
AI takes a while to make a decision and this can make it seem like your program is frozen. Maybe to
enhance your GUI further you could let the user know somehow when the AI is thinking.

You have some good JUnit tests, although many of your functions are missing tests. Adding some tests
would give you more confidence that everything is working as expected, especially in the more complex
tasks such as the AIs.
You have used Git well throughout the assignment with helpful and descriptive
commit messages. It's great to see consistent commits from all team members, showing
good collaboration skills.

Overall this is a really great assignment! Congratulations on a well deserved excellent result.

## Mark

**13.625/14.0**

## Miscellaneous marks

| Level | Requirement | Result |
|:--:|---|:--:|
|    | All files correct                   | .5 / .5  |
|    | Authorship clear for all classes    | .5 / .5  |
|    | Program runs from JAR               | .5 / .5  |
|    | Appropriate use of Git              | .5 / .5  |
| P  | Appropriate use of OO features      | 1.0/1.0  |
| CR | Program well designed               | .5 / .5  |
| CR | Comments clear and sufficient       | .25/ .25 |
| CR | Coding style good                   | .375 / .5  |
| CR | Appropriate use of JUnit tests      | .125/ .25 |
| D  | Design and code of very high quality| .75/ .75 |
| D  | Works well and easy to run          | .5 / .5  |
| HD | Demonstrates interesting extensions | .75/ .75 |
| HD | Game is exceptional                 | .875/1.0  |

**Total for miscellaneous marks:**  7.125/7.5

## Game marks (manual)

| Level | Requirement | Result |
|:-:|---|:-:|
|CR|Can make moves and see outcome (9)| .25/.25 |
|CR|Only valid placements allowed (10)| .25/.25 |
|D |Basic computer opponent (12)      | .5/.5  |
|HD|Advanced computer opponent (13)   | .5/.5  |
|HD|Interesting variation (15)        | .25/.25 |

**Total for manual marks:** 1.75/1.75

## Test results

```
Test Name   Tests Passed  Weighting      Score
-----              -----      -----      -----
task3                4/4          1        1.0
task4                5/5          1        1.0
task8                3/3          1        1.0
task9                2/2          1        1.0
task6                4/4      0.125      0.125
task7                5/5      0.125      0.125
task11               1/1      0.125      0.125
task14a              3/3      0.125      0.125
task14b              2/2      0.125      0.125
task14c              1/1      0.125      0.125
-----              -----     Total:  4.75/4.75
```
## Originality statements

#### Originality statement F
# IMPORTANT: It is very important that you correctly complete this originality
# statement.
#
# This is your statement of your submitted work being your own.
# Incorrectly filling out this statement could lead to charges
# of academic misconduct.
# 
# For information on how to fill this out correctly, see
# https://cs.anu.edu.au/courses/comp1110/help/faq/09-originality/
#

# Remember: Never misrepresent someone else's work as your own.

declaration: >-
  We declare that the work presented in this stage AND all the work presented
  before it upholds the principles of academic integrity, as defined in the
  University Academic Misconduct Rule; is entirely our own work, with only
  the exceptions listed below; is produced for the purposes of this
  assessment task and has not been submitted for assessment in any other
  context, except where authorised in writing by the course convener; gives
  appropriate acknowledgement of the ideas, scholarship and intellectual
  property of others insofar as these have been used; in no part involves
  copying, cheating, collusion, fabrication, plagiarism or recycling.

# Use this to list names of people who you collaborated with, and a
# comment about what you collaborated on.
#
# Add as many "name+comment" entries as necessary
# (or remove it altogether if you haven't collaborated with anyone)
#collaboration:
#  - name:
#    comment: >-

# Use this to list any code that you used that you did not write,
# aside from code provided by the lecturer.  Provide a comment
# explaining your use and the URL to that code and the licence for
# that code
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external code)
code:
  - comment: Used to flatten a list of lists
    url: https://stackoverflow.com/a/25147125
    licence: CC BY-SA 3.0
  - comment: Modified to format the toString() method of a tree
    url: https://stackoverflow.com/a/27265035
    licence: CC BY-SA 3.0
  - comment: UV coordinates for a 3D cube for task 5
    url: https://stackoverflow.com/a/34663339
    licence: CC-BY-SA
  - comment: Used for splitting String into substrings
    url: https://stackoverflow.com/a/3761521
    licence: CC-BY-SA 3.0
  - comment: Used to deep copy objects
    url: https://stackoverflow.com/a/7596565
    licence: CC BY-SA 3.0
  - comment: Used to sort the array
    url: https://stackoverflow.com/revisions/369867/5
    licence: CC BY-SA 3.0

# Use this to list any assets (artwork, sound, etc) that you used.
# Provide a comment explaining your use of that asset and the URL
# and license for the asset
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external assets)
assets:
  - comment: Die image for demonstrative GUI design use
    url: https://svgsilh.com/9e9e9e/image/41187.html
    licence: CC0 1.0 
  - comment: Image of two dice for demonstrative GUI design use
    url: https://svgsilh.com/9e9e9e/image/2027245.html
    licence: CC0 1.0
  - comment: Image of spruce wood for board tiles (lightly modified)
    url: https://us.123rf.com/450wm/dmitr1ch/dmitr1ch1705/dmitr1ch170500017/77459809-old-spruce-wood-planks-texture-on-exterior-wall-of-a-wooden-church.jpg?ver=6
    licence: CC0 1.0
  - comment: Image of light spruce wood for board tiles (lightly modified)
    url: https://cdn.pixabay.com/photo/2016/02/18/01/35/wood-1206421_1280.jpg
    licence: CC0 1.0
  - comment: Image of acacia wood for the table
    url: https://pixnio.com/free-images/2017/11/01/2017-11-01-17-06-25-1200x798.jpg
    licence: CC0 1.0
  - comment: Martian and siberian skybox (lightly modified)
    url: https://opengameart.org/content/skiingpenguins-skybox-pack
    licence: CC0 1.0
  - comment: Placid skybox (modified)
    url: https://www.keithlantz.net/wp-content/uploads/2011/10/skybox_texture.jpg
    licence: CC0 1.0
  - comment: Further skyboxes
    url: https://skyboxes.weebly.com/
    licence: CC0 1.0
  - comment: Move partially made sound effect
    url: https://soundbible.com/893-Button-Click.html
    licence: CC0 1.0
  - comment: Move completed sound effect
    url: https://soundbible.com/1086-Wooden-Thump.html
    licence: CC0 1.0


# Sign *your* name and uids here. (Remove entries if you have fewer
# than three members.)
signatures:
  - name: Ziling Ouyang
    uid: u7312578
  - name: Zane Gates
    uid: u7334218
  - name: Yuechen Liu
    uid: u7227895

#### Originality statement E
# IMPORTANT: It is very important that you correctly complete this originality
# statement.
#
# This is your statement of your submitted work being your own.
# Incorrectly filling out this statement could lead to charges
# of academic misconduct.
# 
# For information on how to fill this out correctly, see
# https://cs.anu.edu.au/courses/comp1110/help/faq/09-originality/
#

# Remember: Never misrepresent someone else's work as your own.

declaration: >-
  We declare that the work presented in this stage AND all the work presented
  before it upholds the principles of academic integrity, as defined in the
  University Academic Misconduct Rule; is entirely our own work, with only
  the exceptions listed below; is produced for the purposes of this
  assessment task and has not been submitted for assessment in any other
  context, except where authorised in writing by the course convener; gives
  appropriate acknowledgement of the ideas, scholarship and intellectual
  property of others insofar as these have been used; in no part involves
  copying, cheating, collusion, fabrication, plagiarism or recycling.

# Use this to list names of people who you collaborated with, and a
# comment about what you collaborated on.
#
# Add as many "name+comment" entries as necessary
# (or remove it altogether if you haven't collaborated with anyone)
#collaboration:
#  - name:
#    comment: >-

# Use this to list any code that you used that you did not write,
# aside from code provided by the lecturer.  Provide a comment
# explaining your use and the URL to that code and the licence for
# that code
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external code)
code: 
  - comment: Used to deep copy objects
    url: https://stackoverflow.com/a/7596565
    licence: CC BY-SA 3.0
  - comment: Used to sort the array
    url: https://stackoverflow.com/revisions/369867/5
    licence: CC BY-SA 3.0


# Use this to list any assets (artwork, sound, etc) that you used.
# Provide a comment explaining your use of that asset and the URL
# and license for the asset
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external assets)
#assets:
#  - comment:
#    url:
#    licence:


# Sign *your* name and uids here. (Remove entries if you have fewer
# than three members.)
signatures:
  - name: Ziling Ouyang
    uid: u7312578
  - name: Yuechen Liu
    uid: u7227895
  - name: Zane Gates
    uid: u7334218

#### Originality statements D
# IMPORTANT: It is very important that you correctly complete this originality
# statement.
#
# This is your statement of your submitted work being your own.
# Incorrectly filling out this statement could lead to charges
# of academic misconduct.
# 
# For information on how to fill this out correctly, see
# https://cs.anu.edu.au/courses/comp1110/help/faq/09-originality/
#

# Remember: Never misrepresent someone else's work as your own.

declaration: >-
  I declare that this work upholds the principles of academic integrity,
  as defined in the University Academic Misconduct Rule; is entirely my
  own work, with only the exceptions listed below; is produced for the
  purposes the purposes of this assessment task and has not been submitted
  for assessment in any other context, except where authorised in writing
  by the course convener; gives appropriate acknowledgement of the ideas,
  scholarship and intellectual property of others insofar as these have been
  used; in no part involves copying, cheating, collusion, fabrication,
  plagiarism or recycling.

# Use this to list names of people who you collaborated with, and a
# comment about what you collaborated on.
#
# Add as many "name+comment" entries as necessary
# (or remove it altogether if you haven't collaborated with anyone)


# Use this to list any code that you used that you did not write,
# aside from code provided by the lecturer.  Provide a comment
# explaining your use and the URL to that code and the licence for
# that code
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external code)

# Use this to list any assets (artwork, sound, etc) that you used.
# Provide a comment explaining your use of that asset and the URL
# and license for the asset
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external assets)



# sign *your* name and uid here
name: Yuechen Liu
uid: u7227895
# IMPORTANT: It is very important that you correctly complete this originality
# statement.
#
# This is your statement of your submitted work being your own.
# Incorrectly filling out this statement could lead to charges
# of academic misconduct.
# 
# For information on how to fill this out correctly, see
# https://cs.anu.edu.au/courses/comp1110/help/faq/09-originality/
#

# Remember: Never misrepresent someone else's work as your own.

declaration: >-
  I declare that this work upholds the principles of academic integrity,
  as defined in the University Academic Misconduct Rule; is entirely my
  own work, with only the exceptions listed below; is produced for the
  purposes the purposes of this assessment task and has not been submitted
  for assessment in any other context, except where authorised in writing
  by the course convener; gives appropriate acknowledgement of the ideas,
  scholarship and intellectual property of others insofar as these have been
  used; in no part involves copying, cheating, collusion, fabrication,
  plagiarism or recycling.

# Use this to list names of people who you collaborated with, and a
# comment about what you collaborated on.
#
# Add as many "name+comment" entries as necessary
# (or remove it altogether if you haven't collaborated with anyone)
#collaboration:
#  - name:
#    comment: >-

# Use this to list any code that you used that you did not write,
# aside from code provided by the lecturer.  Provide a comment
# explaining your use and the URL to that code and the licence for
# that code
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external code)
#code:
#  - comment:
#    url:
#    licence:

# Use this to list any assets (artwork, sound, etc) that you used.
# Provide a comment explaining your use of that asset and the URL
# and license for the asset
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external assets)
#assets:
#  - comment:
#    url:
#    licence:


# sign *your* name and uid here
name: Ziling Ouyang
uid: u7312578
# IMPORTANT: It is very important that you correctly complete this originality
# statement.
#
# This is your statement of your submitted work being your own.
# Incorrectly filling out this statement could lead to charges
# of academic misconduct.
# 
# For information on how to fill this out correctly, see
# https://cs.anu.edu.au/courses/comp1110/help/faq/09-originality/
#

# Remember: Never misrepresent someone else's work as your own.

declaration: >-
  I declare that this work upholds the principles of academic integrity,
  as defined in the University Academic Misconduct Rule; is entirely my
  own work, with only the exceptions listed below; is produced for the
  purposes the purposes of this assessment task and has not been submitted
  for assessment in any other context, except where authorised in writing
  by the course convener; gives appropriate acknowledgement of the ideas,
  scholarship and intellectual property of others insofar as these have been
  used; in no part involves copying, cheating, collusion, fabrication,
  plagiarism or recycling.


# sign *your* name and uid here
name: Zane Gates
uid: u7334218

#### Originality statement C
# IMPORTANT: It is very important that you correctly complete this originality
# statement.
#
# This is your statement of your submitted work being your own.
# Incorrectly filling out this statement could lead to charges
# of academic misconduct.
# 
# For information on how to fill this out correctly, see
# https://cs.anu.edu.au/courses/comp1110/help/faq/09-originality/
#

# Remember: Never misrepresent someone else's work as your own.

declaration: >-
  We declare that the work presented in this stage AND all the work presented
  before it upholds the principles of academic integrity, as defined in the
  University Academic Misconduct Rule; is entirely our own work, with only
  the exceptions listed below; is produced for the purposes the purposes of this
  assessment task and has not been submitted for assessment in any other
  context, except where authorised in writing by the course convener; gives
  appropriate acknowledgement of the ideas, scholarship and intellectual
  property of others insofar as these have been used; in no part involves
  copying, cheating, collusion, fabrication, plagiarism or recycling.

# Use this to list names of people who you collaborated with, and a
# comment about what you collaborated on.
#
# Add as many "name+comment" entries as necessary
# (or remove it altogether if you haven't collaborated with anyone)
#collaboration:
#  - name:
#    comment: >-

# Use this to list any code that you used that you did not write,
# aside from code provided by the lecturer.  Provide a comment
# explaining your use and the URL to that code and the licence for
# that code
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external code)
code:
  - comment: UV coordinates for a 3D cube for task 5
    url: https://stackoverflow.com/a/34663339
    licence: CC-BY-SA
  - comment: Used for splitting String into substrings
    url: https://stackoverflow.com/a/3761521
    licence: CC-BY-SA 3.0


# Use this to list any assets (artwork, sound, etc) that you used.
# Provide a comment explaining your use of that asset and the URL
# and license for the asset
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external assets)
#assets:
#  - comment:
#    url:
#    licence:


# Sign *your* name and uids here. (Remove entries if you have fewer
# than three members.)
signatures:
  - name: Ziling Ouyang
    uid: u7312578
  - name: Zane Gates
    uid: u7334218
  - name: Yuechen Liu
    uid: u7227895

#### Originality statement B
# IMPORTANT: It is very important that you correctly complete this originality
# statement.
#
# This is your statement of your submitted work being your own.
# Incorrectly filling out this statement could lead to charges
# of academic misconduct.
# 
# For information on how to fill this out correctly, see
# https://cs.anu.edu.au/courses/comp1110/help/faq/09-originality/
#

# Remember: Never misrepresent someone else's work as your own.

declaration: >-
  We declare that this work upholds the principles of academic integrity,
  as defined in the University Academic Misconduct Rule; is entirely our
  own work, with only the exceptions listed below; is produced for the
  purposes of this assessment task and has not been submitted
  for assessment in any other context, except where authorised in writing
  by the course convener; gives appropriate acknowledgement of the ideas,
  scholarship and intellectual property of others insofar as these have been
  used; in no part involves copying, cheating, collusion, fabrication,
  plagiarism or recycling.

# Use this to list names of people who you collaborated with, and a
# comment about what you collaborated on.
#
# Add as many "name+comment" entries as necessary
# (or remove it altogether if you haven't collaborated with anyone)
#collaboration:
#  - name:
#    comment: >-

# Use this to list any code that you used that you did not write,
# aside from code provided by the lecturer.  Provide a comment
# explaining your use and the URL to that code and the licence for
# that code
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external code)
#code:
#  - comment:
#    url:
#    licence:

# Use this to list any assets (artwork, sound, etc) that you used.
# Provide a comment explaining your use of that asset and the URL
# and license for the asset
#
# Add as many "url+licence+comment" entries as necessary
# (or remove it altogether if you haven't used any external assets)
assets:
  - comment: Die image for demonstrative GUI design use
    url: https://svgsilh.com/9e9e9e/image/41187.html
    licence: CC0 1.0 
  - comment: Image of two dice for demonstrative GUI design use
    url: https://svgsilh.com/9e9e9e/image/2027245.html
    licence: CC0 1.0 


# Sign *your* name and uids here. (Remove entries if you have fewer
# than three members.)
signatures:
  - name: Zane Gates
    uid: u7334218
  - name: Yuechen Liu
    uid: u7227895
  - name: Ziling Ouyang
    uid: u7312578

## Git Contributions
#### Commit count via git log
```
   2 Alice <u7227895@anu.edu.au>
   1 Bob <u7334218@anu.edu.au>
   2 Cindy <u1234567@anu.edu.au>
   9 Steve Blackburn <steve.blackburn@anu.edu.au>
   6 Yuechen <u7227895@anu.edu.au>
  46 Yuechen Liu <u7227895@anu.edu.au>
 170 Zane Gates <u7334218@anu.edu.au>
  48 Ziling Ouyang <u7312578@anu.edu.au>
  82 Zling Ouyang <u7312578@anu.edu.au>
   3 georgielyall <u6431454@anu.edu.au>

```
#### Line count via git blame
```
  15 Alice
   7 Bob
   9 Cindy
1707 Steve
 456 Yuechen
2078 Zane
2875 Zling
```
## Git Log
```
commit dc0ceebf228e709d01679111954a16b94b40005e
Merge: 9b9ac8f 4bb08b3
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 11:38:26 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit 9b9ac8fbd2b6999c4db668a432a758444d415918
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 11:37:51 2021 +1100

    Final bit of documentation and minor bug fix. Also, removed most debugging souts. Tiny tweak to AI heuristic

commit 4bb08b3804fd2ab173450ed42a09977f234f5288
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 00:06:26 2021 +0000

    Update F-best-u7312578.yml

commit 5c25e96500d21fc039dba2db8cfff68c6710747e
Merge: 9347e76 7c397d8
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 10:25:17 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit 7c397d89ec35471b49510e78f7be4698de79619b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 23:24:47 2021 +0000

    Marked every task as complete

commit 9347e76079b7e2aaace237bb4840fcc800bec0a7
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 10:22:44 2021 +1100

    Added some more documentation

commit e064f6b3a7afe4f4e24fff89e3353219b3024bac
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 10:21:15 2021 +1100

    Fixed tests to account for animation

commit 371ce1b4f97194c6daef0ff02ea07ed5af49f23c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 10:10:22 2021 +1100

    Correctly disabled Tuscan this time

commit aa00315ca10b9816c22f9235899cb04279d1e7f9
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 10:09:00 2021 +1100

    Fixed authorship comments in javadocs in GUI classes

commit 0f651a592a3fed37d72ea99ceebb86b8eed74122
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 10:07:29 2021 +1100

    Generalised DifficultAI.java

commit 7aafc9434f3ec9b9fa158c4ea975971299d1e50d
Merge: 35a6619 09427b4
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 10:04:12 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 35a6619bc62a49a729b16b2e64f3398a2cdf93d6
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 10:04:05 2021 +1100

    Disabled Tuscan skybox

commit 09427b4fb035405ed1e240dee21e523f29853631
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Oct 21 23:01:53 2021 +0000

    Update F-best-u7227895.yml

commit 7d8f7095ec039c5622edf89b8182c6de9825a021
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Oct 21 23:00:23 2021 +0000

    Update F-best-u7227895.yml

commit 22c1272167ced35b898052bf09d85cfe562d4f87
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 22:57:52 2021 +0000

    Update F-best-u7312578.yml

commit ad7dac929d1760ef197112b3366855a2c3e5bea3
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 04:31:57 2021 +1100

    Use the player symbols to show the winner of the game in the UI... and done!

commit 0105b3df612c897a951ed90d943dd32b72cd20bd
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 04:21:02 2021 +1100

    Fixed sound effects and added them to originality statement

commit cc425f67e30e4dde8fa9c069910152465e52022b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 04:16:59 2021 +1100

    Completed originality statement

commit 3dfd94ed9a665cb2b080e1cae52d2541b672f74e
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 04:01:42 2021 +1100

    Improved menu

commit fea1ebb23d97c8c90d94babeba3b9686f071379e
Merge: 5b3444a e96825d
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 03:08:29 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 5b3444a70b96098d284b17e6ec203f767b82aa8a
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 03:08:22 2021 +1100

    Removed debugging souts in Board

commit e96825d805a4442759d262d247868565819d61fc
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 16:07:55 2021 +0000

    Update F-best-u7312578.yml

commit f81d12fb2a41cd40c538bf61ea54f548c9a7ec5c
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Oct 21 16:07:53 2021 +0000

    Update F-best-u7227895.yml

commit de95718d06642fa736a58c6e8a9f94ff1457f72b
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Oct 21 16:06:22 2021 +0000

    Update F-best-u7227895.yml

commit 1552a5600c009067ade8c8d9cdfc0f3dd070aaf6
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 03:06:14 2021 +1100

    Tuned the grittiness of the bumpmapping

commit 421de7c02053025555dfd2e2ba284bd0cd2fa6d6
Merge: 74463d2 032d128
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 03:00:49 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 74463d2c264449056777ebdf2eff45aca1cd8683
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 03:00:42 2021 +1100

    Fully implemented fades between the menu and the game

commit 032d1287f86eb8d22313ffecbaba6575d5160593
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 02:53:05 2021 +1100

    Fixed Pur DifficultAI. I WANT TO SLEEP

commit 6e9d014019d38b1282e96e5728cc7f3ce25d10d3
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 02:37:57 2021 +1100

    Started to implement fades between the menu and game scenes

commit 55221d237d93139efbb9c5f47ff747afb7697ed1
Merge: 8b31919 ab4bebe
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 02:22:20 2021 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       game.jar

commit 8b31919d40e19bb8afdea273e1be4a9c08b17eb4
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 02:22:09 2021 +1100

    Added bumpmaps to the dice

commit ab4bebed279ef11bd0ed362c2734bb7c2bf4ada7
Merge: ce3c7e8 da45cfe
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Fri Oct 22 01:36:13 2021 +1030

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       game.jar

commit ce3c7e8568cb551f244121438aabc0e0c06dea86
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Fri Oct 22 01:34:29 2021 +1030

    generateMovesTest for PurCublino and ContraCublino

commit da45cfe0f4b788151edc5fda7732b0ad7db5c5f4
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 02:01:04 2021 +1100

    Changed the method of tile selection to use lighting effects rather than colored tiles

commit 734439004fe06956162f7875becd30dd143ddb49
Merge: b72cfb7 a6bd3a9
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 01:50:29 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit b72cfb7cf806252273a42fd869d0b51832256bee
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 01:50:20 2021 +1100

    Added restart game button to pause menu

commit a6bd3a9a86113ba9cc59148cde1039590543dc41
Merge: 8ea319e 59d8875
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 01:48:25 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit 8ea319e6940f06275399ef8121ab4d9b149bfd75
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 01:46:52 2021 +1100

    Documented ContraCublino.java and implemented DeepCloneable interface

commit 59d8875f2ede5046c7fd4fb8b8b706b3e8567df6
Merge: fb8c068 5c45224
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 01:42:00 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit fb8c068a756d4607eebc88e485f3cd031070c29e
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 01:41:51 2021 +1100

    Made the turn labels not grotesquely ugly

commit 5c45224940071580652b12493b51323e09ff4552
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 01:34:01 2021 +1100

    Documented Game.java and removed a lot of redundant things. Also, implemented DeepCloneable interface

commit 81a93c6c53ded39e5d80d0fd586e10c699d6ccbc
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 01:20:20 2021 +1100

    Documented PurCublino.java and implemented DeepCloneable interface. Removed redundant abstract method (only ContraCublino used it)

commit 3334b5bddc79b6b53112f73152908dfb3487473a
Merge: 95c0b46 663ad7e
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 01:20:09 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit 663ad7eca2b1db0bf2674bdfd5690b5d2083738f
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 01:09:56 2021 +1100

    Added instructions to menu

commit 95c0b466506c47140247451831a959c7265c6279
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 00:55:23 2021 +1100

    Documented RoseNode.java

commit cdf4b188d56e813361a1c8ae1d769bb1b5f668b5
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 00:50:00 2021 +1100

    Removed deprecated and redundant methods from Boards. Implemented the DeepCloneable interface and added comments

commit 8f8a8b588a2539d48690bff8d1a742a302b00b27
Merge: e16ca44 99f1e4c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 00:46:04 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit e16ca4480c5145dd5679a4ace92e5d543ec2c104
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 00:45:47 2021 +1100

    Started to fill out administrative documents for deliverable F

commit 99f1e4c0b1ed9666707b211100e555071de00ebb
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 13:36:54 2021 +0000

    Update F-best-u7227895.yml just so the pipeline passes

commit 55963b52a863d174993b6d62260f0db2792baaf1
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 00:29:55 2021 +1100

    Started to fill out administrative documents for deliverable F

commit 3c612736bdd65a035372be5f5c6574a3d00dfea0
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 00:18:23 2021 +1100

    Changed a package name in GuiAvatar that overwrote Ziling's previous changes

commit 4cb7f9b43c9f8229d1fe7a6676b8840c83039db1
Merge: 0485ce4 4ba56d7
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 00:09:11 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 0485ce402b4373687ff831c88b5ac0fdc7e2e32b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 22 00:08:51 2021 +1100

    Finished implementing GUI avatar icons

commit 4ba56d72df0f6987157bd7c2dd980963be50265c
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 22 00:00:50 2021 +1100

    Changed package names to fit Java convention (packages names should be in all lowercase)

commit 2f66e12119fd7bbb5126cf3464bf4a466f6cfda9
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 23:54:16 2021 +1100

    Added the DeepCloneable interface and documented Players.java

commit bd6401ad2868b7c4d5b928735573799807bf1c07
Merge: 43c0d00 58837f2
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 23:52:59 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 43c0d00ea5c676270ea841263705d09d0e938dd0
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 23:52:49 2021 +1100

    Gave each player an icon above their head, which will (in the future) represent whether they are human or AI

commit 58837f2b8651a04d7cc643435ffde8e7594adfbd
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 23:40:40 2021 +1100

    Added a test for Die.java

commit 81023fd32de046a7fc116728457bba3bcb3c59a0
Merge: 58b416c d4058ab
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 23:29:53 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 58b416c3f3212205a97292ba08e37f0aee1d74ce
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 23:28:15 2021 +1100

    Added an initial animation to games

commit d4058ab8964c198cb32983332175136e4655fd07
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 23:11:35 2021 +1100

    Completely removed all hardcoding from Die.java. Also, updated authorship details for Controller.java

commit 60ec1f329821f73ac259045c99d69a367441a727
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 22:27:45 2021 +1100

    Reverted an accidental code deletion that caused AIs to never make moves

commit 5e9f040c54a64af74d52e4852ed53ab557e82506
Merge: c08f713 7a84226
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 22:15:12 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit c08f713d069c04c17593062dd4223ffca358c794
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 22:14:57 2021 +1100

    The game no longer floats in midair

commit 7a842267c8b2afc69d7db12cd728e885ab12bb08
Merge: 9fbc499 b84e3b0
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 22:02:24 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit 9fbc499572cf07ffec440ae7ccd3b4fb3fcea172
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 22:01:35 2021 +1100

    Added comments to Die.java and deleted redundant methods

commit b84e3b04b143fa046d12ee74ea4cc8abefd656c6
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 21:58:29 2021 +1100

    Documented the Controller class

commit ddddd7a0a78696e748f7686957c886ee47018f12
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 21:48:13 2021 +1100

    Fixed a issue where Viewer was made incompatible by recent updates to GuiBoard for the main Board class

commit a8dc769f42823327604c070b2f37021d6cacf13c
Merge: d4a5d12 62fa40c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 21:43:22 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit d4a5d1245009f2104b0eb9b51e536b3cdd95649a
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 21:43:15 2021 +1100

    Added substantial documentation to each of the GUI classes

commit 62fa40ce3579f36cde26e1bd9e85595cbf119935
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 10:03:59 2021 +0000

    Aggregated all past originality statements

commit 48f34d90c1c1e8826a8bda734e5182822043573f
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 09:31:42 2021 +0000

    Added some additional features and fixed the task numbering

commit 188b3014fe954446691e3d6f9acf7ae378689547
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 20:20:35 2021 +1100

    Made Die constructor no longer hardcoded and removed redundant parameters from constructor

commit 82d895ca55b92c8c543bb7bc676d0083e2203774
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 20:12:15 2021 +1100

    Refactored repeated code in Controller and added pause menu

commit bcc6e403e2f81c8d4038ca221dc937816045a7dd
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 18:00:01 2021 +1100

    Remove irrelevant TODO

commit 007cbc083559ec73cf8a645e6f99725436cc53b4
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 17:48:55 2021 +1100

    Updated game.jar so it includes image files

commit 1638d383a897a8a62b1f072822f0d89d0a44a1ce
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 17:40:59 2021 +1100

    Players can return to the menu without closing/reopening the executable

commit 5e5860fcd78718808a88c88c8fd0a39f40e195d1
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 17:05:03 2021 +1100

    Finished skybox system and reorganised art assets into subfolders

commit b157da4649a8057a5cb64d50b16fbf30e0863b1e
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 14:52:54 2021 +1100

    Added skybox DLC system and an additional skybox

commit e4c29557bf6163f999180953cbf1306a6eccb8c6
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 14:30:57 2021 +1100

    Skybox DLCs incoming

commit fef50984267a4f66c3e37c5d6a61ad35315e7d3c
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 03:07:10 2021 +0000

    ... and once more

commit 7df86ccaab92b904433ceeed8c9e9086c25728ea
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 03:02:27 2021 +0000

    Hopefully, fixed formatting

commit 51d634d1a0390f8f05f39f9d1a02641841180e4c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 14:00:53 2021 +1100

    Implemented move take-backs tied to a button in the GUI

commit b634d010a1bc92401e54ff3532123e493e4fa3d7
Merge: dd60a38 56ade75
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 13:35:44 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit dd60a3862456fe8f837b8e39a0105e94b0145ac5
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 13:35:32 2021 +1100

    Added temporary sound effects

commit 56ade75f5eda61580c9414e4746e3321ff89b63d
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Oct 21 02:18:55 2021 +0000

    Update F-best-u7227895.yml

commit 7d61dd1586e9f9196cfeb9fdcb34970ac153b8e1
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Oct 21 12:27:12 2021 +1030

    small edition

commit 420f67af5de7d2b5481b9a0df99d5e46491f38be
Merge: 5a2819f 6c09960
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Oct 21 12:24:21 2021 +1030

    Merge remote-tracking branch 'origin/master'

commit 5a2819f8adafa969a8d5faaddda8e71cad308f6d
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Oct 21 12:23:58 2021 +1030

    Pur Cublino AI

commit 6c099602d782d0962d687d144a6a3ec4b1304af8
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 00:36:07 2021 +0000

    Update F-originality.yml

commit 5ebe861947abd110533a0a219fa1a59498a43d49
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 02:59:01 2021 +1100

    Fixed issue where users who made invalid Contra moves in the GUI were unable to do anything, causing a softlock

commit eec1cc9c16af859fa9b38cfff05373a4566fb551
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 02:51:31 2021 +1100

    Gave the text a background for the time being to make it visible against the skybox

commit f15bfd758d94bb297c7f28dba807e5ee3e424054
Merge: 9720c79 8ee9571
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 02:36:03 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 9720c79ddf3af6966e70bc64630ce7b7849bb350
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Oct 21 02:35:56 2021 +1100

    Added skyboxes

commit 8ee95716bb001df24a58240cca1e7bba363e1d77
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 21 02:11:31 2021 +1100

    Rewrote heuristic for greedyAI so it is no longer on a kindergarten level. Also, increased the lookahead to 2.

commit 61cd73df122902be2249c69125e5ba88443dc8e1
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 22:06:41 2021 +1100

    Players can't move AI's dice while they are thinking about a move.

commit dfb88e28e2b7b007be72cb21d5566c8d0b1f67ce
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 22:03:40 2021 +1100

    MC won't throw Exceptions because it's running not in the JavaFx thread.

commit e195bf7252c19b7b2bf2a8ea73f81a9b5677eb46
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 21:49:41 2021 +1100

    ...and MC shouldn't freeze either!

commit 038470ef00eebaad3a22d0d2122a401e0fe8fbdd
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Oct 20 21:44:27 2021 +1100

    Monte Carlo should work now

commit 4e371f9d91d33a8f530e7635a05a043cc0b73ed3
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 21:32:32 2021 +1100

    Adapted the greedy AI to work with the new patch

commit fab49fdc64cf2d0dc3bda34161e847e6d2af8a4d
Merge: 32ede5a 23f602b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 21:26:24 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 32ede5aaaecbe7a0fbe480385ff1f7df1021f18a
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 21:26:04 2021 +1100

    Applied a patch to fix AI movemaking in tandem with Ziling

commit 23f602bb82a4bd086a62a703249a906295f791e0
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Oct 20 21:20:23 2021 +1100

    Changed deepClone so most of them aren't serialising and deserialising because that is time expensive. Thus, improved MCTS 1000 visits time to be probably around ~20 seconds

commit e2db65b8d78ae5748da21c30ffad6b6a870f4e31
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 17:34:42 2021 +1100

    Prettified the board lighting

commit 6298bbe1f661cbcd0e4d1736fbf42488c646818b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 17:02:09 2021 +1100

    Removed debugging code for the AI interfacing with the GUI

commit 59990e3acd774f1618b96f41048da0f6e570d90d
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 16:48:59 2021 +1100

    Animated AI-controlled dice using a queue of steps

commit 38243e48108ee30f67c9b91553d5965ef78df09e
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Oct 20 14:34:21 2021 +1100

    Still trying to get AI working

commit fa428d3a0f692fe5133dd5a90affbb582758338b
Merge: 890a03f 110e40b
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Oct 20 02:14:26 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s
    
     Conflicts:
    	game.jar

commit 890a03fc3f2db3ad687e76cae72a10cb10d7c193
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Oct 20 02:13:22 2021 +1100

    Attempt at getting AI to work

commit 110e40b39d5516ce2ea5bb56cc8137cfcbeab58e
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 01:38:15 2021 +1100

    Added even more dice skins

commit 0fe1c6f0761b1738f4ef5ba27b38650552a10aaf
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 01:21:19 2021 +1100

    In Contra, deleted dice can no longer be selected, and they will disappear from the board

commit 2ab96f8d906d85a5848d7a66b0f4e9514bc301d1
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 01:01:11 2021 +1100

    Fixed bug causing animation to not play for tipping dice towards the first player (which is only possible for the second player)

commit ae489bcec45340e88e27f2c6b1d539acf0c41553
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 00:59:52 2021 +1100

    Fixed typo causing cloudy dice to render pure white

commit 4c046c5287084f7cf20dcb30a55dcf75d8af7d53
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 00:55:35 2021 +1100

    Added several new dice skins to spruce up the GUI menus and boards

commit 5ebd4ff3f0b760d59ce2579967a9ed510ba2ddf2
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Oct 20 00:40:38 2021 +1100

    Refactored animations into the AnimationTarget subclass

commit 06342a336bc423f37659458ef44f8d8998e91faa
Merge: 465e0b8 1749c70
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 23:58:25 2021 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       game.jar

commit 465e0b80d881e742e0856e3bdba557a3199371d0
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 23:58:16 2021 +1100

    Added translation and rotation animation to the dice in the GUI when making tipping moves

commit 1749c70991816e610951380247e6d4e8f7ed4f4b
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 19 21:04:58 2021 +1100

    Added a boolean 'deleted' to Die which signals when a die has been removed due to being 'eaten' in the Contra game mode. Is False by default

commit 655212c50caac4cd9fe4aea44253bb20c77a3054
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 19 09:28:55 2021 +0000

    Update F-best-u7312578.yml

commit 564f762a3ed0dbc8e4ac1413bd56a4b249d1c81b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 03:01:43 2021 +0000

    Signed contribution statement for deliverable D2F

commit 7de5339ddab059f31e5fc0669c043e2e405176ab
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 13:59:57 2021 +1100

    Changed Controller type to operate from an enum rather than subclasses

commit a61aa8cf25a404583f54d863ee789c3fbb4d8c70
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 13:28:29 2021 +1100

    Setup interface between front-end GUI and back-end AIs

commit cfea31ca37bf4c70dcf024597359502c3e2844cf
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 13:25:53 2021 +1100

    Refactoring for neatness in GuiBoard

commit 40d91f2db2087999ad3fd1d91d647927444f1352
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 13:17:31 2021 +1100

    Updated the turn label to reflect the players' names when making turns and winning the game

commit 450baba46984d82f9226af3a6fe4e4b4a407258b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 13:11:05 2021 +1100

    If a player doesn't change their name in the menu, it will adapt to changes in the controller settings

commit 1b6f7d240fb878a2ad9c804696db99c09ad19bdf
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 12:58:05 2021 +1100

    Added player settings to the Menu class

commit cfc1f231a238edcf973755056f06b14b87916ad7
Merge: 6a965a4 f58713a
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 12:39:37 2021 +1100

    Merge remote-tracking branch 'origin/master'

commit 6a965a466e952d1142c3c03cc892ec7c201bf770
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 12:39:28 2021 +1100

    Started implementing a menu for the Board class

commit f58713a4fd2a26efea9e8966cbd559a56f39b35c
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 19 01:32:42 2021 +0000

    Update F-best-u1234567.yml

commit c53971de6f875bd6a53a4f00f8a46217e544d8ae
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 19 01:32:07 2021 +0000

    Upload New File

commit c8d48ea344f5eed37b466469a2184a932edebd15
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 19 01:31:52 2021 +0000

    Upload New File

commit 1da5885a7e5d16ad4c137ebb63d9f205cf06164b
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Oct 19 01:28:50 2021 +0000

    Update F-contribution.yml

commit de677017f62c4aad6da9d56d4730ed842756b7f1
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 19 01:26:59 2021 +0000

    Signed F-contribution.yml

commit 0798d95b9092cc908d5f9097868beb41be0977d0
Merge: 49189f1 caa2d3a
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 12:15:56 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s
    
     Conflicts:
    	game.jar

commit 49189f187facb0cb9184d76ea9c46b803bfdb4be
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 19 12:15:47 2021 +1100

    Updated game.jar

commit caa2d3a9b504bb79f79547e0c4976fda2f3df13d
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Oct 19 01:03:28 2021 +0000

    Update F-originality.yml

commit e95c92eb952f43502c4c273e9cb48ae2569185f2
Merge: 0ba42a5 2e0c02c
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 19 01:30:02 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit 2e0c02c1036f07cb340562debc2e2db772a67832
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Mon Oct 18 14:29:41 2021 +0000

    Update F-contribution.yml

commit 0ba42a58d703a81da99a1d92a4b1e18d36b00ffe
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 19 01:28:37 2021 +1100

    Some refactoring to clean up unused code

commit 782ee223f4a165ea5df4dbf614f1e6f1625bed84
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Oct 18 00:40:15 2021 +0000

    Signed originality statement

commit bb2a0fca51e4280df13cb6d216058c799d3ad35e
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Oct 18 03:33:58 2021 +1100

    Rolled back changes to test files

commit 0d88ba0f864154b65c4df8e453bea0cda1496896
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Oct 18 03:32:15 2021 +1100

    Account for the edge case where all of White's die are eliminated in a game simulation. Removed first node bias for MCTS.

commit f195025477484b0c8a1aa5576a9366eda3bffcd0
Merge: 4a2e2ed 380dc3c
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Oct 17 02:45:06 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit 4a2e2ed8f77103ed3abdbdbdea0925376283b60a
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Oct 17 02:44:33 2021 +1100

    Fixed a bug in applyTip which didn't update the orientation of the die.

commit 380dc3c4da439b737854b4e5f6c421077584233f
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sat Oct 16 15:07:56 2021 +0000

    Signed F-originality.yml. Uncomment fields if you want to fill them in

commit af3e2b68673eadfd913ee4e5fe4365f38f9294fc
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Oct 17 00:35:40 2021 +1030

    Task 11 not complete

commit 9d05be681a84011ea0f4dba534207fff59f56423
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sat Oct 16 02:10:56 2021 +1100

    Implemented a rudimentary version of MCTS for contra. Needs to be optimised to be much more time efficient. Needs testing. Also, added a RoseNode class

commit dc3665eb0db0ae94a966db68c84c2bed0c78e99f
Merge: 2287fb1 b1fd39f
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 15 02:03:44 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s
    
     Conflicts:
    	game.jar

commit 2287fb17d3e5787492ac415aef7f9930ead9169a
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 15 02:03:26 2021 +1100

    Removed extraneous variable in GuiBoard

commit b1fd39fed16799401d89ed3f852038026bf0b83c
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 15 01:53:20 2021 +1100

    Fixed a logic issue in which applyTip and applyJump didn't actually update the player's die list only the die on the 2d board array. Also, deleted redundant Result.java

commit 8d35f083a21d506952737e235836b4db761e39f8
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Oct 14 01:49:28 2021 +1100

    Fixed a logic issue in generateLegalMoves (the turn not ending after move is generated) which affects game trees.

commit 0dd609fabc3e696e892ca5eb2cd13cda3f17aa3d
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Oct 13 01:10:10 2021 +1100

    Added implementation of greedy and random AI for contra. Needs testing

commit 9e3949295201d2bc788df1bc588cf4eaea36fbf0
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 12 12:31:11 2021 +1100

    Connected the GUI with early work in the various Controllers

commit 128416aa7b2e14437f2eaa3236478eba7f60e689
Merge: 7525a26 f5d5f20
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 12 02:31:16 2021 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       game.jar

commit 7525a26ae4dcbd3d2d67541121aaf409b95f9858
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Oct 12 02:31:01 2021 +1100

    Further documentation and began work on GuiAvatar

commit f5d5f206aa78585efce2d36c0699997b954775c3
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Oct 12 00:38:14 2021 +1100

    generateMoveContra now generates a random move instead of the first found move. Refactored generateLegalMoves to no longer be ugly

commit b6118bdb3d7815cb3f190fa63c3bdee6b40a6c45
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Oct 11 23:50:48 2021 +1100

    Some documentation and refactoring in the new GuiBoard

commit 7da41aa5c304812d2a441f343bc15ff85b784280
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Oct 11 23:18:45 2021 +1100

    Refactored the obscenely long BoardConstructor into a number of files representing each component of the board GUI

commit 635bfdb1dc0093214614788a896a26f1c2185485
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Oct 11 21:59:28 2021 +1100

    Modified the implementation of getWinner to be non-static, use enums and interface easier with the GUI

commit 55f218fd6b762e6657e611023736810062136006
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Oct 11 21:26:22 2021 +1100

    When a player tries to make an invalid step at the start of their turn, allow them to make a valid step from the position before (rather than after) the invalid step

commit baaca8dd8c03cc4e457415a2cb5290503ec68799
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sat Oct 9 22:26:33 2021 +1100

    Reimplemented the permitsMoveMaking boolean field in the new move-making system

commit 8567308442e74b353fcf4da6e3ab00b3ab4769c4
Merge: 184aa5f c6ad949
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sat Oct 9 22:23:14 2021 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       game.jar

commit 184aa5f85877891d9545ffd7a8d38ca4f2817141
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sat Oct 9 22:23:01 2021 +1100

    Moved the duty of selecting and making moves from dice to tiles

commit c6ad949900f15d0c9b7596729b193b7523c0c067
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sat Oct 9 19:25:03 2021 +1100

    Rudimentary implementation for Contra generate move

commit bfc9080e88fa9f19db4dc0638e35fc61f5a0b8f2
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sat Oct 9 19:18:31 2021 +1100

    Rolledback a change that broke a test for task 8

commit 357c086b4fb1839bb5cf1a669a771f3e0625b4d9
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sat Oct 9 18:00:55 2021 +1100

    Fixed an issue that allowed players to make a turn consisting of solely invalid steps

commit 8f4c37e27568cd6875333dfa168d6855ce81da3e
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sat Oct 9 17:50:16 2021 +1100

    Changed how multi-step moves are made to introduce the RMB. Now, users will hold LMB to select a die and indicate a move, then right-click to confirm a step. Moves end immediately after LMB is released

commit 6a10861dc4cbe29a3358ab014ea4fe06b998ad95
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sat Oct 9 16:34:36 2021 +1100

    Passed test 14b

commit 5a9c2c38c9480ee72be40bd20c8e285723d3ed7f
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 22:22:46 2021 +1100

    Fixed another logic error allowing incorrect turns to be made

commit 3a8b702a1c9de6199b17a145ee1be8bc77fea559
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 22:07:46 2021 +1100

    Considering IntelliJ's suggestions and warnings in BoardConstructor

commit 3e7555c6a61b15e4c0dd2464f616ab0ff21926b1
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 21:53:47 2021 +1100

    Minor refactoring in BoardConstructor for cleanliness

commit cf7e4032c143a6162801fb7485a729db0620babb
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 21:26:43 2021 +1100

    Added an end turn button allowing players to end their turn

commit 3fe655db101cbd2c2b6d58d95798074499e3cc38
Merge: bfc5c01 2ad6301
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 20:59:11 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s
    
     Conflicts:
    	game.jar

commit bfc5c0171774cbf3cac7b8976e5e1d312a89bf78
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 20:58:55 2021 +1100

    Added boolean to BoardConstructor representing if moves can be made (since they can in Board, but not in Viewer)

commit 2ad6301c01c81986af1e23185671c2dd4cff9d9f
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 8 20:55:20 2021 +1100

    Modified applyStep so that tips can only be played in the first step

commit 62c072fc6c42003ae7ec93b7b896f024b227c7ea
Merge: d5e2251 b679bc6
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 20:40:32 2021 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s
    
     Conflicts:
    	game.jar

commit d5e2251f9514d223fe8711f23de6d3e214334287
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 20:40:07 2021 +1100

    Updated the executable

commit b679bc6b3e4a05505b96547b913bdd9ccc239849
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 8 20:37:24 2021 +1100

    Implemented endTurn and modified applyStep so that it keeps track of the die being moved

commit 1c0ef336d38888e583bb06576dae8a66c10f57d8
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 19:40:33 2021 +1100

    Fixed mouseMagnitude()

commit b52274088209c947d6b69fcd5c944b0d180f53f3
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 18:23:18 2021 +1100

    Simplified mouse distance checks with a new function mouseMagnitude()

commit a370fb8540cbe3b0d2140c323d553f3c82f6f0cb
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 18:04:40 2021 +1100

    Allows the player to make jump moves (in addition to the already allowed step moves)

commit 30562256f68a6abb471918623d455128778a29a4
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 8 17:46:35 2021 +1100

    Moved identical behaviour in Viewer and Board into the new BoardConstructor, and added the ability to make moves based on the indicator

commit ea301531a1a307d54ac0cd1331e24df1c656d9a1
Author: georgielyall <u6431454@anu.edu.au>
Date:   Tue Oct 5 13:56:47 2021 +1100

    Deliverable D2E Feedback

commit 392559b916049042e66e805cd32167da96b2233c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 1 01:44:54 2021 +0000

    Update E-contribution.yml

commit da0c6c76f54f191fa264111eb6a76ec5183654e8
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Fri Oct 1 00:39:56 2021 +0000

    Update E-originality.yml

commit 46d4c08aa2592f0df8b2f3cc4e12c7ad2740c3f2
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 1 04:09:04 2021 +1000

    Changed build to be based on Board class rather than Viewer

commit 35e9e93ba95cace205b315899117565b2ae9ff17
Merge: 6aa2eee 638f30b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 1 04:05:22 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit 6aa2eee6440b46f4dce6574a494954d1fd13b0a3
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 1 04:05:04 2021 +1000

    Duplicated some code and added very basic functionality into Board, which should be refactored later

commit 638f30bd3c449a16c7fa037e37df522fcfa04d1c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Sep 30 17:51:36 2021 +0000

    Signed originality statement for deliverable E

commit caf331b37259a9908c3f740631ccc788f87bc02b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Sep 30 17:50:46 2021 +0000

    Reviewed Ziling's code

commit 9c948c87a04a98cafbea8b6a5b252f5e04c5ef9f
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Sep 30 17:07:48 2021 +0000

    Update E-contribution.yml

commit 398510badfd6be9602288e759348d2645342645c
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Sep 30 17:06:38 2021 +0000

    Update E-contribution.yml

commit af2ae9415ba02b8a18ab8e893556ee2940560137
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Sep 30 17:06:06 2021 +0000

    Update E-originality.yml

commit bae2afe32289a824795b01ee2e22b4ff166c1ad1
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Sep 30 17:00:50 2021 +0000

    Update E-review-u7227895.md

commit 524f02a6527ed9a26235f9e1d691407946dc7765
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Sep 30 17:00:23 2021 +0000

    Update E-review-u7227895

commit 436976cb2762ef1b0bfd5d5853caa5d456b6b134
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 1 01:04:10 2021 +1000

    Added authorship to Viewer.java

commit ad3ea36da75862f8a47af4b825959594227f1fa7
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 1 00:45:14 2021 +1000

    Added Manifest build file

commit 92c6c20e46061d95050129dc41597aa9d8619302
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Fri Oct 1 00:20:35 2021 +1000

    Made .jar run without errors

commit 6f28f5a11d3a4f5f7e9865982c7fa63a777b259a
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Thu Sep 30 23:33:59 2021 +1000

    Generated a .jar for the game

commit 9529fc37416a52cf5cad2788faf6be98f8f5896e
Merge: ca2ca31 bae990a
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Sep 30 21:32:13 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit bae990a1c56b3e864825d255933503557ad6cc44
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Thu Sep 30 11:32:09 2021 +0000

    Update E-review-u7312578.md

commit ca2ca3155fd7e6be6e66e26c5391fa21f0a40501
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Thu Sep 30 21:27:56 2021 +1000

    Added tentative authorship details. Feel free to change. Mostly didn't do it for files I haven't touched.

commit f24e7e5731ba347d7d0dfb7f55e681c40d272ef3
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Thu Sep 30 03:39:13 2021 +0000

    Update E-review-u7227895.md

commit 25c72ae9dc08de7d8fcca6b4af95a2e91748b716
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 29 10:21:28 2021 +0000

    Update E-review-u7312578.md

commit 8c6c41a4d66ed16a7064d1f343ab9ec1a652382b
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 29 10:21:01 2021 +0000

    Upload E-review-u7334218

commit 9141129b53f89f32b16f732c8fe8960c833d20f3
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 29 10:20:22 2021 +0000

    E-review-u7227895

commit 4294173a302b2d7cee7d8bac7557e596dd30cdad
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 29 10:17:29 2021 +0000

    Sign E-contribution.yml

commit a388357b925b6b454d425e118de9edc1bb8a1023
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 29 10:16:02 2021 +0000

    Sign E-originality.yml

commit 8125efa9e8e86153219d9388b5945a3531b94232
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Sep 29 02:53:17 2021 +1000

    Further indicator simplification and refactoring

commit 4af8d7f8fc4372ed805ac8b8938d5ac8da5b2acd
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Sep 29 02:47:50 2021 +1000

    Moved mouse position information into the DieModel class and added null spots to move intention indicators

commit 0e645e5b5d09888492f29943578eb71061c69bfa
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Sep 29 02:42:12 2021 +1000

    The user can indicate their intention to move a piece in a particular direction

commit cb433ca0a50c87b51d8689250e47aef73d4a4b59
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Sep 29 02:30:43 2021 +1000

    Selecting a die also selects the tile underneath it

commit 9e30fc3fe8b57c05eb8e9dd471422daf7f92ea27
Merge: 6e1add6 21ba218
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Sep 29 02:01:52 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit 6e1add6f6693ca90296daf187f8a0601f1be1978
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Sep 29 02:01:44 2021 +1000

    Added visual feedback to dice selected by mouse press+hold in the Viewer

commit 21ba21814bdd88c491f3cc0173b16a26af4f3745
Author: georgielyall <u6431454@anu.edu.au>
Date:   Tue Sep 28 19:09:47 2021 +1000

    Deliverable D2D Feedback

commit cfa2d80f029046c6f69f1829abe8d2da69a72b1d
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Sep 28 12:43:03 2021 +0930

    Task 9 applyMove

commit 0aa685d9ccb3c1c3e7f9121f9d324536e91cff7b
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Sep 28 00:29:41 2021 +0930

    isGameOverContra finished

commit b26ea6ea02c81dd1c19ab466604bbe97157eb46b
Merge: 4d1e138 e0c7cda
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Sep 28 00:17:48 2021 +0930

    Merge remote-tracking branch 'origin/master'

commit 4d1e1388bbdb1a6f15e18d1ef3d412e2f2a21a1a
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Sep 28 00:17:41 2021 +0930

    isGameOverContra finished

commit e0c7cda9540bb420a62ffcf82554ea2c7db77cc0
Merge: 5e70cdc e34af66
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Sep 28 00:13:27 2021 +0930

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1140/ass2/State/Boards.java

commit 5e70cdcf2f28dc50309a826d5ee681d5b64487ca
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Sep 28 00:12:27 2021 +0930

    isGameOverContra finished

commit e34af66a909e710ec6c9ddae0da4f172bcf83f6d
Merge: 37ae143 a82455a
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Sep 28 00:17:26 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit 37ae1432a86322475bad8b916e356c112d4c3d4a
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 15:16:32 2021 +1000

    Fixed applyStep so that it accurately tracks step history via deep cloning

commit f96e49bd94a02aa1ebfff9391f2d7d0f0c1841bc
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 14:52:07 2021 +1000

    Added encapsulation

commit a82455adcd62c955bbed9c73d213f0711cd3d15c
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 04:36:53 2021 +0000

    Update D-newcode-u7312578.md

commit 92ed4da0149e796a7712ffcdbbf9c6f5cd32c624
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 04:23:51 2021 +0000

    Updated the link to show the same code after adding additional lines of code

commit ad7016e594125d5b5e5ed19c4dc2b7e4e4f8ff10
Merge: b685ba5 a67b582
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 14:22:18 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit b685ba514313162bfa66b3fae08eb99a1c0b1e4d
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 14:22:10 2021 +1000

    Added javadoc to some functions in the DieModel inner class of the Viewer

commit a67b582cc24b7e1af59004e16d8ce3468ef4763b
Merge: ace6c11 1cf1c14
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 14:08:11 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit ace6c110b6337b411ad866ca0b79aa3931837d75
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 14:07:45 2021 +1000

    Update tests

commit 1cf1c14627aa6cdd951c64a65d3a188af8fe7e0b
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Mon Sep 27 03:10:10 2021 +0000

    Update D-originality-u7227895.yml

commit 9c6609bc8858c859b07146f0f4f5a806b622e211
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 03:03:52 2021 +0000

    Update D-newcode-u7312578.md

commit 011dc56f6bb1e4a2a551df90090324eabe5a6b00
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 03:01:20 2021 +0000

    Signed D-originality-u7312578.yml

commit b5de9887f6ccf637a7bcae95d6255e703f8a9b9d
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 03:00:43 2021 +0000

    Update D-originality-u7312578.yml

commit d30a59bd9ace20095598eed6e5ee770eb85b6e36
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 03:00:08 2021 +0000

    Upload D-originality-u7227895.yml

commit f0986c4849145bcd755abc501204f0895c14b9b3
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 26 18:12:13 2021 +0000

    Update D-originality-u7334218.yml

commit 7cd0df2f0846e75bb902c8c449d8ed2a5471ced0
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 26 18:11:36 2021 +0000

    Created my originality statement

commit bf118b04b9bf3f369355d4a955a8414953a050a3
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 26 18:09:40 2021 +0000

    Wrote in my best recent contribution

commit d55a8c8e3cd57ee6bac47b39a0575b89ef33e19a
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Sep 26 17:15:11 2021 +0000

    Update D-newcode-u7227895

commit 29cc0cbf554dcf396b813a87fcac8d4f421f1a5d
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Sep 26 17:12:28 2021 +0000

    Update D-newcode-u7227895.md

commit 9e233c8c4ed185125f83924b89e77d855949372c
Merge: 74b075c c9cf163
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Mon Sep 27 02:36:20 2021 +0930

    Merge remote-tracking branch 'origin/master'

commit 74b075cf1b1a84e2b4d6042c042ede8ec7b2d451
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Mon Sep 27 02:36:08 2021 +0930

    die to encoding test

commit c9cf1631f448a4d20121510fcc9e44277f0ea2c2
Merge: f6c67c5 d38c866
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 02:00:02 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit f6c67c5cb25edeb18bbb305c8561f5a09a7a7ca8
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 01:59:54 2021 +1000

    Added further documentation to Viewer and its unit tests

commit d38c866ca331f914eb191de4a78bcbbe0f6bbfb3
Merge: f102be2 227f88b
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 01:28:26 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit f102be2e69dcbedcb4fc41813918a7786dedde3c
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 01:27:27 2021 +1000

    Updated admin and test so that it actually runs

commit 227f88b70dbf4df2012bd9ab1136b54d018ccf3d
Merge: c8e611a 3912f0d
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 01:27:08 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit c8e611ab88aff4d12875107acda6fe496e0ecd4d
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 01:27:01 2021 +1000

    Added further documentation to Viewer

commit 29730296afe07a7c3e42149571fa4770630bc702
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 27 01:24:57 2021 +1000

    Changed method name back after fixing timeline

commit 3912f0d96813a97b025f4df11287d5f11cf87e78
Merge: 6d7c4ff 2a6ccf6
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Mon Sep 27 00:45:55 2021 +0930

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1140/ass2/Cublino.java
    #       src/comp1140/ass2/State/Boards.java
    #       src/comp1140/ass2/State/Die.java

commit 6d7c4ffbaddf4333ddf679cc27968478c6832cee
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Mon Sep 27 00:41:48 2021 +0930

    Task 9 update -- helper functions implemented but the task is not finished yet

commit 2a6ccf6350e04de537ea0c3b1bc7c289b8c43857
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 00:46:27 2021 +1000

    Modified function call names in Viewer in alignment with refactoring of Die

commit e15d779fe379dc953f59c87713f3452e72a81a23
Merge: b8fc76a 8dfda17
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 00:38:30 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit b8fc76a76ce0a1375ea027d4884d4947a79bc91f
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 27 00:38:24 2021 +1000

    Added more tests corresponding to checking the die mesh's rotation

commit 8dfda17cf67c65245aea6fa7d322cf737724b4a3
Merge: 3613561 e8811c3
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Sep 26 23:55:59 2021 +0930

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1140/ass2/State/Boards.java
    #       src/comp1140/ass2/State/Die.java

commit 3613561da1039d397fce0106aeef1b0a30c714f6
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Sep 26 23:18:34 2021 +0930

    Task 9 update -- helper functions implemented but the task is not finished yet

commit e8811c3f91e69afb7a7c3012cc1e21e306f47df2
Merge: dce191a 12b85c0
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 26 22:45:40 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit dce191a21490e5fc46e9530cc0b71238d9c1d273
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 26 22:45:15 2021 +1000

    Created unit tests, starting with the MeshView for the dice model

commit 12b85c082d29340652c02e5c10bdfa56f5d3f01b
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 26 08:06:36 2021 +0000

    Upload D-newcode-u7334218

commit ce2f01584679f8fb825045814d20031a810af85c
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 26 08:05:44 2021 +0000

    Upload D-newcode-u7312578.md

commit d8e60c2847014c1b7606b8e31b5e1bbec9f70b46
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 26 08:04:39 2021 +0000

    Update D-newcode-u7227895.md

commit a92c3aa7281bb0a976be3dd800e039b509ba85e0
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 26 17:59:52 2021 +1000

    Code refactoring

commit da023bd2d79ef1394250c725954f33856fe8bed6
Merge: 9f32a5a 4b933b3
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 26 17:36:49 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit d2b6e2c486fe14070d5a2e8fc38a27961a39152c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 26 17:19:36 2021 +1000

    Added additional documentation to DieMesh methods

commit 4b933b3601a8a51599473150fe887e18d6dc1378
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 26 17:14:27 2021 +1000

    Refactored to introduce the Die class and fix a bug with rotations

commit 9f32a5a6d4490cdeb1a68be5bcf0bf46db3e0ad5
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Sep 24 21:21:46 2021 +1000

    Code refactoring

commit 448ba8e719e2acf3405279fb1511fb1e0db580d7
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Fri Sep 24 21:21:03 2021 +1000

    Completed one part of applyStepTest

commit ba27abd2516516da1b55f8ec7e6fc16b67bf60a3
Merge: f3ce03c 3ddb5d4
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 22 22:14:02 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit f3ce03cae56647ee504f2ae069bc26bda8e3c259
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 22 22:12:55 2021 +1000

    Task 8 now passes tests

commit cfc1d49aeb270e1d4783ce77e3aab515cbe16658
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 22 20:51:03 2021 +1000

    Changed Die to fit constructor change

commit 8bed9f386aebba197a9f8f2b6d6f2ed0d9013f31
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 22 20:34:33 2021 +1000

    Changed Die constructor

commit 3ddb5d417c5f6f399a4935df562fdfc561c51e52
Author: georgielyall <u6431454@anu.edu.au>
Date:   Mon Sep 20 19:33:35 2021 +1000

    Deliverable D2C Feedback

commit b349b443bcda451000555922d7072a3d091775d3
Merge: 00bd67b f5e91c6
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 20 12:36:21 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit 00bd67b4dc6a4111b96c3e985218dc8200f74667
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 20 12:36:15 2021 +1000

    Fixed issue with multiple boards being displayed at once

commit f5e91c664f749e13e8b066eb2aa3607e66ff1b08
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 20 02:26:24 2021 +0000

    Update C-best-u7312578.yml

commit 3b73d212e10c1262ab2e40402ffe46fdde8556a3
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Mon Sep 20 02:24:38 2021 +0000

    Update C-best-u7227895.yml

commit a10bead0bf39395c66a82d328f03efacff9a4c1d
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 20 01:18:13 2021 +1000

    Improved dice assets

commit e7fd7e0eba36c92c3fdc72d4366e78d28b411c4a
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 20 01:15:30 2021 +1000

    Added handling for invalid board states

commit 667ade9834257df170c3971bbae4ab4784be6b52
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 20 01:01:29 2021 +1000

    Moved GUI assets to the rooted assets folder

commit 6cae0e264db483f347d859cc7192b7a251d6f626
Merge: 664323e 9450456
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 20 00:56:01 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit 664323eab376e882e62655fec492b0e942bb2261
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Sep 20 00:55:54 2021 +1000

    Added HUD instructions to explain how to use the 3D Viewer

commit 9450456aad8cd297c860ec2501a0daab09cc323e
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 09:27:18 2021 +0000

    Update C-best-u7312578.yml

commit 9e63d24658c4231c7d7553b0c0e446ede8ab9eb2
Merge: 581e5a9 002062f
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 19:16:43 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit 581e5a9d2438fd936e3cf27db68c67e05ff9d4eb
Merge: f11ae7e a9542e1
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 19:16:03 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 002062f1124178883270174985f9f0ba8c259ca5
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 09:15:06 2021 +0000

    Update C-best-u7312578.yml

commit f11ae7e1f03bd00ae20cc3a29b6d5b99d380e4e9
Merge: c8c4fd5 4bbfc22
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 19:07:57 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit c8c4fd5c271a4e7dbdddcb84ea4e6ef15c546526
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 19:07:20 2021 +1000

    Refactor task 7

commit a9542e103fc8db713cdb5fb7517794a7f33353b8
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Sun Sep 19 14:30:58 2021 +1000

    Fix inconsistencies between isStateValid test and specification

commit 4bbfc22235b93071e096ad6e8047da3efc00fbd2
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:33:41 2021 +0000

    Update C-originality.yml

commit fadfda03fb3788a01d4f9266cabf3ae8dbe4d1ab
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Sep 19 03:27:14 2021 +0000

    Update C-best-u7227895.yml

commit 4dd50d3ba6aa2303dd82d933300fc37199138a1b
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 19 03:26:38 2021 +0000

    Update C-best-u7334218.yml

commit e7e751543bd5bdc1d631bcf5f8ae8b8710fa91b8
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:25:30 2021 +0000

    Signed C-best-u7312578.yml

commit 501b977634410c1c3442d41d35cf8cab3019682e
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Sep 19 03:24:02 2021 +0000

    Update C-contribution.yml

commit 26c6ea96aff76c7f9009e2a266d75b91fe6acf0c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 19 03:20:52 2021 +0000

    Update C-contribution.yml

commit 3caa15329eb5f3c97581cb3e8089d5a6f4cbfd72
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 19 03:19:48 2021 +0000

    Update C-originality.yml

commit b36cc6f0ab469698d180895645711ba12baf256c
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:16:54 2021 +0000

    Update C-contribution.yml

commit 840b56f660fd0c31d57e9038654aed710b27a971
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:15:52 2021 +0000

    Updated C-originality.yml syntax

commit e0cb69fd1528ac4cc2d818acb9b9448134d5681d
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:14:28 2021 +0000

    Upload New File

commit 3a24859f374c98794710e229c407945367d36ba1
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:13:54 2021 +0000

    Changed file name

commit c58d3fd747d006ed1d60a0d77d2208d23ebebd24
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Sep 19 03:13:23 2021 +0000

    Update C-contribution.yml

commit 0c7a63d58a15add8caa983a35b776082954b274c
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:12:56 2021 +0000

    Upload New File

commit 2a768cd73b6e7e0a05a2a3a3e157cd73e7b4688f
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Sun Sep 19 03:12:25 2021 +0000

    Yuechen signed the C-originality

commit 93a9365402cfb1f58ee3ac6955b7d56cd766196b
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:11:08 2021 +0000

    Updated C-originality.yml because no collaboration

commit f4349c04aa77619629cbef8aff6faab052acf6ac
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:06:41 2021 +0000

    Signed C-contribution.yml

commit 43a98cc8df7daad2be9f7f4934cf2d021902ace4
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 19 03:05:38 2021 +0000

    Signed C-originality.yml

commit 5e406d79957351025d9cab9728816f90c41806ef
Merge: 8ea1c1e d11e79c
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 19 02:23:19 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit 8ea1c1e65600bdc3824a54bfdddc9fae1340494f
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Sep 19 02:23:07 2021 +1000

    Replaced the automated rotation with code allowing the user to drag their mouse horizontally to rotate the board on command

commit d11e79c4ab9804442e6747ee609e0f48c20fbe44
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Wed Sep 15 18:59:42 2021 +0930

    task 6 update - remove useless variables

commit 485b13855b140ccc97a7a7e02504cdbb1a2c1b4a
Merge: 466c3de e697659
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Wed Sep 15 15:39:20 2021 +0930

    Merge remote-tracking branch 'origin/master'

commit 466c3de790b300d268a04fd6d5ca634c15435551
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Wed Sep 15 15:37:18 2021 +0930

    task 6

commit e69765956eab18304ef8b905277c15adf8fe2a88
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Sep 14 15:05:50 2021 +1000

    Refactored code to remove magic numbers

commit 2f3c48a24204d2cea981c9b098ff8c5c4ccb02a2
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Sep 14 01:19:49 2021 +1000

    Converted the state displayer to 3D using textured cubes for dice, added files for the art assets, further developed code for displaying game states, and moved the code into the correct file (from Board.java to Viewer.java).

commit 98f23ece957c3d44af278a25c648b294eade4f66
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 12 19:19:10 2021 +1000

    Completed Task 7

commit 92b1cee67dd28ff108395892dba523f7a228ed86
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Wed Sep 8 15:34:58 2021 +1000

    Refactored some more code

commit 226601056cf323397f6a3895c4351c90531c9d41
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 6 21:03:17 2021 +1000

    Refactored to make code clearer

commit 3c63f8004a2d4df52160edbac1658109aac2204f
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 6 18:55:31 2021 +1000

    Completed Task 4

commit ec92be0a7a71f639467f98d686858c36c923e550
Merge: d1f16ba 24a6f54
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 6 18:17:17 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s

commit d1f16ba2b76865ee749a08b6f9fbac1d61779361
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 6 18:17:10 2021 +1000

    Untracked file

commit 6783514c128f87fa066758589537cc9750be3de1
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 6 18:13:01 2021 +1000

    Added methods to PurCublino.java

commit 24a6f549020a90bb365a4a6d0871675e9f724877
Merge: 60f6610 c100b40
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Mon Sep 6 17:19:09 2021 +0930

    Merge remote-tracking branch 'origin/master'

commit 60f6610e8270fe8f6b60c65a16f5c8829e60bace
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Mon Sep 6 17:07:24 2021 +0930

    task 3

commit c100b4003e913fb1eda645bf4d1876cc56bd7f2e
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 6 17:07:48 2021 +1000

    Added some setters and getters. Also, started on Task 4

commit 149ab36bb3d885e3f33c907cacd4f973dd6f1845
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Mon Sep 6 15:15:40 2021 +1000

    Created class names

commit 1119cc5a3ec504bf48d5dca4ba5d9fdd6937c89b
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Sep 5 21:39:31 2021 +1000

    Dice orientation based on character

commit 2dcdfc7bbf70866675d3b875b38bb3a95ef63c21
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Wed Sep 1 03:52:25 2021 +1000

    Initial work on displaying game states visually and implementing necessary parts of the underlying framework

commit 581c09bfb87bd89d5dd2b95dc90022bd370c8873
Merge: 85e288d d1f357c
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 31 11:56:11 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 85e288dff92f0a5a2d5a102e43a55bd76cbbbf69
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 31 01:35:50 2021 +0000

    Updates tasks.md for week 6

commit d1f357c882ebe22cd97647d11979442e74b0704d
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Tue Aug 31 07:24:19 2021 +1000

    Fixes to tests

commit 717a076889b7d28c033658cd6ae4f4cb15b4f8be
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Aug 29 21:13:50 2021 +1000

    Pulled updates from upstream

commit bd0b6d3fdd9a760f40d61cb4886863514664f5bc
Merge: 467e5eb b912110
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Sun Aug 29 21:10:05 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit b912110b86c5786cf7609e17f7a68ce57a497b2d
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Sun Aug 29 14:56:56 2021 +1000

    Fixed test outputs having expected/actual the wrong way around.

commit 755f138982d4d91dc1aa766e083f2a7ab630fc78
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Sat Aug 28 19:20:22 2021 +1000

    Clarification and correction to test

commit 43ae826398f8a6ae1ee7a2e5cfa4be5dcd5090c4
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Fri Aug 27 10:07:11 2021 +1000

    Fix json schema

commit fc42ef510dd117c39d6a3eeb13f2ebdd1a9bfac0
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Fri Aug 27 09:48:46 2021 +1000

    Assignment details

commit 467e5ebf6a7f336696448aa0dc2244ee3e7a107d
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 24 13:11:34 2021 +1000

    Added back in gui package

commit a0f6363ebd165a7d0efd4227574ed37d23105a36
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 24 13:01:48 2021 +1000

    Added line in Main.java

commit 09eea742d1ce624cde07ca4822a382100cca69bc
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Tue Aug 24 13:01:05 2021 +1000

    Fix JDK name

commit 190cdd1b696922f763c09d011cb05b703d4cef05
Merge: b925efc b2245ee
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 24 12:59:50 2021 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u7312578/comp1140-ass2-tue12s
    
     Conflicts:
    	src/gittest/Main.java

commit b2245eefe9ac6baaafcb3bf9f25815506bf91d7e
Merge: fc3513a 90e7057
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 24 12:59:30 2021 +1000

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/gittest/Main.java

commit fc3513a2afa5eacdf362c0246c60b3777769793e
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 24 12:57:59 2021 +1000

    Modified the Main class

commit 90e7057d578ad2e4db547de4412dd87151ef53f8
Author: Alice <u7227895@anu.edu.au>
Date:   Tue Aug 24 12:18:39 2021 +0930

    Add A

commit b925efc45f6f56e8e872752da8f6347fcac149ed
Author: Cindy <u1234567@anu.edu.au>
Date:   Tue Aug 24 12:46:29 2021 +1000

    Added line in Main.java

commit fdd61ba266801fb7d708c49b0313b7cfd0b20738
Merge: 13e99e6 d57d00d
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 24 12:41:08 2021 +1000

    Merge remote-tracking branch 'origin/master'

commit d57d00dde311eba7a51640121177b308a3ba5de4
Author: Cindy <u1234567@anu.edu.au>
Date:   Tue Aug 24 12:39:25 2021 +1000

    Created class C

commit 13e99e6ec281064335165e37ec22115302b3631f
Author: Bob <u7334218@anu.edu.au>
Date:   Tue Aug 24 12:37:17 2021 +1000

    Completed part 2 of the task

commit fec9610b749ed5426ced2ff88212831d8a9c4215
Author: Alice <u7227895@anu.edu.au>
Date:   Tue Aug 24 12:04:16 2021 +0930

    Alice finished her part

commit 8075d9567730d37895569cf98cd5b52756b4ea08
Merge: 852fd57 49f3538
Author: Yuechen <u7227895@anu.edu.au>
Date:   Tue Aug 24 11:37:23 2021 +0930

    Merge remote-tracking branch 'origin/master'

commit 852fd57c1774778cc3dcaa9f1ac13cd86ec567d6
Author: Yuechen <u7227895@anu.edu.au>
Date:   Tue Aug 24 11:36:45 2021 +0930

    Skeleton update -- A tiny mistake was found and fixed

commit 49f3538d4172dd807ae4804342e31bf9f36943f6
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Aug 24 02:00:22 2021 +0000

    Update B-contribution.yml

commit b7b47076bed250df672303448c0d96b3816ea0e1
Merge: 91a857d 5fcab9b
Author: Yuechen <u7227895@anu.edu.au>
Date:   Tue Aug 24 11:28:54 2021 +0930

    Merge remote-tracking branch 'origin/master'

commit 91a857dd3d3c4dbd0586384e72869ca914691938
Author: Yuechen <u7227895@anu.edu.au>
Date:   Tue Aug 24 11:28:36 2021 +0930

    Skeleton update

commit 5fcab9b9036adddb2279a866657c00e5fcf4fee0
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 24 01:41:13 2021 +0000

    Final version of B-design

commit 82e60c038fb42677c873f0afa8b7f73d98d94bd0
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 24 01:22:01 2021 +0000

    Signed B-contriution.yml

commit 9f224448963ba43148f4ad53d3eec80cd9886773
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 24 01:20:49 2021 +0000

    Fixed a typo

commit 74959f818d06ab298fe61c1975541135dd506df4
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 24 01:20:47 2021 +0000

    Removed unnecessary information from B-originality.yml

commit 7ae0a70a46445f0f45a659ad20f205f4ddaf5cc8
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 24 01:20:36 2021 +0000

    Added contribution information and signed the statement

commit b4a3f85b2433d15f47d51075b96b7dd18f847aad
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 24 01:16:13 2021 +0000

    Replace B-design with an updated version

commit e6da01d91c174566bbe416cc6812f946b32f1289
Author: Ziling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 24 01:12:31 2021 +0000

    Update B-originality.yml

commit fd6266702f55d7f54fffc591022fe4ed429d2020
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Mon Aug 23 18:13:09 2021 +0000

    Added a first draft of the design pdf

commit 6fd53daf079c3e7161eb421c69295daef33c0486
Merge: 677cc25 ecb4f00
Author: Yuechen <u7227895@anu.edu.au>
Date:   Tue Aug 24 01:04:04 2021 +0930

    Merge remote-tracking branch 'origin/master'

commit 677cc2546d51f33d49a52ec8b23b88069300b372
Author: Yuechen <u7227895@anu.edu.au>
Date:   Tue Aug 24 01:03:39 2021 +0930

    Skeleton

commit e21d642e564c2aeacdfc52fb08d669ae5d0d7e2e
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Mon Aug 23 11:51:40 2021 +1000

    Remove later tasks from CI for now.

commit ecb4f00cdd7bcb17f95f38c449256cd718a8c1ab
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Sun Aug 22 05:10:54 2021 +0000

    Update tasks.md

commit d8ae76f615fefd7542e4498f6532ba7954baa6d9
Author: Yuechen Liu <u7227895@anu.edu.au>
Date:   Tue Aug 17 03:42:30 2021 +0000

    Yuechen signed the originality

commit 1f3bdbca48536c45280c107d70727977303dd024
Author: Zling Ouyang <u7312578@anu.edu.au>
Date:   Tue Aug 17 13:41:08 2021 +1000

    Completed B-originality.yml

commit 959cddf71956ac47c72216f6d2bb76cfbfbc7db0
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 17 03:40:23 2021 +0000

    Zane Gates signed the originality statement

commit 45be8d6d69e98d14b5c818b0accbfa845ba60264
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 17 03:34:53 2021 +0000

    Schedule meetings for this week and next week

commit 452c2d173d55f6becae966c5ea3f84dffee786e3
Author: Zane Gates <u7334218@anu.edu.au>
Date:   Tue Aug 17 03:26:02 2021 +0000

    Added all group members to members.yml

commit 9454375ad23a64bbfcbd135aca14b2c3d32fa885
Author: Steve Blackburn <steve.blackburn@anu.edu.au>
Date:   Tue Aug 17 01:38:26 2021 +1000

    Initial import
```
## Changes
``` diff
Only in comp1140-ass2/.idea: artifacts
diff -ru -x .git ../master/comp1140-ass2/.idea/runConfigurations/Board.xml comp1140-ass2/.idea/runConfigurations/Board.xml
--- ../master/comp1140-ass2/.idea/runConfigurations/Board.xml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/.idea/runConfigurations/Board.xml	2021-10-23 12:53:33.000000000 +1100
@@ -5,7 +5,7 @@
     <option name="VM_PARAMETERS" value="--module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml,javafx.media" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1140.ass2.gui.*" />
+        <option name="PATTERN" value="comp1140.ass2.gui.guiPieces.*" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
diff -ru -x .git ../master/comp1140-ass2/.idea/runConfigurations/Viewer.xml comp1140-ass2/.idea/runConfigurations/Viewer.xml
--- ../master/comp1140-ass2/.idea/runConfigurations/Viewer.xml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/.idea/runConfigurations/Viewer.xml	2021-10-23 12:53:33.000000000 +1100
@@ -5,7 +5,7 @@
     <option name="VM_PARAMETERS" value="--module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml,javafx.media" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1140.ass2.gui.*" />
+        <option name="PATTERN" value="comp1140.ass2.gui.guiPieces.*" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
Only in comp1140-ass2: META-INF
diff -ru -x .git ../master/comp1140-ass2/admin/B-contribution.yml comp1140-ass2/admin/B-contribution.yml
--- ../master/comp1140-ass2/admin/B-contribution.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/B-contribution.yml	2021-10-23 12:53:33.000000000 +1100
@@ -7,20 +7,20 @@
 # State your contributions as integers out of 100. The total contribution should
 # be 100 or 99 (33/33/33 is fine).  (Remove entries if you have fewer than three
 # members).  
-contributions:
-  - uid: 
-    contribution: 
-  - uid:
-    contribution:
-  - uid:
-    contribution:
+contributions: 
+  - uid: u7227895
+    contribution: 33
+  - uid: u7312578
+    contribution: 33
+  - uid: u7334218
+    contribution: 33
 
 # Sign *your* name and uids here. (Remove entries if you have fewer
 # than three members)
 signatures:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
\ No newline at end of file
+  - name: Yuechen Liu
+    uid: u7227895
+  - name: Ziling Ouyang
+    uid: u7312578
+  - name: Zane Gates
+    uid: u7334218
Only in comp1140-ass2/admin: B-design.pdf
diff -ru -x .git ../master/comp1140-ass2/admin/B-originality.yml comp1140-ass2/admin/B-originality.yml
--- ../master/comp1140-ass2/admin/B-originality.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/B-originality.yml	2021-10-23 12:53:33.000000000 +1100
@@ -27,9 +27,9 @@
 #
 # Add as many "name+comment" entries as necessary
 # (or remove it altogether if you haven't collaborated with anyone)
-collaboration:
-  - name:
-    comment: >-
+#collaboration:
+#  - name:
+#    comment: >-
 
 # Use this to list any code that you used that you did not write,
 # aside from code provided by the lecturer.  Provide a comment
@@ -38,10 +38,10 @@
 #
 # Add as many "url+licence+comment" entries as necessary
 # (or remove it altogether if you haven't used any external code)
-code:
-  - comment:
-    url:
-    licence:
+#code:
+#  - comment:
+#    url:
+#    licence:
 
 # Use this to list any assets (artwork, sound, etc) that you used.
 # Provide a comment explaining your use of that asset and the URL
@@ -50,17 +50,20 @@
 # Add as many "url+licence+comment" entries as necessary
 # (or remove it altogether if you haven't used any external assets)
 assets:
-  - comment:
-    url:
-    licence:
+  - comment: Die image for demonstrative GUI design use
+    url: https://svgsilh.com/9e9e9e/image/41187.html
+    licence: CC0 1.0 
+  - comment: Image of two dice for demonstrative GUI design use
+    url: https://svgsilh.com/9e9e9e/image/2027245.html
+    licence: CC0 1.0 
 
 
 # Sign *your* name and uids here. (Remove entries if you have fewer
 # than three members.)
 signatures:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
+  - name: Zane Gates
+    uid: u7334218
+  - name: Yuechen Liu
+    uid: u7227895
+  - name: Ziling Ouyang
+    uid: u7312578
Only in ../master/comp1140-ass2/admin: C-best-u1234567.yml
Only in comp1140-ass2/admin: C-best-u7227895.yml
Only in comp1140-ass2/admin: C-best-u7312578.yml
Only in comp1140-ass2/admin: C-best-u7334218.yml
diff -ru -x .git ../master/comp1140-ass2/admin/C-contribution.yml comp1140-ass2/admin/C-contribution.yml
--- ../master/comp1140-ass2/admin/C-contribution.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/C-contribution.yml	2021-10-23 12:53:33.000000000 +1100
@@ -8,19 +8,19 @@
 # be 100 or 99 (33/33/33 is fine).  (Remove entries if you have fewer than three
 # members).  
 contributions:
-  - uid: 
-    contribution: 
-  - uid:
-    contribution:
-  - uid:
-    contribution:
+  - uid: u7312578
+    contribution: 33
+  - uid: u7227895
+    contribution: 33
+  - uid: u7334218
+    contribution: 33
 
 # Sign *your* name and uids here. (Remove entries if you have fewer
 # than three members)
 signatures:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
\ No newline at end of file
+  - name: Ziling Ouyang
+    uid: u7312578
+  - name: Yuechen Liu
+    uid: u7227895
+  - name: Zane Gates
+    uid: u7334218
diff -ru -x .git ../master/comp1140-ass2/admin/C-originality.yml comp1140-ass2/admin/C-originality.yml
--- ../master/comp1140-ass2/admin/C-originality.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/C-originality.yml	2021-10-23 12:53:33.000000000 +1100
@@ -27,9 +27,9 @@
 #
 # Add as many "name+comment" entries as necessary
 # (or remove it altogether if you haven't collaborated with anyone)
-collaboration:
-  - name:
-    comment: >-
+#collaboration:
+#  - name:
+#    comment: >-
 
 # Use this to list any code that you used that you did not write,
 # aside from code provided by the lecturer.  Provide a comment
@@ -39,9 +39,13 @@
 # Add as many "url+licence+comment" entries as necessary
 # (or remove it altogether if you haven't used any external code)
 code:
-  - comment:
-    url:
-    licence:
+  - comment: UV coordinates for a 3D cube for task 5
+    url: https://stackoverflow.com/a/34663339
+    licence: CC-BY-SA
+  - comment: Used for splitting String into substrings
+    url: https://stackoverflow.com/a/3761521
+    licence: CC-BY-SA 3.0
+
 
 # Use this to list any assets (artwork, sound, etc) that you used.
 # Provide a comment explaining your use of that asset and the URL
@@ -49,18 +53,18 @@
 #
 # Add as many "url+licence+comment" entries as necessary
 # (or remove it altogether if you haven't used any external assets)
-assets:
-  - comment:
-    url:
-    licence:
+#assets:
+#  - comment:
+#    url:
+#    licence:
 
 
 # Sign *your* name and uids here. (Remove entries if you have fewer
 # than three members.)
 signatures:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
+  - name: Ziling Ouyang
+    uid: u7312578
+  - name: Zane Gates
+    uid: u7334218
+  - name: Yuechen Liu
+    uid: u7227895
Only in ../master/comp1140-ass2/admin: D-newcode-u1234567.md
Only in comp1140-ass2/admin: D-newcode-u7227895.md
Only in comp1140-ass2/admin: D-newcode-u7312578.md
Only in comp1140-ass2/admin: D-newcode-u7334218.md
Only in ../master/comp1140-ass2/admin: D-originality-u1234567.yml
Only in comp1140-ass2/admin: D-originality-u7227895.yml
Only in comp1140-ass2/admin: D-originality-u7312578.yml
Only in comp1140-ass2/admin: D-originality-u7334218.yml
diff -ru -x .git ../master/comp1140-ass2/admin/E-contribution.yml comp1140-ass2/admin/E-contribution.yml
--- ../master/comp1140-ass2/admin/E-contribution.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/E-contribution.yml	2021-10-23 12:53:33.000000000 +1100
@@ -8,19 +8,19 @@
 # be 100 or 99 (33/33/33 is fine).  (Remove entries if you have fewer than three
 # members).  
 contributions:
-  - uid: 
-    contribution: 
-  - uid:
-    contribution:
-  - uid:
-    contribution:
+  - uid: u7312578
+    contribution: 33
+  - uid: u7227895
+    contribution: 33
+  - uid: u7334218
+    contribution: 33
 
 # Sign *your* name and uids here. (Remove entries if you have fewer
 # than three members)
 signatures:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
\ No newline at end of file
+  - name: Ziling Ouyang
+    uid: u7312578
+  - name: Yuechen Liu
+    uid: u7227895
+  - name: Zane Gates
+    uid: u7334218
diff -ru -x .git ../master/comp1140-ass2/admin/E-originality.yml comp1140-ass2/admin/E-originality.yml
--- ../master/comp1140-ass2/admin/E-originality.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/E-originality.yml	2021-10-23 12:53:33.000000000 +1100
@@ -27,9 +27,9 @@
 #
 # Add as many "name+comment" entries as necessary
 # (or remove it altogether if you haven't collaborated with anyone)
-collaboration:
-  - name:
-    comment: >-
+#collaboration:
+#  - name:
+#    comment: >-
 
 # Use this to list any code that you used that you did not write,
 # aside from code provided by the lecturer.  Provide a comment
@@ -38,10 +38,14 @@
 #
 # Add as many "url+licence+comment" entries as necessary
 # (or remove it altogether if you haven't used any external code)
-code:
-  - comment:
-    url:
-    licence:
+code: 
+  - comment: Used to deep copy objects
+    url: https://stackoverflow.com/a/7596565
+    licence: CC BY-SA 3.0
+  - comment: Used to sort the array
+    url: https://stackoverflow.com/revisions/369867/5
+    licence: CC BY-SA 3.0
+
 
 # Use this to list any assets (artwork, sound, etc) that you used.
 # Provide a comment explaining your use of that asset and the URL
@@ -49,18 +53,18 @@
 #
 # Add as many "url+licence+comment" entries as necessary
 # (or remove it altogether if you haven't used any external assets)
-assets:
-  - comment:
-    url:
-    licence:
+#assets:
+#  - comment:
+#    url:
+#    licence:
 
 
 # Sign *your* name and uids here. (Remove entries if you have fewer
 # than three members.)
 signatures:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
+  - name: Ziling Ouyang
+    uid: u7312578
+  - name: Yuechen Liu
+    uid: u7227895
+  - name: Zane Gates
+    uid: u7334218
Only in ../master/comp1140-ass2/admin: E-review-u1234567.md
Only in comp1140-ass2/admin: E-review-u7227895.md
Only in comp1140-ass2/admin: E-review-u7312578.md
Only in comp1140-ass2/admin: E-review-u7334218.md
Only in ../master/comp1140-ass2/admin: F-best-u1234567.yml
Only in comp1140-ass2/admin: F-best-u7227895.yml
Only in comp1140-ass2/admin: F-best-u7312578.yml
Only in comp1140-ass2/admin: F-best-u7334218.yml
diff -ru -x .git ../master/comp1140-ass2/admin/F-contribution.yml comp1140-ass2/admin/F-contribution.yml
--- ../master/comp1140-ass2/admin/F-contribution.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/F-contribution.yml	2021-10-23 12:53:33.000000000 +1100
@@ -10,19 +10,19 @@
 # be 100 or 99 (33/33/33 is fine).  (Remove entries if you have fewer than three
 # members).
 contributions:
-  - uid: 
-    contribution: 
-  - uid:
-    contribution:
-  - uid:
-    contribution:
+  - uid: u7312578
+    contribution: 33
+  - uid: u7227895
+    contribution: 33
+  - uid: u7334218
+    contribution: 33
 
 # Sign *your* name and uids here. (Remove entries if you have fewer
 # than three members)
 signatures:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
+  - name: Ziling Ouyang
+    uid: u7312578
+  - name: Yuechen Liu
+    uid: u7227895
+  - name: Zane Gates
+    uid: u7334218
diff -ru -x .git ../master/comp1140-ass2/admin/F-features.md comp1140-ass2/admin/F-features.md
--- ../master/comp1140-ass2/admin/F-features.md	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/F-features.md	2021-10-23 12:53:33.000000000 +1100
@@ -1,15 +1,30 @@
 In addition to the features that are auto-graded, the graphical user interface
 of our project implements the following features:
 
-*(Remove those that are unimplemented)*
+ X A simple placement viewer (Task 5)
 
- - A simple placement viewer (Task 4)
- - A basic playable Pur game
- - A basic playable Pur game only allows valid games to be played (Task 9)
- - Generates simple Pur moves (Task 10)
- - Generates smart Pur moves (Task 12)
- - A basic playable Contra game only allows valid games to be played (Task 9)
- - Generates simple Contra moves (Task 13)
- - Generates smart Contra moves (Task 13)
+ X A basic playable Pur game (Task 10)
 
-additional features...
+ X A basic playable Pur game only allows valid games to be played (Task 10)
+
+ X Generates simple Pur moves (Task 11)
+
+ X Generates smart Pur moves (Task 11)
+
+ X A basic playable Contra game only allows valid games to be played (Task 14)
+
+ X Generates simple Contra moves (Task 14)
+
+ X Generates smart Contra moves (Task 14)
+
+ X Easy (greedy) and difficult (MCTS) AI opponents for Contra and Pur
+
+ X Viewing and playing games occurs in a 3D environment
+
+ X Various 3D models and animations for GUI
+
+ X Ability to "take back" / reverse moves
+
+ X Graphical features for the GUI (e.g., Skins for dice, backgrounds while playing, player avatars, etc)
+
+ X A menu for the GUI
diff -ru -x .git ../master/comp1140-ass2/admin/F-originality.yml comp1140-ass2/admin/F-originality.yml
--- ../master/comp1140-ass2/admin/F-originality.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/F-originality.yml	2021-10-23 12:53:33.000000000 +1100
@@ -27,9 +27,9 @@
 #
 # Add as many "name+comment" entries as necessary
 # (or remove it altogether if you haven't collaborated with anyone)
-collaboration:
-  - name:
-    comment: >-
+#collaboration:
+#  - name:
+#    comment: >-
 
 # Use this to list any code that you used that you did not write,
 # aside from code provided by the lecturer.  Provide a comment
@@ -39,9 +39,24 @@
 # Add as many "url+licence+comment" entries as necessary
 # (or remove it altogether if you haven't used any external code)
 code:
-  - comment:
-    url:
-    licence:
+  - comment: Used to flatten a list of lists
+    url: https://stackoverflow.com/a/25147125
+    licence: CC BY-SA 3.0
+  - comment: Modified to format the toString() method of a tree
+    url: https://stackoverflow.com/a/27265035
+    licence: CC BY-SA 3.0
+  - comment: UV coordinates for a 3D cube for task 5
+    url: https://stackoverflow.com/a/34663339
+    licence: CC-BY-SA
+  - comment: Used for splitting String into substrings
+    url: https://stackoverflow.com/a/3761521
+    licence: CC-BY-SA 3.0
+  - comment: Used to deep copy objects
+    url: https://stackoverflow.com/a/7596565
+    licence: CC BY-SA 3.0
+  - comment: Used to sort the array
+    url: https://stackoverflow.com/revisions/369867/5
+    licence: CC BY-SA 3.0
 
 # Use this to list any assets (artwork, sound, etc) that you used.
 # Provide a comment explaining your use of that asset and the URL
@@ -50,17 +65,44 @@
 # Add as many "url+licence+comment" entries as necessary
 # (or remove it altogether if you haven't used any external assets)
 assets:
-  - comment:
-    url:
-    licence:
+  - comment: Die image for demonstrative GUI design use
+    url: https://svgsilh.com/9e9e9e/image/41187.html
+    licence: CC0 1.0 
+  - comment: Image of two dice for demonstrative GUI design use
+    url: https://svgsilh.com/9e9e9e/image/2027245.html
+    licence: CC0 1.0
+  - comment: Image of spruce wood for board tiles (lightly modified)
+    url: https://us.123rf.com/450wm/dmitr1ch/dmitr1ch1705/dmitr1ch170500017/77459809-old-spruce-wood-planks-texture-on-exterior-wall-of-a-wooden-church.jpg?ver=6
+    licence: CC0 1.0
+  - comment: Image of light spruce wood for board tiles (lightly modified)
+    url: https://cdn.pixabay.com/photo/2016/02/18/01/35/wood-1206421_1280.jpg
+    licence: CC0 1.0
+  - comment: Image of acacia wood for the table
+    url: https://pixnio.com/free-images/2017/11/01/2017-11-01-17-06-25-1200x798.jpg
+    licence: CC0 1.0
+  - comment: Martian and siberian skybox (lightly modified)
+    url: https://opengameart.org/content/skiingpenguins-skybox-pack
+    licence: CC0 1.0
+  - comment: Placid skybox (modified)
+    url: https://www.keithlantz.net/wp-content/uploads/2011/10/skybox_texture.jpg
+    licence: CC0 1.0
+  - comment: Further skyboxes
+    url: https://skyboxes.weebly.com/
+    licence: CC0 1.0
+  - comment: Move partially made sound effect
+    url: https://soundbible.com/893-Button-Click.html
+    licence: CC0 1.0
+  - comment: Move completed sound effect
+    url: https://soundbible.com/1086-Wooden-Thump.html
+    licence: CC0 1.0
 
 
 # Sign *your* name and uids here. (Remove entries if you have fewer
 # than three members.)
 signatures:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
+  - name: Ziling Ouyang
+    uid: u7312578
+  - name: Zane Gates
+    uid: u7334218
+  - name: Yuechen Liu
+    uid: u7227895
diff -ru -x .git ../master/comp1140-ass2/admin/members.yml comp1140-ass2/admin/members.yml
--- ../master/comp1140-ass2/admin/members.yml	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/members.yml	2021-10-23 12:53:33.000000000 +1100
@@ -3,9 +3,12 @@
 #
 
 members:
-  - name:
-    uid:
-  - name:
-    uid:
-  - name:
-    uid:
+  - name: Yuechen Liu
+    uid: u7227895
+    contact: u7227895@anu.edu.au
+  - name: Ziling Ouyang
+    uid: u7312578
+    contact: u7312578@anu.edu.au
+  - name: Zane Gates
+    uid: u7334218
+    contact: u7334218@anu.edu.au
diff -ru -x .git ../master/comp1140-ass2/admin/tasks.md comp1140-ass2/admin/tasks.md
--- ../master/comp1140-ass2/admin/tasks.md	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/admin/tasks.md	2021-10-23 12:53:33.000000000 +1100
@@ -5,19 +5,25 @@
 
 ## Week 4
 
-Everyone: create application skeleton - meeting 14:00 17 Aug
+Everyone: create application skeleton - meeting 14:00 Sunday 22 August
 
 ## Week 5
 
-Zhang San: Task 3 isPiecePlacementWellFormed - 21 Aug
+Zane: construct UML diagram for classes in LaTeX - meeting
 
-Jane Bloggs: Task 4 getNeighbours - 23 Aug
+Yuechen: javadocs + create classes - meeting
 
-Erika Mustermann: Task 6 getViablePiecePlacements - 24 Aug (depends on Task 3)
+Ziling: prettify the UI example - meeting
+
+Everyone: further develop application skeleton - meeting 11:00 Tuesday 24 August
 
 ## Week 6
 
-...
+Zane: Task 5
+
+Yuechen: Tasks 3 and 6
+
+Ziling: Tasks 4 and 7
 
 ## Mid-Semester Break
 
Only in comp1140-ass2: assets
Only in comp1140-ass2: feedback
Only in comp1140-ass2: feedback.md
Only in comp1140-ass2: game.jar
diff -ru -x .git ../master/comp1140-ass2/src/comp1140/ass2/Cublino.java comp1140-ass2/src/comp1140/ass2/Cublino.java
--- ../master/comp1140-ass2/src/comp1140/ass2/Cublino.java	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/src/comp1140/ass2/Cublino.java	2021-10-23 12:53:34.000000000 +1100
@@ -1,5 +1,18 @@
 package comp1140.ass2;
 
+import comp1140.ass2.gamelogic.ContraCublino;
+import comp1140.ass2.gamelogic.Game;
+import comp1140.ass2.gamelogic.PurCublino;
+import comp1140.ass2.state.Boards;
+import comp1140.ass2.state.Die;
+
+import java.util.Random;
+
+import static comp1140.ass2.state.Boards.boardToString;
+
+/**
+ * @author Ziling Ouyang, Yuechen Liu
+ */
 public class Cublino {
 
     /**
@@ -10,23 +23,51 @@
      * [variant][dice]*
      * where [variant] and [dice] are replaced by the corresponding substrings below. Note that [dice]* means zero or
      * more repetitions of the [dice] substring.
-     *
+     * <p>
      * 1. [variant] The variant substring is a single character which is either an upper or lower case 'p' or 'c' - The
      * letter encodes which variant the of the rules the game is using and the case represents the turn of the current
      * player.
-     *
+     * <p>
      * 2. [dice] The dice substring contains three characters. The first character can be either an upper or lower case
      * letter in the range 'a' to 'x'. The letter encodes the orientation of the dice and the case encodes which player
      * the dice belongs two. The next two characters encode the position of the dice in the format [column][row] where
      * the column is one character in the range 'a' to 'g' and the row is one character in the range '1' to '7'.
-     *
+     * <p>
      * See the "Encoding Game State" section in the README for more details.
      *
      * @param state a string representing a game state
      * @return true if the input state is well formed, otherwise false
      */
     public static Boolean isStateWellFormed(String state) {
-        return null; // FIXME Task 3 (P)
+        if (state != null && state.length() % 3 == 1) {
+            if (state.charAt(0) == 'p' || state.charAt(0) == 'c'
+                    || state.charAt(0) == 'P' || state.charAt(0) == 'C') {
+                int l = (state.length() - 1) / 3;
+                if (l != 0) {
+                    int i = 1;
+                    while (i < state.length()) {
+                        String x;
+                        if (i + 3 == state.length()) {
+                            x = state.substring(i);
+                        } else {
+                            x = state.substring(i, i + 3);
+                        }
+
+                        if (x.charAt(0) < 'A' || (x.charAt(0) > 'X' && x.charAt(0) < 'a')
+                                || x.charAt(0) > 'x') {
+                            return false;
+                        } else if (x.charAt(1) > 'g' || x.charAt(1) < 'a') {
+                            return false;
+                        } else if (x.charAt(2) > '7' || x.charAt(2) < '1') {
+                            return false;
+                        }
+                        i += 3;
+                    }
+                }
+                return true;
+            }
+        }
+        return false;
     }
 
     /**
@@ -35,16 +76,16 @@
      * A game state is valid if it satisfies the conditions below. Note that there are some shared conditions and
      * some conditions which are specific to each variant of the game. For this task you are expected to check states
      * from both variants.
-     *
+     * <p>
      * [Both Variants]
      * 1. The game state is well formed.
      * 2. No two dice occupy the same position on the board.
-     *
+     * <p>
      * [Pur]
      * 1. Each player has exactly seven dice.
      * 2. Both players do not have all seven of their dice on the opponent's end of the board (as the game would have
      * already finished before this)
-     *
+     * <p>
      * [Contra]
      * 1. Each player has no more than seven dice.
      * 2. No more than one player has a dice on the opponent's end of the board.
@@ -53,17 +94,35 @@
      * @return true if the input state is valid, otherwise false
      */
     public static Boolean isStateValid(String state) {
-        return null; // FIXME Task 4 (P)
+
+        char purMode = 'p';
+        int noDie = 1;
+
+        if (!isStateWellFormed(state) || (state.toLowerCase().charAt(0) == purMode && state.length() == noDie))
+            return false;
+
+        char gameMode = state.toLowerCase().charAt(0);
+        Boards board = new Boards(state);
+        PurCublino purCublino = new PurCublino();
+        ContraCublino contraCublino = new ContraCublino();
+
+        if (board.containsOverlappingPieces()) return false;
+
+        if (gameMode == purMode) {
+            return purCublino.isGameValid(board);
+        } else {
+            return contraCublino.isGameValid(board);
+        }
     }
 
     /**
      * Task 6:
      * Determine whether a state represents a finished Pur game, and if so who the winner is.
-     *
+     * <p>
      * A game of Cublino Pur is finished once one player has reached the opponent's end of the board with all seven of
      * their dice. Each player then adds the numbers facing upwards on their dice which have reached the opponent's end
      * of the board. The player with the highest total wins.
-     *
+     * <p>
      * You may assume that all states input into this method will be of the Pur variant and that the state will be
      * valid.
      *
@@ -71,7 +130,16 @@
      * @return 1 if player one has won, 2 if player two has won, 3 if the result is a draw, otherwise 0.
      */
     public static int isGameOverPur(String state) {
-        return -1; // FIXME Task 6 (D)
+        if (state.charAt(0) != 'p' && state.charAt(0) != 'P') return 0;
+        else {
+            PurCublino cublino = new PurCublino(true, new Boards(state));
+            return switch (cublino.getWinner()) {
+                case UNFINISHED -> 0;
+                case WHITE_WINS -> 1;
+                case BLACK_WINS -> 2;
+                case TIE -> 3;
+            };
+        }
     }
 
     /**
@@ -81,22 +149,38 @@
      * position on the board. The first position represents the starting position of the dice making the step and the
      * second position represents the ending position of the dice making the step. You may assume that the step strings
      * input into this method are well formed according to the above specification.
-     *
+     * <p>
      * A step is valid if it satisfies the following conditions:
      * 1. It represents either a tilt or a jump of a dice.
      * 2. The ending position of the step is not occupied.
      * 3. The step moves towards the opponent's end of the board or horizontally (along its current row).
      * 3. If it is a jump step, there is a dice in the position which is jumped over.
-     *
+     * <p>
      * You may assume that all states input into this method will be of the Pur variant and that the state will be
      * valid.
      *
      * @param state a Pur game state
-     * @param step a string representing a single step of a move
+     * @param step  a string representing a single step of a move
      * @return true if the step is valid for the given state, otherwise false
      */
     public static Boolean isValidStepPur(String state, String step) {
-        return null; // FIXME Task 7 (D)
+
+        int jumpDistance = 2;
+        PurCublino purCublino = new PurCublino(Character.isUpperCase(state.charAt(0)), new Boards(state));
+        Boards board = purCublino.getBoard();
+        Boards.Positions[] positions = Boards.moveToPositions(step);
+        String start = positions[0].toString();
+        String end = positions[positions.length - 1].toString();
+
+        if (board.getAt(end) != null) return false;
+
+        if (board.isAdjacent(start, end)) {
+            return purCublino.isMoveNotBackwards(start, end);
+        } else if (Boards.getManhattanDistance(start, end) == jumpDistance) {
+            return purCublino.isJumpValid(start, end);
+        } else {
+            return false;
+        }
     }
 
     /**
@@ -106,23 +190,39 @@
      * lists a sequence of positions that a dice will have as it makes a move. Note that [position]* means zero or more
      * repetitions of the [position] substring. You may assume that the move strings input into this method are well
      * formed according to the above specification.
-     *
+     * <p>
      * A move is valid if it satisfies the following conditions:
      * 1. The starting position of the move contains a dice belonging to the player who's turn it is.
      * 2. All steps in the move are valid.
      * 3. The move contains at least one step.
      * 4. Only the first step may be a tipping step.
      * 5. The starting and ending positions of the moved dice are different.
-     *
+     * <p>
      * You may assume that all states input into this method will be of the Pur variant and that the state will be
      * valid.
      *
      * @param state a Pur game state
-     * @param move a string representing a move
+     * @param move  a string representing a move
      * @return true if the move is valid for the given state, otherwise false
      */
     public static Boolean isValidMovePur(String state, String move) {
-        return null; // FIXME Task 8 (P)
+
+        PurCublino pur = new PurCublino(Character.isUpperCase(state.charAt(0)), new Boards(state));
+        Boards board = pur.getBoard();
+        Boards.Positions[] stepPositions = Boards.moveToPositions(move);
+        String diePosition = stepPositions[0].toString();
+        String lastPosition = stepPositions[stepPositions.length - 1].toString();
+        int firstStepIndex = 1;
+
+        if (move.length() == 0 || board.getAt(diePosition) == null || diePosition.equals(lastPosition)) return false;
+        if (pur.getCurrentPlayer().isWhite() == board.getAt(diePosition).isWhite()) {
+            for (int i = firstStepIndex; i < stepPositions.length; i++) {
+                Die initial = board.getAt(stepPositions[i - firstStepIndex].toString());
+                pur.applyStep(initial, stepPositions[i].toString());
+                if (pur.getStepHistory().stream().anyMatch((x) -> x.getType() == Game.MoveType.INVALID)) return false;
+            }
+        }
+        return (pur.getStepHistory().size() != 0);
     }
 
     /**
@@ -130,16 +230,28 @@
      * Given a Pur game state and a move to play, determine the state that results from that move being played.
      * If the move ends the game, the turn should be the player who would have played next had the game not ended. If
      * the move is invalid the game state should remain unchanged.
-     *
+     * <p>
      * You may assume that all states input into this method will be of the Pur variant and that the state will be
      * valid.
      *
      * @param state a Pur game state
-     * @param move a move being played
+     * @param move  a move being played
      * @return the resulting state after the move has been applied
      */
     public static String applyMovePur(String state, String move) {
-        return null; // FIXME Task 9 (P)
+        if (!isValidMovePur(state, move)) return state;
+        Boards board = new Boards(state);
+        char gameIdentifier = state.charAt(0);
+        PurCublino pur = new PurCublino(Character.isUpperCase(gameIdentifier), board);
+        Boards.Positions[] stepPositions = Boards.moveToPositions(move);
+        char purIdentifier = (Character.isLowerCase(gameIdentifier) ? Character.toUpperCase(gameIdentifier) : Character.toLowerCase(gameIdentifier));
+
+        for (int i = 1; i < stepPositions.length; i++) {
+            Die die = board.getAt(stepPositions[i - 1].toString());
+            pur.applyStep(die, stepPositions[i].toString());
+        }
+
+        return purIdentifier + boardToString(board);
     }
 
     /**
@@ -148,7 +260,7 @@
      * This task imposes an additional constraint that moves returned must not revisit positions previously occupied as
      * part of the move (ie. a move may not contain a jumping move followed by another jumping move back to the previous
      * position).
-     *
+     * <p>
      * You may assume that all states input into this method will be of the Pur variant and that the state will be
      * valid.
      *
@@ -156,16 +268,20 @@
      * @return a valid move for the current game state.
      */
     public static String generateMovePur(String state) {
-        return null; // FIXME Task 11 (D)
-        // FIXME Task 13 (HD): Implement a "smart" generateMove()
+        Boards board = new Boards(state);
+        char turnInformation = state.charAt(0);
+        PurCublino pur = new PurCublino(Character.isUpperCase(turnInformation), board);
+        Random rand = new Random();
+        int randomMove = rand.nextInt(pur.generateLegalMoves().length);
+        return pur.generateLegalMoves()[randomMove].getEncodedMove();
     }
 
     /**
      * Task 14a:
      * Determine whether a state represents a finished Contra game, and if so who the winner is.
-     *
+     * <p>
      * A player wins a game of Cublino Contra by reaching the opponent's end of the board with one of their dice.
-     *
+     * <p>
      * You may assume that all states input into this method will be of the Contra variant and that the state will be
      * valid.
      *
@@ -173,7 +289,14 @@
      * @return 1 if player one has won, 2 if player two has won, otherwise 0.
      */
     public static int isGameOverContra(String state) {
-        return -1; // FIXME Task 14a (HD)
+        Boards board = new Boards(state);
+        ContraCublino contra = new ContraCublino(Character.isUpperCase(state.charAt(0)), board);
+        if (!contra.isGameOver()) return 0;
+        return switch (contra.getWinner()) {
+            case WHITE_WINS -> 1;
+            case BLACK_WINS -> 2;
+            default -> 0;
+        };
     }
 
     /**
@@ -182,22 +305,31 @@
      * If the move ends the game, the turn should be the player who would have played next had the game not ended. If
      * the move is invalid the game state should remain unchanged. See the README for what constitutes a valid Contra
      * move and the rules for updating the game state.
-     *
+     * <p>
      * You may assume that all states input into this method will be of the Contra variant and that the state will be
      * valid.
      *
      * @param state a Contra game state
-     * @param move a move being played
+     * @param move  a move being played
      * @return the resulting state after the move has been applied
      */
     public static String applyMoveContra(String state, String move) {
-        return null; // FIXME Task 14b (HD)
+        Boards board = new Boards(state);
+        ContraCublino contra = new ContraCublino(Character.isUpperCase(state.charAt(0)), board);
+        Boards.Positions[] stepPositions = Boards.moveToPositions(move);
+
+        for (int i = 1; i < stepPositions.length; i++) {
+            Die die = board.getAt(stepPositions[i - 1].toString());
+            contra.applyStep(die, stepPositions[i].toString());
+        }
+        return ((Character.isLowerCase(state.charAt(0))) ? String.valueOf(Character.toUpperCase(state.charAt(0))) : Character.toLowerCase(state.charAt(0)))
+                + boardToString(board);
     }
 
     /**
      * Task 14c:
      * Given a valid Contra game state, return a valid move.
-     *
+     * <p>
      * You may assume that all states input into this method will be of the Contra variant and that the state will be
      * valid.
      *
@@ -205,7 +337,11 @@
      * @return a move for the current game state.
      */
     public static String generateMoveContra(String state) {
-        return null; // FIXME Task 14c (HD)
+        Boards board = new Boards(state);
+        char turnInformation = state.charAt(0);
+        ContraCublino contra = new ContraCublino(Character.isUpperCase(turnInformation), board);
+        Random rand = new Random();
+        int randomMove = rand.nextInt(contra.generateLegalMoves().length);
+        return contra.generateLegalMoves()[randomMove].getEncodedMove();
     }
-
 }
Only in comp1140-ass2/src/comp1140/ass2: controller
Only in comp1140-ass2/src/comp1140/ass2: gamelogic
diff -ru -x .git ../master/comp1140-ass2/src/comp1140/ass2/gui/Board.java comp1140-ass2/src/comp1140/ass2/gui/Board.java
--- ../master/comp1140-ass2/src/comp1140/ass2/gui/Board.java	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/src/comp1140/ass2/gui/Board.java	2021-10-23 12:53:34.000000000 +1100
@@ -1,25 +1,204 @@
 package comp1140.ass2.gui;
 
+import comp1140.ass2.controller.Controller;
+import comp1140.ass2.gui.guipieces.GuiBoard;
+import comp1140.ass2.gui.guipieces.GuiSkybox;
+import comp1140.ass2.gui.guipieces.Menu;
+import javafx.animation.AnimationTimer;
 import javafx.application.Application;
+import javafx.geometry.Pos;
 import javafx.scene.Group;
 import javafx.scene.Scene;
+import javafx.scene.control.Button;
+import javafx.scene.image.ImageView;
+import javafx.scene.input.KeyCode;
+import javafx.scene.paint.Color;
+import javafx.scene.shape.Rectangle;
+import javafx.scene.text.TextAlignment;
 import javafx.stage.Stage;
 
+/**
+ * A comprehensive class for playing games of Cublino.
+ *
+ * @author Zane Gates
+ */
 public class Board extends Application {
 
-    private static final int BOARD_WIDTH = 933;
-    private static final int BOARD_HEIGHT = 700;
+    /* board layout */
+    private static final int VIEWER_WIDTH = 933;
+    private static final int VIEWER_HEIGHT = 700;
 
-    private final Group root = new Group();
+    GuiBoard game;
+    Menu menu;
 
-    //  FIXME Task 9 (CR): Implement a basic playable Cublino game in JavaFX that only allows valid moves to be played
+    Group root;
 
+    ImageView turnDisplayer;
+    Button takeBack;
+    Button toMenu;
+    Button restart;
+
+    boolean inGame = false;
+    boolean pauseMenuVisible = false;
+
+    boolean startingFadeNow = false;
+    double initialTime = 0;
+    FadeAction fadingTo = FadeAction.NONE;
+
+    enum FadeAction {NONE, MENU, GAME};
+
+    Boolean isPur;
+    GuiSkybox.Locale locale;
+    Controller[] controllers;
+
+    /**
+     * Starts the application
+     * @param primaryStage the stage from which the application will be built
+     */
     @Override
     public void start(Stage primaryStage) {
         primaryStage.setTitle("Cublino");
-        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
+
+        root = new Group();
+
+        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
+
+        menu = new Menu(this);
 
         primaryStage.setScene(scene);
         primaryStage.show();
+
+        // Construct the label that shows whose turn it is currently
+        turnDisplayer = new ImageView();
+        turnDisplayer.setFitWidth(64);
+        turnDisplayer.setFitHeight(64);
+        turnDisplayer.setSmooth(true);
+        turnDisplayer.setLayoutX(20);
+        turnDisplayer.setLayoutY(20);
+
+        // Construct the take-back button
+        takeBack = new Button("Takeback move");
+        takeBack.setOnMouseClicked(e -> {game.takeBack();});
+        takeBack.setLayoutX(VIEWER_WIDTH*0.5-100);
+        takeBack.setLayoutY(VIEWER_HEIGHT*0.5-50);
+        takeBack.setPrefWidth(200);
+        takeBack.setTextAlignment(TextAlignment.CENTER);
+
+        // Construct a button to restart the game without returning to the menu
+        restart = new Button("Restart game");
+        restart.setOnMouseClicked(e -> {showMenu(); menu.startGame();});
+        restart.setLayoutX(VIEWER_WIDTH*0.5-100);
+        restart.setLayoutY(VIEWER_HEIGHT*0.5);
+        restart.setPrefWidth(200);
+        restart.setTextAlignment(TextAlignment.CENTER);
+
+        // Construct a button to return to the menu
+        toMenu = new Button("Return to menu");
+        toMenu.setOnMouseClicked(e -> {showMenu();});
+        toMenu.setLayoutX(VIEWER_WIDTH*0.5-100);
+        toMenu.setLayoutY(VIEWER_HEIGHT*0.5+50);
+        toMenu.setPrefWidth(200);
+        toMenu.setTextAlignment(TextAlignment.CENTER);
+
+        // Construct a translucent gray rectangle as a background for the pause menu
+        Rectangle pauseBackground = new Rectangle();
+        pauseBackground.setLayoutX(0);
+        pauseBackground.setLayoutY(0);
+        pauseBackground.setWidth(VIEWER_WIDTH);
+        pauseBackground.setHeight(VIEWER_HEIGHT);
+        pauseBackground.setFill(Color.BLACK);
+        pauseBackground.setOpacity(0.5);
+
+        // Construct a translucent gray rectangle as a background for the pause menu
+        Rectangle fadeBackground = new Rectangle();
+        fadeBackground.setLayoutX(0);
+        fadeBackground.setLayoutY(0);
+        fadeBackground.setWidth(VIEWER_WIDTH);
+        fadeBackground.setHeight(VIEWER_HEIGHT);
+        fadeBackground.setFill(Color.BLACK);
+        fadeBackground.setOpacity(0);
+
+        root.getChildren().addAll(menu, fadeBackground);
+
+        new AnimationTimer() {
+            @Override
+            public void handle(long l) {
+                // If a fade is queued but has not started, start it
+                if (startingFadeNow && initialTime == 0) {
+                    initialTime = l;
+                    startingFadeNow = false;
+                }
+
+                // Calculate and apply the target opacity using an inverted absolute curve
+                double progress = (l-initialTime)/2e9;
+                double opacity = 2*(0.5-Math.abs(progress-0.5));
+                fadeBackground.setOpacity(opacity);
+
+                if (opacity <= 0 && root.getChildren().contains(fadeBackground)) {
+                    root.getChildren().remove(fadeBackground);
+                } else if (opacity > 0 && !root.getChildren().contains(fadeBackground)) {
+                    root.getChildren().add(fadeBackground);
+                }
+
+                // If a fade has finished, reset so you can run another fade
+                if (progress >= 2) initialTime = 0;
+
+                // Once the fade is halfway complete (fully black), change scenes as queued
+                if (progress > 0.5) {
+                    switch (fadingTo) {
+                        case NONE: return;
+                        case GAME: {
+                            inGame = true;
+                            pauseMenuVisible = false;
+                            root.getChildren().clear();
+                            try {
+                                game = new GuiBoard((isPur ? "P" : "C") + "Wa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7", locale, controllers, isPur,true, turnDisplayer);
+                            } catch (Exception e) { }
+                            root.getChildren().addAll(game, turnDisplayer, fadeBackground);
+                            fadingTo = FadeAction.NONE;
+                            return;
+                        }
+                        case MENU: {
+                            inGame = false;
+                            root.getChildren().clear();
+                            root.getChildren().addAll(menu, fadeBackground);
+                            fadingTo = FadeAction.NONE;
+                        }
+                    }
+                }
+            }
+        }.start();
+
+        // When ESCAPE is pressed while in a game, toggle the visibility of the pause menu
+        scene.setOnKeyPressed(e -> {
+            if (inGame && e.getCode() == KeyCode.ESCAPE) {
+                pauseMenuVisible = !pauseMenuVisible;
+                if (pauseMenuVisible) root.getChildren().addAll(pauseBackground, takeBack, toMenu, restart);
+                else root.getChildren().removeAll(pauseBackground, takeBack, toMenu, restart);
+            }
+        });
+    }
+
+    /**
+     * Shows the menu, and hides anything else currently visible, after a fade
+     */
+    public void showMenu() {
+        startingFadeNow = true;
+        fadingTo = FadeAction.MENU;
+    }
+
+    /**
+     * Starts a game using settings read from the menu, after a fade
+     * @param isPur whether the game-mode is Pur or Contra
+     * @param locale the skybox for this game
+     * @param controllers the controllers for each players
+     * @throws Exception if an invalid combination of parameters is given
+     */
+    public void startGame(boolean isPur, GuiSkybox.Locale locale, Controller[] controllers) throws Exception {
+        this.isPur = isPur;
+        this.locale = locale;
+        this.controllers = controllers;
+        startingFadeNow = true;
+        fadingTo = FadeAction.GAME;
     }
 }
diff -ru -x .git ../master/comp1140-ass2/src/comp1140/ass2/gui/Viewer.java comp1140-ass2/src/comp1140/ass2/gui/Viewer.java
--- ../master/comp1140-ass2/src/comp1140/ass2/gui/Viewer.java	2021-10-23 12:43:45.000000000 +1100
+++ comp1140-ass2/src/comp1140/ass2/gui/Viewer.java	2021-10-23 12:53:34.000000000 +1100
@@ -1,5 +1,10 @@
 package comp1140.ass2.gui;
 
+
+import comp1140.ass2.controller.Controller;
+import comp1140.ass2.gui.guipieces.GuiBoard;
+import comp1140.ass2.gui.guipieces.GuiDie;
+import comp1140.ass2.gui.guipieces.GuiSkybox;
 import javafx.application.Application;
 import javafx.scene.Group;
 import javafx.scene.Scene;
@@ -9,13 +14,14 @@
 import javafx.scene.layout.HBox;
 import javafx.stage.Stage;
 
-
 /**
  * A very simple viewer for piece placements in the Cublino game.
  * <p>
  * NOTE: This class is separate from your main game class.  This
  * class does not play a game, it just illustrates various piece
  * placements.
+ *
+ * @author Zane Gates
  */
 public class Viewer extends Application {
 
@@ -23,19 +29,24 @@
     private static final int VIEWER_WIDTH = 933;
     private static final int VIEWER_HEIGHT = 700;
 
-    private static final String URI_BASE = "assets/";
-
     private final Group root = new Group();
     private final Group controls = new Group();
     private TextField textField;
 
+    private GuiBoard boardSubscene;
+
+    private final Controller p1 = new Controller(Controller.ControllerType.HUMAN, "Player 1", GuiDie.Skin.PLAIN_WHITE);
+    private final Controller p2 = new Controller(Controller.ControllerType.HUMAN, "Player 2", GuiDie.Skin.PLAIN_BLACK);
+
     /**
      * Draw a placement in the window, removing any previously drawn one
      *
      * @param placement A valid placement string
      */
-    void makePlacement(String placement) {
-        // FIXME Task 4 (CR): implement the simple game state viewer
+    void makePlacement(String placement) throws Exception {
+        root.getChildren().remove(boardSubscene);
+        boardSubscene = new GuiBoard(placement, GuiSkybox.Locale.NONE, new Controller[] {p1, p2}, true, false, null);
+        root.getChildren().add(boardSubscene);
     }
 
     /**
@@ -47,8 +58,12 @@
         textField.setPrefWidth(300);
         Button refresh = new Button("Refresh");
         refresh.setOnAction(actionEvent -> {
+            try {
                 makePlacement(textField.getText());
-                textField.clear();
+            } catch (Exception e) {
+                e.printStackTrace();
+            }
+            textField.clear();
         });
         HBox hb = new HBox();
         hb.getChildren().addAll(label1, textField, refresh);
@@ -56,19 +71,26 @@
         hb.setLayoutX(230);
         hb.setLayoutY(VIEWER_HEIGHT - 50);
         controls.getChildren().add(hb);
+
+        Label instructions = new Label("Drag the mouse horizontally to rotate view");
+        instructions.setLayoutX(10);
+        instructions.setLayoutY(10);
+        controls.getChildren().add(instructions);
     }
 
     @Override
     public void start(Stage primaryStage) throws Exception {
-        primaryStage.setTitle("Cublino Viewer");
-        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
 
-        root.getChildren().add(controls);
+        // Use a 3D sub scene for rendering the board alongside (behind) the controls
+
+        boardSubscene = new GuiBoard("", GuiSkybox.Locale.NONE, new Controller[] {p1, p2}, true, false, null);
 
+        primaryStage.setTitle("Cublino Viewer");
+        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
         makeControls();
+        root.getChildren().addAll(boardSubscene, controls);
 
         primaryStage.setScene(scene);
         primaryStage.show();
     }
-
 }
Only in comp1140-ass2/src/comp1140/ass2/gui: guipieces
Only in comp1140-ass2/src/comp1140/ass2: helperclasses
Only in comp1140-ass2/src/comp1140/ass2: state
Only in comp1140-ass2/src: gittest
Only in comp1140-ass2/tests/comp1140/ass2: DieTests.java
Only in comp1140-ass2/tests/comp1140/ass2: applyStepTest.java
Only in comp1140-ass2/tests/comp1140/ass2: dieMeshTests.java
Only in comp1140-ass2/tests/comp1140/ass2: dieRotationTests.java
Only in comp1140-ass2/tests/comp1140/ass2: generateMovesTest.java
```
