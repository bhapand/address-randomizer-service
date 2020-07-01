package com.lial.addressrandomizerservice;

import com.lial.addressrandomizerservice.controller.AddressController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AddressRandomizerServiceApplicationTests {

	@Autowired
	private AddressController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
