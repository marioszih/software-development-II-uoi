package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.commands.EnableVersionsManagementCommand;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import view.LatexEditorView;

class EnableVersionsManagementCommandTest {

	@Test
	void test() {
		LatexEditorView viewer = new LatexEditorView();
		VersionsStrategy versionsStrategy = new StableVersionsStrategy();
		VersionsManager versionsItem = new VersionsManager(versionsStrategy,viewer);
		boolean t = true;
		versionsItem.disable();
		viewer.setStrategy("volatile");
		EnableVersionsManagementCommand enabler = new EnableVersionsManagementCommand(versionsItem);
		enabler.execute();
		assertEquals(t,versionsItem.isEnabled());
		viewer.setStrategy("stable");
		versionsItem.disable();
		enabler.execute();
		assertEquals(t,versionsItem.isEnabled());
	}

}
