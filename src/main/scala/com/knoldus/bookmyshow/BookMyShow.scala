package com.knoldus.bookmyshow


import akka.actor.{ActorRef, Props, ActorSystem, Actor}
import com.typesafe.config.ConfigFactory
import akka.routing.FromConfig

object BookMyShow extends App {
  val system = ActorSystem("BookMyShowSystem")
  val bookProps = BookMyShowActor.bookMyShowProps
  val bookRef = system.actorOf(bookProps)
  val userSystem = system.actorOf(FromConfig.props(UserActor.prop(bookRef)),"poolRouter")


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

  userSystem ! "bookseat"
  userSystem ! "bookseat"

}
