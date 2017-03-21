package com.knoldus.bookmyshow

import akka.actor.{Actor, Props}

class BookMyShowActor extends Actor {
  var isAvailable = true
  override def receive = {
    case "getstatus" => sender() ! isAvailable
    case "bookseat" => if(isAvailable == true){

      sender() ! isAvailable
      println("Seat booked")
      isAvailable = false
                        }
      else{
      println("cannot book the seat! HOUSEFULL.. :(")
    }

    case "cancelseat" => isAvailable = true
                      println("seat canceled")
  }
}

object BookMyShowActor {
  val bookMyShowProps = Props[BookMyShowActor]
}
