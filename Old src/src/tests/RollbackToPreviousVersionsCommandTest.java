package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import controller.commands.RollbackToPreviousVersionCommand;
import model.Document;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import view.LatexEditorView;

class RollbackToPreviousVersionsCommandTest {

	@Test
	void test() {
		LatexEditorView viewer = new LatexEditorView();
		VersionsStrategy versionsStrategy = new StableVersionsStrategy();
		VersionsManager versionsItem = new VersionsManager(versionsStrategy,viewer);
		RollbackToPreviousVersionCommand roller = new RollbackToPreviousVersionCommand(versionsItem);
		versionsItem.enable();
		Document doc0 = new Document("a","a","a","0","e");
		viewer.setCurrentDocument(doc0);
		doc0.changeVersion();												//theoritika paei 1 to verId
		versionsStrategy.putVersion(doc0);
		roller.execute();
		assertNotEquals("1",viewer.getCurrentDocument().getVersionID());
		Document doc1 = new Document("a","a","a","0","e");
		viewer.setCurrentDocument(doc1);
		viewer.setStrategy("volatile");
		versionsStrategy.putVersion(doc1);
		int sizeBeforeChange = versionsStrategy.getEntireHistory().size();  //perno to size tou history prin tin allagi
		roller.execute();
		int sizeAfterChange = versionsStrategy.getEntireHistory().size();	//perno to size tou history meta tin allagi
		assertNotEquals(sizeBeforeChange,sizeAfterChange);
		
	}

}
