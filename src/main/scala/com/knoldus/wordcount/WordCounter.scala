package com.knoldus.wordcount

import akka.actor.ActorSystem
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object WordCounter extends App{
  val system = ActorSystem("WordCounterSystem")
  val parentProps = ParentWordCount.props
  val parentRef = system.actorOf(parentProps)

  val file = "demo.txt"
  implicit val timeout = Timeout(5 seconds)
  val future = parentRef ? file
  val result = Await.result(future, timeout.duration)
  println(result)
}