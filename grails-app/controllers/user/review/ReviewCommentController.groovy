package user.review

import findMeSitter.user.Role
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured([Role.ROLE_USER,Role.ROLE_ADMIN])
class ReviewCommentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ReviewComment.list(params), model:[reviewCommentCount: ReviewComment.count()]
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def show(ReviewComment reviewComment) {
        respond reviewComment
    }

    def create() {
        respond new ReviewComment(params)
    }

    @Transactional
    def save(ReviewComment reviewComment) {
        if (reviewComment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reviewComment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reviewComment.errors, view:'create'
            return
        }

        reviewComment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reviewComment.label', default: 'ReviewComment'), reviewComment.id])
                redirect reviewComment
            }
            '*' { respond reviewComment, [status: CREATED] }
        }
    }

    def edit(ReviewComment reviewComment) {
        respond reviewComment
    }

    @Transactional
    def update(ReviewComment reviewComment) {
        if (reviewComment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reviewComment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reviewComment.errors, view:'edit'
            return
        }

        reviewComment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reviewComment.label', default: 'ReviewComment'), reviewComment.id])
                redirect reviewComment
            }
            '*'{ respond reviewComment, [status: OK] }
        }
    }

    @Transactional
    def delete(ReviewComment reviewComment) {

        if (reviewComment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        reviewComment.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reviewComment.label', default: 'ReviewComment'), reviewComment.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reviewComment.label', default: 'ReviewComment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
