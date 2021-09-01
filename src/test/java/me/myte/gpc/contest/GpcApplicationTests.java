package me.myte.gpc.contest;

import me.myte.gpc.contest.repositories.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GpcApplication.class})
class GpcApplicationTests {

	@Resource
	private PlaceRepository placeRepository;

	@Test
	void contextLoads() {
	}

}
