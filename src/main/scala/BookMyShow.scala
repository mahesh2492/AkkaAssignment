import akka.actor.{Props, ActorSystem, Actor}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory



class BookMyShow extends Actor{
  val maxSeat = 5
  var totalNoOfSeats = maxSeat
  def receive = {
    case request:String if(request == "Book Seat 1")   => if(totalNoOfSeats != 0)
      {
        totalNoOfSeats -= 1
        println("Seat is successfully booked")
      }
      else
      println("Show is Full")

    case request: String if(request == "Cancel Seat 1") => if(totalNoOfSeats == maxSeat)
      println("Show is Empty")

      else if(totalNoOfSeats < 5 )
      {
        totalNoOfSeats += 1
        println("Seat is successfully cancelled")

      }


  }

  }

object BookMyShow extends App {

  val system = ActorSystem("BookMyShow")
  val actor = system.actorOf(Props[BookMyShow])
  actor ! "Cancel Seat 1"
  actor ! "Book Seat 1"
  actor ! "Book Seat 1"
  actor ! "Cancel Seat 1"


}
