package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import com.google.java.contract.Requires;

import model.engine.EngineImpl;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import viewcontroller.contract.ViewControllerInterface;

/**
 * Invoked when user choose a piece before making a move.<br>
 * NOTE: we separate into two actions Select a Piece and Make a Move
 * 
 * @author ted &#38; kevin
 * 
 */
public class SelectPieceController implements ActionListener {

	private AbstractButton buttonClicked;
	private MovePieceController movePieceController;

	private ViewControllerInterface viewControllerFacade;

	private PlayerAction playerAction;

	/**
	 * @param facade
	 * @param boardView
	 */
	public SelectPieceController(ViewControllerInterface facade) {
		this.viewControllerFacade = facade;
		playerAction = new PlayerAction();
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.buttonClicked = (AbstractButton) e.getSource();

		if (EngineImpl.getSingletonInstance().getStartGame()) {
			viewControllerFacade.updateBoardSelectAnotherPiece(buttonClicked);
			checkPieceSelectedTurn();
		} else {
			viewControllerFacade.notifyNotStartGame();
		}
	}

	@Requires({ "teamType != null", "viewControllerFacade != null" })
	private void checkAllowTransitToMovePieceAction(TeamType teamType) {
		if (PieceType.parsePieceType(buttonClicked.getActionCommand()).team() == teamType) {

			viewControllerFacade.getPlayerAction(playerAction);
			System.out.println(playerAction.getPlayerAction());
			movePieceController = new MovePieceController(PieceType.parsePieceType(buttonClicked.getActionCommand()),
					viewControllerFacade);

			viewControllerFacade.updateBoardBeforeMovePiece(buttonClicked, movePieceController);
		} else {
			viewControllerFacade.notifySelectWrongTeam();
		}
	}

	@Requires("buttonClicked != null")
	private void checkPieceSelectedTurn() {
		if (EngineImpl.getSingletonInstance()
				.checkSelectPiece(PieceType.parsePieceType(buttonClicked.getActionCommand()))) {
			TeamType currentTurn = EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType();
			checkAllowTransitToMovePieceAction(currentTurn);

		}
	}

}
