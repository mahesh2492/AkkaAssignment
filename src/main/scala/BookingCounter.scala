import akka.actor.{Props, ActorSystem}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

/**
  * Created by knoldus on 20/3/17.
  */
object BookingCounter extends App{
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

  val system = ActorSystem("Users",config)
  val bookMyShowActor  = system.actorOf(BookMyShow.prop)
  val userActor = system.actorOf(FromConfig.props(User.prop(bookMyShowActor)),"poolRouter")
  userActor ! "Cancel Seat 1"

  userActor ! "Book Seat 1"

  userActor ! "Book Seat 1"

  userActor ! "Book Seat 1"
  userActor ! "Cancel Seat 1"




}






