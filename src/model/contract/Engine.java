package model.contract;

import model.engine.EngineImpl;
import model.engine.GameBoard;
import model.engine.GamePiece;
import model.piece.commands.PieceCommands;
import model.player.GameTurn;

/**
 * @author sefira
 *
 */
public interface Engine {

	public GameTurn gameTurn();

	public GameBoard gameBoard();


	public GamePiece pieceOperator();


	public int getTotalNumPiece();


	public void configBoardSize(int boardSize);

	public void configNumPiece(int pieceNumberSelection);

	public void configObstacle(boolean hasObstacle);

	public void loadGame(EngineImpl e);

	public PieceCommands getPieceCommands();

	public boolean endGame();

}