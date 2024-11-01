package org.learn.gobang.view;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import org.springframework.context.annotation.Scope;

@FXMLView("/fxml/game.fxml")
@Scope("prototype")
public class GameView extends AbstractFxmlView {
}
