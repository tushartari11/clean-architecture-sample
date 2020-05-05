package usecases

import domain.EmailAddress
import domain.User
import domain.UserRepository
import io.mockk.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Create user use case")
class CreateUserTest {

    @Test
    fun `it calls the repo when saving a user`() {
        val user = User(email = EmailAddress("luis.s@gmail.com"), name = "Luís Soares", password = "toEncode")
        val repository = mockk<UserRepository> {
            every { save(user.copy(hashedPassword = "encoded")) } just Runs
        }
        val createUser = CreateUser(repository)

        createUser(user)

        verify(exactly = 1) { repository.save(user.copy(hashedPassword = "encoded")) }
    }
}