package com.knoldus.wordcount

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.Await
import scala.io._


class ParentWordCount extends Actor {

  val childRef = context.actorOf(ChildWordCount.props)
  var wordCount = 0
  var senderRef: Option[ActorRef] = None

  override def receive = {
    case "EOF" => senderRef.foreach(x => x ! wordCount)
    case file: String => senderRef = Some(sender)
      for (line <- Source.fromFile(file).getLines())
        childRef ! line
      childRef ! "EOF"
    case count: Int => wordCount += count

  }
}

object ParentWordCount{

  def props: Props = Props[ParentWordCount]
}


