import akka.actor.{Props, ActorSystem, Actor}




class BookMyShow extends Actor{
  val maxSeat = 5
  var totalNoOfSeats = maxSeat
  def receive = {
    case request:String if(request == "Book Seat 1")   => if(totalNoOfSeats != 0)
      {
        totalNoOfSeats -= 1
        println("Seat is successfully booked "+totalNoOfSeats + sender().path)
        Thread.sleep(10000)
      }
      else
      println("Show is Full")

    case request: String if(request == "Cancel Seat 1") => if(totalNoOfSeats == maxSeat)
      println("Show is Empty")

      else if(totalNoOfSeats < 5 )
      {
        totalNoOfSeats += 1
        println("Seat is successfully cancelled"+ sender().path)

      }


  }

  }

object BookMyShow {

  def prop:Props = Props[BookMyShow]


}
