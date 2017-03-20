import akka.actor.{ActorRef, Props, Actor}




class User(bookMyShow: ActorRef) extends Actor {


  override def receive = {
    case "Book Seat 1"=> bookMyShow ! "Book Seat 1"
    case "Cancel Seat 1" => bookMyShow ! "Cancel Seat 1"

  }
}
object User {
  def prop(ref: ActorRef):Props = Props(classOf[User],ref)
}

