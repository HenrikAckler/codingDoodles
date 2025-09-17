# Senior Project Planner
A program designed to explore the possible combinations of people and projects for a senior project. While this could likely be solved optimally with some flavor of Discrete Math, I'm just interested in messing around with the problem. So this code will be primarily brute force with some minor optimizations.

## Requirements
There are a set of rules defining the problem:
 - A set number of students, 18 for this example, to be called `s`.
 - A set number of projects, 6 for this example, to be called `p`.
   - `p` must be an even factor of `s`, so that the following can be enforced.
   - all teams must be of size `s/p`.
 - Every student may pick `v` vetos, or people they do not want to work with. For this example, `v=2`.
 - Every student may pick `r` projects, ranked by preference. For this example, they may pick 3. 

## Initial Plan
The first attempt of this project will use a set of `Student` objects, with the following requirements:
 - Each student has an array of size `r` containing their project rankings, where the index of the element is their ranking.
 - Each student has an array of size `v` containing their vetos in the form of object pointers to other students.
 - Each student must have a name that is used in an overridden compareTo method.
 - Each student must have a compatibility ranker. This will return -1 if on the veto list, otherwise tally points based on matching project preference, with more weight to higher rated projects.

 We will then have `Team` objects, containing `s/p` `Students` and capable of returning a compatibility of all members with eachother. 