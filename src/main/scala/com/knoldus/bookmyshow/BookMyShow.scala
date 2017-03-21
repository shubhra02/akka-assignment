package com.knoldus.bookmyshow


import akka.actor.{ActorRef, Props, ActorSystem, Actor}
import com.typesafe.config.ConfigFactory
import akka.routing.FromConfig

object BookMyShow extends App {

  val user = ConfigFactory.parseString(
    """
      |akka.actor.deployment {
      | /poolRouter {
      | router = round-robin-pool
      | nr-of-instances = 4
      | }
      |}
    """.stripMargin
  )

  val system = ActorSystem("BookMyShowSystem", user)
  val bookProps = BookMyShowActor.bookMyShowProps
  val bookRef = system.actorOf(bookProps)
  val userSystem = system.actorOf(FromConfig.props(UserActor.userProps(bookRef)),"poolRouter")

  userSystem ! "bookseat"
  userSystem ! "bookseat"
  userSystem ! "cancelseat"
  userSystem ! "bookseat"

}
