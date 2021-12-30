package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.commands.DisableVersionsManagementCommand;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import view.LatexEditorView;

class DisableVersionsManagementCommandTest {

	@Test
	void test() {
		LatexEditorView viewer = new LatexEditorView();
		VersionsStrategy versionsStrategy = new StableVersionsStrategy();
		VersionsManager versionsItem = new VersionsManager(versionsStrategy,viewer);
		boolean t = false;
		versionsItem.enable();
		DisableVersionsManagementCommand disabler = new DisableVersionsManagementCommand(versionsItem);
		disabler.execute();
		assertEquals(t,versionsItem.isEnabled());
	}

}
