package big.daddy.tictactoe.parser
import big.daddy.tictactoe.domain.MenuCommand
import zio.UIO

trait MenuCommandParserLive extends MenuCommandParser {
  val menuCommandParser = new MenuCommandParser.Service[Any] {
    def parse(input: String): UIO[MenuCommand] = ???
  }
}
