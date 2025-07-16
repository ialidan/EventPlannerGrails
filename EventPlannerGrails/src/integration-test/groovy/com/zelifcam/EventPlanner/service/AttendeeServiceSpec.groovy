package com.zelifcam.EventPlanner.service

import com.zelifcam.EventPlanner.domain.Attendee
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AttendeeServiceSpec extends Specification {

    AttendeeService attendeeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Attendee(...).save(flush: true, failOnError: true)
        //new Attendee(...).save(flush: true, failOnError: true)
        //Attendee attendee = new Attendee(...).save(flush: true, failOnError: true)
        //new Attendee(...).save(flush: true, failOnError: true)
        //new Attendee(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //attendee.id
    }

    void "test get"() {
        setupData()

        expect:
        attendeeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Attendee> attendeeList = attendeeService.list(max: 2, offset: 2)

        then:
        attendeeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        attendeeService.count() == 5
    }

    void "test delete"() {
        Long attendeeId = setupData()

        expect:
        attendeeService.count() == 5

        when:
        attendeeService.delete(attendeeId)
        sessionFactory.currentSession.flush()

        then:
        attendeeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Attendee attendee = new Attendee()
        attendeeService.save(attendee)

        then:
        attendee.id != null
    }
}
