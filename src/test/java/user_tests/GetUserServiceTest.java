package user_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.blogs.blog.entities.user.User;
import com.blogs.blog.entities.user.dto.UserDTO;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.entities.user.services.GetUserService;
import com.blogs.blog.exceptions.UserNotFoundException;

class GetUserServiceTest {
	
	@Mock // what to mock to response of
	private UserRepository userRepository;
	
	@InjectMocks // the thing we are testing
	private GetUserService getUserService;
	
	@BeforeEach
	public void setup() {
		// Initializes the repository and service
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test // The test we are running
	void test_that_get_user_service_class_will_return_user_if_exists() {
		
		// * Given *
		User user = User.builder().email("akhsgda@akjshd")
				.id(1)
				.name("Yılmaz")
				.password("asdasferwwdfs")
				.surname("Sönmez")
				.username("Trencmivront").build();
		
		// "when the x method is called, then return the y"
		// this is a simple description for this line
		// so our test is, function should return user when user is
		when(userRepository.findById(1l)).thenReturn(Optional.of(user));
		
		// * When *
		
		// this will return UserDTO inside a ResponseEntity
		ResponseEntity<UserDTO> userDTO = getUserService.execute(1l);
		
		// * Then *
		
		assertEquals(ResponseEntity.ok(new UserDTO(user)), userDTO);
		
		// asserts that user repository was only called once
		verify(userRepository, times(1)).findById(1l);
		
	}
	
	@Test
	void test_that_get_user_service_class_will_throw_user_not_found_exception_if_user_not_exists() {
		
		// * Given *
		
		when(userRepository.findById(1l)).thenThrow(new UserNotFoundException());
		
		// * When * & * Then *

		assertThrows(UserNotFoundException.class, () -> getUserService.execute(1l));
		
		verify(userRepository, times(1)).findById(1l);
		
	}

}
