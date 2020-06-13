# uml-analysis
CPSC410 project2 UML Analysis



## Debates:
- Representation of Parameters
    - (Current) represent as String : "String filename, List<String> literals, Int num"
    - represent as List<Pair<Type,Name>>  [{"Type":"String", "Name":"filename" }, {"Type":"Int", "Name":"num" }]
    
- Dependency Types
    - (Current) [Association, Inheritance(extends), Realization(implements), Import] 
    - Other Plan: ... add dependency
 
 - Full Class Diagram VS Single UML + dependents
 - Whether we support Import Dependency
 
 
 
#####Note:
 -  We no longer use Enums, all use String instead, see MyClass
 -  Constraints: Sine the parser does not ignore comments in files, the parser will fail to parse any projects with more than one line of comments.
    No comments of more than 1 line are allowed in input file.
  