package user.review

import daycare.provider.DayCareCenter
import grails.test.mixin.*
import spock.lang.*

@TestFor(ReviewCommentController)
@Mock(ReviewComment)
class ReviewCommentControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        Review rv1 = new Review(dateOfReview: new Date() - 4 ,reviewTitle: 'I love this day care',reviewDetail: 'This is the best family day care in Malden',
                otherDetail: 'Very clean and professional',stars: 5,recommended: true)
        params << [comment: 'word',commentDate: new Date() - 2,published: true,review: rv1]
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.reviewCommentList
            model.reviewCommentCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.reviewComment!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def reviewComment = new ReviewComment()
            reviewComment.validate()
            controller.save(reviewComment)

        then:"The create view is rendered again with the correct model"
            model.reviewComment!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            reviewComment = new ReviewComment(params)

            controller.save(reviewComment)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/reviewComment/show/1'
            controller.flash.message != null
            ReviewComment.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def reviewComment = new ReviewComment(params)
            controller.show(reviewComment)

        then:"A model is populated containing the domain instance"
            model.reviewComment == reviewComment
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def reviewComment = new ReviewComment(params)
            controller.edit(reviewComment)

        then:"A model is populated containing the domain instance"
            model.reviewComment == reviewComment
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/reviewComment/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def reviewComment = new ReviewComment()
            reviewComment.validate()
            controller.update(reviewComment)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.reviewComment == reviewComment

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            reviewComment = new ReviewComment(params).save(flush: true)
            controller.update(reviewComment)

        then:"A redirect is issued to the show action"
            reviewComment != null
            response.redirectedUrl == "/reviewComment/show/$reviewComment.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/reviewComment/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def reviewComment = new ReviewComment(params).save(flush: true)

        then:"It exists"
            ReviewComment.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(reviewComment)

        then:"The instance is deleted"
            ReviewComment.count() == 0
            response.redirectedUrl == '/reviewComment/index'
            flash.message != null
    }
}
