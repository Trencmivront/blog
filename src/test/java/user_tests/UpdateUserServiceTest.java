package user_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.blogs.blog.entities.user.containers.UserUpdateContainer;
import com.blogs.blog.entities.user.containers.UserCreateContainer;
import com.blogs.blog.entities.user.repo.UserRepository;
import com.blogs.blog.entities.user.services.UpdateUserService;
import com.blogs.blog.exceptions.UserNotFoundException;

class UpdateUserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UpdateUserService updateUserService;

	@BeforeEach
	void setup() {

		MockitoAnnotations.openMocks(this);

	}

	@Test
	void given_that_user_exists_and_updated_then_return_ok() {

//		Given

		User user = User.builder().email("akhsgda@akjshd")
				.id(1l)
				.name("Yılmaz")
				.password("asdasferwwdfs")
				.surname("Sönmez")
				.username("Trencmivront").build();

		when(userRepository.findById(1l)).thenReturn(Optional.of(user));

//		When

		ResponseEntity<String> responseEntity = updateUserService.execute(new UserUpdateContainer(1l, new UserCreateContainer(null, null, null, null, null)));

//		Then
//		exists?
		assertEquals(user, userRepository.findById(1l).get());
//		updated?
		assertEquals(ResponseEntity.ok("Updated user"), responseEntity);

//		This time we use userRepository.findById() twice because
//		we want to check that user exists and updated
		verify(userRepository, times(2)).findById(1l);
	}

	@Test
	void given_that_user_does_not_exists_throw_user_not_found_exception() {

//		Given
		when(userRepository.findById(1l)).thenThrow(new UserNotFoundException());

//	 	When & Then

		assertThrows(UserNotFoundException.class, () ->  updateUserService.execute(new UserUpdateContainer(1l, null)));

		verify(userRepository, times(1)).findById(1l);
	}

}
