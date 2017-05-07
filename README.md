# findMeSitter
Find me a Day Care App

I would like to do a Day Care matching App

As a parents often comes a time we need to find a suitable day care for our kids. Finding a reliable and dependable good day care is a very difficult task and often parents rely on reference from other people they know to pick their day care.

I would like to write an application that not only take detail from day care provider but also allow other parent to write review or reference for the particular day care. This also should include a search front end that would allow parents search criteria and match day care with recommendation.

I would imagine the application will contains 2 different data set one from Day Care provider to enter their detail (fee, address, etc) and the other for parents review. Then a search front for all user to search both data set.


This project contains the following
* Spring Security Plugin  integrated into the custom plugin 
* Custom plugin integrated into the Main Project (userdetail plugin)
* User, Role, UserRole defined for all Controller and view for log in security
* Implemented Controller and view for all domain classes
* Use UrlMappings for custom mapping to view and controller
* Relation defined in all Domain classes including some transients properties
* Use Bootstrap to initial data loading
* Created custom Service (CalculateRatingAverageService)
* Created custom Tablib (AverageRatingTabLib)
* Use custom Template in View
* Use JQuery DataTable , use custom jquery code to render star image for rating
* User JQuery ajax for POST and GET (dayCareTable.js and reviewerTable.js)
* User GSON for Json response from ajax
* Use Elasticsearch plugin to index all searchable field across multiple domains
* Able to search multiple field cross domain using the elasticSearchService
* Render search result and create link to access multiple Controller 
* Created Unit and Integration test and all test passed with about 
    * 75% line coverage on Domain
     * 84% line coverage on Controller


    