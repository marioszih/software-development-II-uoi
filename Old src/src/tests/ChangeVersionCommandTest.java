package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import controller.commands.ChangeVersionsStrategyCommand;
import model.VersionsManager;
import view.LatexEditorView;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;

class ChangeVersionCommandTest {

	@Test
	void test() {
		LatexEditorView viewer = new LatexEditorView();
		VersionsStrategy versionsStrategy = new StableVersionsStrategy();
		VersionsManager versionsItem = new VersionsManager(versionsStrategy,viewer);
		ChangeVersionsStrategyCommand changer = new ChangeVersionsStrategyCommand(versionsItem);
		VersionsStrategy strategy0 = versionsItem.getStrategy();
		viewer.setStrategy("volatile");
		changer.execute();
		VersionsStrategy strategy1 = versionsItem.getStrategy();
		assertNotEquals(strategy0,strategy1);
		viewer.setStrategy("stable");
		changer.execute();
		VersionsStrategy strategy2 = versionsItem.getStrategy();
		assertNotEquals(strategy1,strategy2);
	}

}
