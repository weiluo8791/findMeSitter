package findmesitter

import findMeSitter.user.Role
import findMeSitter.user.User
import findMeSitter.user.UserRole

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

    def setupUsersAndRoles(){
        User admin = new User (username: 'admin',password: 'supersecret',firstName: 'Administrator',lastName: 'None')
        saveObject(admin)

        User user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas' )
        saveObject(user1)
        User user2= new User (username: 'ljames',password: 'ljames',firstName: 'Lebron',lastName: 'James')
        saveObject(user2)
        User user3= new User (username: 'jwall',password: 'jwall',firstName: 'John',lastName: 'Wall')
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

    }
}
