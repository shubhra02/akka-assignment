package com.knoldus.wordcount

import akka.actor.{Actor, Props}

class ChildWordCount extends Actor{

  override def receive = {
    case "EOF" => sender ! "EOF"
    case line: String => sender !  line.split("\\s+").length


  }

}

object ChildWordCount{

  def props: Props = Props[ChildWordCount]
}
