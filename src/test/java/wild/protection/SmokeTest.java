package wild.protection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import wild.protection.controllers.publics.PublicHomeController;

@SpringBootTest
class SmokeTest {

	@Autowired
	private PublicHomeController controller;

	@Test
	void contextLoads(Model model) throws Exception {
		
		assertThat(controller.loadmainRegisterPage(model));
	}
}
