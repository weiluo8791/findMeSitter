package user.review

import findMeSitter.user.Role
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import user.review.*


@Transactional(readOnly = true)
@Secured([Role.ROLE_USER,Role.ROLE_ADMIN])
class ReviewController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def elasticSearchService

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def search() {
        def result = elasticSearchService.search("${params.query}")
        [query:params.query, total:result.total, searchResults:result.searchResults]
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Review.list(params), model:[reviewCount: Review.count()]
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def show(Review review) {
        respond review
    }

    def create() {
        respond new Review(params)
    }

    @Transactional
    def save(Review review) {
        if (review == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (review.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond review.errors, view:'create'
            return
        }

        review.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'review.label', default: 'Review'), review.id])
                redirect review
            }
            '*' { respond review, [status: CREATED] }
        }
    }

    def edit(Review review) {
        respond review
    }

    @Transactional
    def update(Review review) {
        if (review == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (review.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond review.errors, view:'edit'
            return
        }

        review.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'review.label', default: 'Review'), review.id])
                redirect review
            }
            '*'{ respond review, [status: OK] }
        }
    }

    @Transactional
    def delete(Review review) {

        if (review == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        review.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'review.label', default: 'Review'), review.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'review.label', default: 'Review'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
