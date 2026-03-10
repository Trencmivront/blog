package user_tests;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.containers.UserCreateContainer;
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
//		Given
		UserCreateContainer userCreateContainer = new UserCreateContainer("Test",
				"Testical",
				"Testesteron",
				"test@gmail.com",
				"test123");
		when(createUserService.execute(userCreateContainer));
		
		
		
	}

}
