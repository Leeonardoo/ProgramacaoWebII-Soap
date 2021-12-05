package br.furb.progweb.soap.endpoint

import br.furb.progweb.soap.GetUserRequest
import br.furb.progweb.soap.NAMESPACE_URI
import br.furb.progweb.soap.UserResponse
import br.furb.progweb.soap.*
import br.furb.progweb.soap.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
class UserEndpoint @Autowired constructor(private val userRepository: UserRepository) {

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    fun getUser(@RequestPayload request: GetUserRequest): UserResponse {
        return UserResponse().apply {
            user = userRepository.findUser(request.id)
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    fun createUser(@RequestPayload request: CreateUserRequest): UserResponse {
        return UserResponse().apply {
            user = userRepository.addUser(request)
        }
    }
}
