package daycare.provider

import findMeSitter.user.Role
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured([Role.ROLE_USER,Role.ROLE_ADMIN])
class PictureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Picture.list(params), model:[pictureCount: Picture.count()]
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def show(Picture picture) {
        respond picture
    }

    def create() {
        respond new Picture(params)
    }

    @Transactional
    def save(Picture picture) {
        if (picture == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (picture.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond picture.errors, view:'create'
            return
        }

        picture.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'picture.label', default: 'Picture'), picture.id])
                redirect picture
            }
            '*' { respond picture, [status: CREATED] }
        }
    }

    def edit(Picture picture) {
        respond picture
    }

    @Transactional
    def update(Picture picture) {
        if (picture == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (picture.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond picture.errors, view:'edit'
            return
        }

        picture.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'picture.label', default: 'Picture'), picture.id])
                redirect picture
            }
            '*'{ respond picture, [status: OK] }
        }
    }

    @Transactional
    def delete(Picture picture) {

        if (picture == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        picture.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'picture.label', default: 'Picture'), picture.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
