package user_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.entities.user.services.CreateUserService;

public class CreateUserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private CreateUserService createUserService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void test_that_create_user_service_creates_user_correctly() {
		
		User user = User.builder()
				.email("")
				.id(1l)
				.name("")
				.password("")
				.surname("")
				.username("").build();
		
		
		
	}

}
