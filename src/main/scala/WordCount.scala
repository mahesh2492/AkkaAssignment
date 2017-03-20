import java.io.{FileNotFoundException, File}

import akka.actor.{Props, ActorSystem, Actor}
import scala.io.Source


class WordCount extends Actor {

  var wordCount = 0;
  val child = context.actorOf(Props[ChildWordCount])

  override def receive = {
    case "EOF" => println("Word Count in given file "+ wordCount)
    case filename:String => {

      try {
        for (line <- Source.fromFile(filename).getLines()) {
          child ! line
        }
        child ! "EOF"
      }
      catch {
        case e: FileNotFoundException => {
          println("Couldn't find that file.")
        }
      }
    }
    case count: Int => wordCount += count

  }

}

class ChildWordCount extends WordCount {


  override def receive = {
    case "EOF" => sender ! "EOF"
    case line:String => sender ! line.trim.replaceAll(" +"," ").split(" ").length
  }
}

object WordCount extends App {

  val system = ActorSystem("WordCount")
  val props = Props[WordCount];
  val parent = system.actorOf(props)
  val file = "index.txt"
   parent ! file
}
