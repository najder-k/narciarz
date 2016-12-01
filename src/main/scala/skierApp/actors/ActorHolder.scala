package skierApp.actors

object ActorHolder {
  //todo: make a supervisor
  val actorSystem = ActorImplicits.actorSystem

  val exampleActor = actorSystem.actorOf(ExampleActor.props, "exampleActor")
}
