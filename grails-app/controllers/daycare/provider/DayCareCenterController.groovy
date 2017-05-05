package daycare.provider

import findMeSitter.user.Role
import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

import java.text.NumberFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured([Role.ROLE_USER,Role.ROLE_ADMIN])
class DayCareCenterController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DayCareCenter.list(params), model:[dayCareCenterCount: DayCareCenter.count()]
        //respond DayCareCenter.list()
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def show(DayCareCenter dayCareCenter) {
        respond dayCareCenter
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS])
    def _show(DayCareCenter dayCareCenter) {
        withFormat {
            html { respond dayCareCenter }
            json { render ( [
                    id:dayCareCenter?.id
                    ,name: dayCareCenter?.name
                    ,address: dayCareCenter?.address
                    ,city: dayCareCenter?.city
                    ,state: dayCareCenter?.state
                    ,zip: dayCareCenter?.zip
                    ,email: dayCareCenter?.email
                    ,phone: dayCareCenter?.phoneNumber
                    ,capacity: dayCareCenter?.centerCapcity
                    ,rate: NumberFormat.getCurrencyInstance().format(dayCareCenter?.dailyRate)
                    ,other: dayCareCenter?.otherDetail
            ]  as JSON ) }
            xml { render (dayCareCenter as XML) }
        }
    }

    def create() {
        respond new DayCareCenter(params)
    }

    @Transactional
    def save(DayCareCenter dayCareCenter) {
        if (dayCareCenter == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dayCareCenter.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dayCareCenter.errors, view:'create'
            return
        }

        dayCareCenter.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dayCareCenter.label', default: 'DayCareCenter'), dayCareCenter.id])
                redirect dayCareCenter
            }
            '*' { respond dayCareCenter, [status: CREATED] }
        }
    }

    def edit(DayCareCenter dayCareCenter) {
        respond dayCareCenter
    }

    @Transactional
    def update(DayCareCenter dayCareCenter) {
        if (dayCareCenter == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dayCareCenter.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dayCareCenter.errors, view:'edit'
            return
        }

        dayCareCenter.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dayCareCenter.label', default: 'DayCareCenter'), dayCareCenter.id])
                redirect dayCareCenter
            }
            '*'{ respond dayCareCenter, [status: OK] }
        }
    }

    @Transactional
    def delete(DayCareCenter dayCareCenter) {

        if (dayCareCenter == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dayCareCenter.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dayCareCenter.label', default: 'DayCareCenter'), dayCareCenter.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dayCareCenter.label', default: 'DayCareCenter'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
