package user.review

import findMeSitter.user.User
import grails.test.mixin.*
import spock.lang.*

@TestFor(ReviewerController)
@Mock(Reviewer)
class ReviewerControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        User user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas' )
        params << [dateOfFirstReview: new Date() - 10 , dateOfLatestReview: new Date(),userDetail: user1]
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.reviewerList
            model.reviewerCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.reviewer!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            User user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas',gender: 'M',state: 'MA')
            def reviewer = new Reviewer(userDetail: user1)
            reviewer.validate()
            controller.save(reviewer)

        then:"The create view is rendered again with the correct model"
            model.reviewer!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            reviewer = new Reviewer(params)

            controller.save(reviewer)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/reviewer/show/1'
            controller.flash.message != null
            Reviewer.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def reviewer = new Reviewer(params)
            controller.show(reviewer)

        then:"A model is populated containing the domain instance"
            model.reviewer == reviewer
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def reviewer = new Reviewer(params)
            controller.edit(reviewer)

        then:"A model is populated containing the domain instance"
            model.reviewer == reviewer
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/reviewer/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas',gender: 'M',state: 'MA')
            def reviewer = new Reviewer(userDetail: user1)
            reviewer.validate()
            controller.update(reviewer)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.reviewer == reviewer

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            reviewer = new Reviewer(params).save(flush: true)
            controller.update(reviewer)

        then:"A redirect is issued to the show action"
            reviewer != null
            response.redirectedUrl == "/reviewer/show/$reviewer.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/reviewer/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def reviewer = new Reviewer(params).save(flush: true)

        then:"It exists"
            Reviewer.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(reviewer)

        then:"The instance is deleted"
            Reviewer.count() == 0
            response.redirectedUrl == '/reviewer/index'
            flash.message != null
    }
}
