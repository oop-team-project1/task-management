package taskmanagement;

public @interface IgnoreCoverage {
    /*
     Set up:
     File -> Settings -> Exclude Annotations -> Add current annotation.

     Purpose: Get more informative coverage, by ignoring methods you do not wish to test.

     In current project used to:
        - ignore getters, unless they return copies of objects.
        - ignore private methods, the functionality of which is tested in public methods.
     */
}
