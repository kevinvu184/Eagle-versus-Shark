package model.piece.movement;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.PieceInterface;
import model.contract.PieceMovementInterface;
import model.engine.EngineImpl;

/**
 * @author sefira & kevin
 *
 */

public class PieceMoveImpl implements PieceMovementInterface {

	protected Set<Cell> validMoves = new HashSet<>();

	/*
	 * @param piece - selected piece
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate
	 */
	@Requires({"piece != null", "distance == 1 || distance ==2"})
	@Ensures("validMoves != null")
	@Override
	public Set<Cell> getValidMove(PieceInterface piece, int distance) {
		Map<String, Integer> currentPosition = piece.getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");

		this.validMoves.addAll(validMovesSouth(currentX, currentY, distance));
		this.validMoves.addAll(validMovesNorth(currentX, currentY, distance));
		this.validMoves.addAll(validMovesEast(currentX, currentY, distance));
		this.validMoves.addAll(validMovesWest(currentX, currentY, distance));
		return validMoves;
	}

	/*
	 * @param piece - selected piece
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate for east direction
	 */
	@Requires({"x>=0", "y>=0", "step== 1 || step==2"})
	@Ensures("validMoves != null")
	private Set<Cell> validMovesEast(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getSize()
					&& !EngineImpl.getSingletonInstance().getBoard()
							.getOccupationState(x + i, y)) {
				Cell validMove = new Cell(x + i, y);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	/*
	 * @param piece - selected piece
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate for north direction
	 */
	@Requires({"x>=0", "y>=0", "step== 1 || step==2"})
	@Ensures("validMoves != null")
	private Set<Cell> validMovesNorth(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (y - i >= 0 && !EngineImpl.getSingletonInstance().getBoard()
					.getOccupationState(x, y - i)) {
				Cell validMove = new Cell(x, y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	/*
	 * @param piece - selected piece
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate for south direction
	 */
	@Requires({"x>=0", "y>=0", "step== 1 || step==2"})
	@Ensures("validMoves != null")
	private Set<Cell> validMovesSouth(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<Cell>();
		for (int i = 1; i <= step; i++) {
			if (y + i < EngineImpl.getSingletonInstance().getBoard().getSize()
					&& !EngineImpl.getSingletonInstance().getBoard()
							.getOccupationState(x, y + i)) {
				Cell validMove = new Cell(x, y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}
		return validMoves;
	}

	/*
	 * @param piece - selected piece
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate for west direction
	 */
	@Requires({"x>=0", "y>=0", "step== 1 || step==2"})
	@Ensures("validMoves != null")
	private Set<Cell> validMovesWest(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x - i >= 0 && !EngineImpl.getSingletonInstance().getBoard()
					.getOccupationState(x - i, y)) {
				Cell validMove = new Cell(x - i, y);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

}