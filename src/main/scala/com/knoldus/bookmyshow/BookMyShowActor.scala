package com.knoldus.bookmyshow

import akka.actor.{Actor, Props}

class BookMyShowActor extends Actor {
  var isAvailable = true
  override def receive = {
    case "getstatus" => sender() ! isAvailable
    case "bookseat" => isAvailable = false
                     sender() ! isAvailable
                     println("Seat booked")
    case "cancelseat" => isAvailable = true
                      println("seat canceled")
  }
}

object BookMyShowActor {
  val bookMyShowProps = Props[BookMyShowActor]
}
