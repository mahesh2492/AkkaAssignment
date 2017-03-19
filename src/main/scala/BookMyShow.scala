import akka.actor.{Props, ActorSystem, Actor}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory



class BookMyShow extends Actor{


  var flag = false
  var isFirstTimeVisit = true
  def receive = {
    case request if(request == "Book Seat 1") => {
      if(flag == false) {

        flag = true
        println(s"Thanks for Booking")
      }
      else
        println("Seat is already booked")
    }

    case request if(request == "Cancel Seat 1") => {
      if(flag == true) {
        flag = false
        println("Seat is successfully cancelled")
      }

      else {
        if(isFirstTimeVisit == true) {
          isFirstTimeVisit = false
          println("You have not booked any seat so you cant cancel")
        }
        else
        println("Seat is already cancelled")
      }

    }

  }

}

object BookMyShow extends App {


  val config = ConfigFactory.parseString(
    """
      |akka.actor.deployment {
      | /poolRouter {
      |   router = round-robin-pool
      |   nr-of-instances = 5
      | }
      |}
    """.stripMargin
  )
  val system = ActorSystem("RouterSystem", config)
  val actor = system.actorOf(FromConfig.props(Props[BookMyShow]), "poolRouter")


  actor ! "Book Seat 1"
  actor ! "Book Seat 1"
  actor ! "Book Seat 1"
  actor ! "Cancel Seat 1"
  actor ! "Cancel Seat 1"
  actor ! "Book Seat 1"
  actor ! "Cancel Seat 1"
  actor ! "Cancel Seat 1"
  actor ! "Cancel Seat 1"

}
