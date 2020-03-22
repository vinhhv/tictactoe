package big.daddy.tictactoe

import zio.test.{DefaultRunnableSpec, assert, suite, testM}
import zio.test.Assertion.equalTo
import zio.test.environment.TestConsole

object TicTacToeSpec extends DefaultRunnableSpec(
  suite("TicTacToe") (
    testM("prints to console") (
      for {
        test <- TestConsole.makeTest(TestConsole.DefaultData)
        _ <- TicTacToe.program.provide(new TestConsole {
          val console = test
        })
        out <- test.output
      } yield assert(out, equalTo(Vector("TicTacToe game!\n")))
    )
  )
)
