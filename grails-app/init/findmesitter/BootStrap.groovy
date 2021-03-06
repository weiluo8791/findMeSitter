package findmesitter

import daycare.provider.*
import user.review.*
import findMeSitter.user.*


class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                setupUsersAndRoles()
                setupData()
            }
            test {
                setupUsersAndRoles()
                setupData()
            }
            production {
                //do nothing
            }
        }
    }
    def destroy = {
    }
    def saveObject(object) {
        if (!object.save(flush:true)) {
            object.errors.allErrors.each { println it }
        }
    }

    User user1
    User user2
    User user3
    def setupUsersAndRoles(){
        User admin = new User (username: 'admin',password: 'supersecret',firstName: 'Administrator',lastName: 'None')
        saveObject(admin)

        user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas',city: 'Boston',state: 'MA',gender: 'M')
        saveObject(user1)
        user2= new User (username: 'ljames',password: 'ljames',firstName: 'Lebron',lastName: 'James',city: 'Cleveland',state: 'OH',gender: 'F',nickName: 'Queen James')
        saveObject(user2)
        user3= new User (username: 'jwall',password: 'jwall',firstName: 'John',lastName: 'Wall',city: 'Washington D.C.',state: 'DC',gender: 'M')
        saveObject(user3)

        Role adminRole = new Role(authority: Role.ROLE_ADMIN)
        saveObject(adminRole)
        Role userRole = new Role(authority: Role.ROLE_USER)
        saveObject(userRole)

        UserRole.create(admin,adminRole)
        UserRole.create(admin,userRole)
        UserRole.create(user1,userRole)
        UserRole.create(user2,userRole)
        UserRole.create(user3,userRole)

    }

    def setupData(){
        //load day care center
        DayCareCenter dc1 = new DayCareCenter(name: 'Luo Family Day Care',address: '123 Main St',city: 'Malden',state: 'MA',zip: '02148',
                email: 'iqboss@mymail.com',phoneNumber: '123-456-7890',otherDetail:'None',centerCapcity: '8',dailyRate: 55.00)
        saveObject(dc1)
        DayCareCenter dc2 = new DayCareCenter(name: 'Mei Family Day Care',address: '321 High St',city: 'Newton',state: 'MA',zip: '02465',
                email: 'kidfirst@mymail.com',phoneNumber: '123-456-7890',otherDetail:'Including pre-school',centerCapcity: '20',dailyRate: 75.00)
        saveObject(dc2)
        DayCareCenter dc3 = new DayCareCenter(name: 'No Fun Family Day Care',address: '000 Boring St',city: 'Boston',state: 'MA',zip: '02111',
                email: 'nofun@mymail.com',phoneNumber: '123-456-7890',otherDetail:'Please do not have any fun',centerCapcity: '999',dailyRate: 99.98)
        saveObject(dc3)

        //load Day Center component
        Calendar ca1 = new Calendar(calendarYear: 2016,hours: 'M-F 7-6',center: dc1)
        saveObject(ca1)
        Calendar ca2 = new Calendar(calendarYear: 2017,hours: 'M-F 7-7',center: dc1)
        saveObject(ca2)
        Calendar ca3 = new Calendar(calendarYear: 2017,hours: 'M-F 9-5',center: dc2)
        saveObject(ca3)
        Feature fe1 = new Feature(featureName: 'Licensed',featureDescription: 'We are Mass Licensed',featureType: 'external',center: dc1)
        saveObject(fe1)
        Feature fe2 = new Feature(featureName: 'Licensed',featureDescription: 'We are Mass Licensed',featureType: 'external',center: dc2)
        saveObject(fe2)
        Feature fe3= new Feature(featureName: 'Licensed',featureDescription: 'We are Mass Licensed',featureType: 'external',center: dc3)
        saveObject(fe3)
        Picture pi1 = new Picture(pictureName: 'front door',pictureDescription: 'This is our main entry',imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/b/b0/White_House_DC.JPG',center: dc1)
        saveObject(pi1)

        //load reviewer
        Reviewer re1 = new Reviewer(dateOfFirstReview: new Date() - 10 , dateOfLatestReview: new Date(),userDetail: user1)
        saveObject(re1)
        Reviewer re2 = new Reviewer(dateOfFirstReview: new Date() - 15 , dateOfLatestReview: new Date() - 3,userDetail: user2)
        saveObject(re2)
        Reviewer re3 = new Reviewer(dateOfFirstReview: new Date() - 20 , dateOfLatestReview: new Date() - 2,userDetail: user3)
        saveObject(re3)

        //load review
        Review rv1 = new Review(dateOfReview: new Date() - 4 ,reviewTitle: 'I love this day care',reviewDetail: 'This is the best family day care in Malden',
            otherDetail: 'Very clean and professional',stars: 5,recommended: true,reviewer: re1,dayCareCenter: dc1)
        saveObject(rv1)
        Review rv2 = new Review(dateOfReview: new Date() - 3 ,reviewTitle: 'I hate this day care',reviewDetail: 'This is the worst family day care in Malden',
                otherDetail: 'Very dirty and unprofessional',stars: 0,recommended: false,reviewer: re2,dayCareCenter: dc1)
        saveObject(rv2)
        Review rv3 = new Review(dateOfReview: new Date() - 2,reviewTitle: 'I don\'t care',reviewDetail: 'This is ok',
                otherDetail: 'meh',stars: 2,recommended: false,reviewer: re3,dayCareCenter: dc1)
        saveObject(rv3)
        Review rv4 = new Review(dateOfReview: new Date() - 1,reviewTitle: 'I don\'t give a ****',reviewDetail: 'They lost my kid !!',
                otherDetail: 'Police found my kid',stars: 0,recommended: false,reviewer: re1,dayCareCenter: dc3)
        saveObject(rv4)
        Review rv5 = new Review(dateOfReview: new Date() - 5,reviewTitle: 'Highly recommended',reviewDetail: 'My daughter and son loved this place. All the teacher so nice and at a very reason rate',
                otherDetail: 'I wish they can  take my kids at night too ! ',stars: 5,recommended: true,reviewer: re2,dayCareCenter: dc2)
        saveObject(rv5)

        //load review comment
        ReviewComment rc1 = new ReviewComment(comment: 'word',commentDate: new Date() - 2,published: true,review: rv1 )
        saveObject(rc1)
        ReviewComment rc2 = new ReviewComment(comment: 'true',commentDate: new Date() - 3,published: true,review: rv1 )
        saveObject(rc2)
        ReviewComment rc3 = new ReviewComment(comment: 'right',commentDate: new Date() - 4,published: true,review: rv2 )
        saveObject(rc3)
        ReviewComment rc4 = new ReviewComment(comment: 'wrong',commentDate: new Date() - 5,published: true,review: rv2 )
        saveObject(rc4)
        ReviewComment rc5 = new ReviewComment(comment: 'liar',commentDate: new Date() - 6,published: true,review: rv2 )
        saveObject(rc5)
        ReviewComment rc6 = new ReviewComment(comment: 'Thumbs up',commentDate: new Date() - 7,published: true,review: rv3 )
        saveObject(rc6)

        dc1.addToReviews(rv1).addToReviews(rv2).addToReviews(rv3)
        saveObject(dc1)
        rv1.addToComments(rc1).addToComments(rc2)
        saveObject(rv1)
        rv2.addToComments(rc3).addToComments(rc4).addToComments(rc5)
        saveObject(rv2)
        rv3.addToComments(rc1).addToComments(rc6)
        saveObject(rv3)

    }
}
