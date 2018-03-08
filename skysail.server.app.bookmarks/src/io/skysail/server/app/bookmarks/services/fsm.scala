package io.skysail.server.app.bookmarks.services

sealed trait State
object S1 extends State
object S2 extends State

//sealed trait Transition {
  //def from(): State
  //def to(): State
//}
case class Transition(from: State, to: State) //extends Transition {}

class FSM {

  val t1 = Transition(S1, S1)
  val t2 = Transition(S1, S2)

  val initialState = S1

  def process(xs: List[Char]): Unit = {

  }

}

class TestFsm() {
  new FSM().process("xTabC".toList);
}
