package com.knoldus.bookmyshow

import akka.actor.{Actor, ActorRef, Props}


class UserActor(bookMyShow: ActorRef) extends Actor{
  override def receive = {
    case "bookseat" => bookMyShow ! "bookseat"
    case "cancelseat" => bookMyShow ! "cancelseat"
  }

}

object UserActor{
  def userProps(bookShow: ActorRef) = Props(classOf[UserActor], bookShow)
}
