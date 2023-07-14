package io.github.vcvitaly.grokkingfp
package ch8

import cats.effect.IO
import io.github.vcvitaly.grokkingfp.ch8.ExternalApi.{calendarEntriesApiCall, createMeetingApiCall}

import scala.jdk.CollectionConverters.*
import scala.jdk.javaapi.CollectionConverters

/**
 * MeetingScheduler.
 *
 * @author Vitalii Chura
 */
class MeetingScheduler {

  private def calendarEntries(name: String): IO[List[MeetingTime]] = {
    IO.delay(calendarEntriesApiCall(name).asScala.toList)
  }

  private def createMeeting(names: List[String], meeting: MeetingTime): IO[Unit] = {
    IO.delay(createMeetingApiCall(CollectionConverters.asJava(names), meeting))
  }

  private def scheduledMeetings(person1: String, person2: String): IO[List[MeetingTime]] = {
    for {
      person1Entries <- calendarEntries(person1)
      person2Entries <- calendarEntries(person2)
    } yield person1Entries.appendedAll(person2Entries)
  }

  private def meetingsOverlap(meeting1: MeetingTime, meeting2: MeetingTime): Boolean = {
    meeting1.endHour > meeting2.startHour && meeting2.endHour > meeting1.startHour
  }

  private def possibleMeetings(existingMeetings: List[MeetingTime], startHour: Int, endHour: Int, lengthHours: Int): List[MeetingTime] = {
    val slots = List.range(startHour, endHour - lengthHours + 1)
      .map(startHour => MeetingTime(startHour, startHour + lengthHours))

    slots.filter(slot => existingMeetings.forall(meeting => !meetingsOverlap(meeting, slot)))
  }

  def schedule(person1: String, person2: String, lengthHours: Int): IO[Option[MeetingTime]] = {
    for {
      existingMeetings <- scheduledMeetings(person1, person2)
        .orElse(scheduledMeetings(person1, person2))
        .orElse(IO.pure(List.empty))
      meetings = possibleMeetings(existingMeetings, 8, 16, lengthHours)
      aMeeting = meetings.headOption
      _ <- createMeeting(List(person1, person2), aMeeting)
    } yield aMeeting
  }

  def schedulingProgram(getName: IO[String], showMeeting: Option[MeetingTime] => IO[Unit]): IO[Unit] = {
    for {
      name1 <- getName
      name2 <- getName
      possibleMeeting <- schedule(name1, name2, 2)
      _ <- showMeeting(possibleMeeting)
    } yield ()
  }
}
