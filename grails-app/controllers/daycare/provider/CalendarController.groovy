package daycare.provider

import findMeSitter.user.Role
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured([Role.ROLE_USER,Role.ROLE_ADMIN])
class CalendarController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Calendar.list(params), model:[calendarCount: Calendar.count()]
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def show(Calendar calendar) {
        respond calendar
    }

    def create() {
        respond new Calendar(params)
    }

    @Transactional
    def save(Calendar calendar) {
        if (calendar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (calendar.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond calendar.errors, view:'create'
            return
        }

        calendar.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'calendar.label', default: 'Calendar'), calendar.id])
                redirect calendar
            }
            '*' { respond calendar, [status: CREATED] }
        }
    }

    def edit(Calendar calendar) {
        respond calendar
    }

    @Transactional
    def update(Calendar calendar) {
        if (calendar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (calendar.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond calendar.errors, view:'edit'
            return
        }

        calendar.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'calendar.label', default: 'Calendar'), calendar.id])
                redirect calendar
            }
            '*'{ respond calendar, [status: OK] }
        }
    }

    @Transactional
    def delete(Calendar calendar) {

        if (calendar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        calendar.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'calendar.label', default: 'Calendar'), calendar.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'calendar.label', default: 'Calendar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
