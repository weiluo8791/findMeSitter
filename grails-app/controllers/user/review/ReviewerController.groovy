package user.review

import findMeSitter.user.Role
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured([Role.ROLE_USER,Role.ROLE_ADMIN])
class ReviewerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Reviewer.list(params), model:[reviewerCount: Reviewer.count()]
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def show(Reviewer reviewer) {
        respond reviewer
    }

    def create() {
        respond new Reviewer(params)
    }

    @Transactional
    def save(Reviewer reviewer) {
        if (reviewer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reviewer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reviewer.errors, view:'create'
            return
        }

        reviewer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reviewer.label', default: 'Reviewer'), reviewer.id])
                redirect reviewer
            }
            '*' { respond reviewer, [status: CREATED] }
        }
    }

    def edit(Reviewer reviewer) {
        respond reviewer
    }

    @Transactional
    def update(Reviewer reviewer) {
        if (reviewer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reviewer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reviewer.errors, view:'edit'
            return
        }

        reviewer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reviewer.label', default: 'Reviewer'), reviewer.id])
                redirect reviewer
            }
            '*'{ respond reviewer, [status: OK] }
        }
    }

    @Transactional
    def delete(Reviewer reviewer) {

        if (reviewer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        reviewer.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reviewer.label', default: 'Reviewer'), reviewer.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reviewer.label', default: 'Reviewer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
