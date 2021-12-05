package br.furb.progweb.soap.repository

import br.furb.progweb.soap.Car
import br.furb.progweb.soap.CreateOwnerRequest
import br.furb.progweb.soap.CreateUserRequest
import br.furb.progweb.soap.User
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class UserRepository {

    @PostConstruct
    fun initData() {
        val user = User().apply {
            id = 1
            name = "Teste"
            email = "teste@furb.br"
            age = 19
            cpf = "000.000.000-00"
            numCnh = "1212314134"
        }
        val user2 = User().apply {
            id = 2
            name = "Teste2"
            email = "teste2@furb.br"
            age = 19
            cpf = "000.000.000-00"
            numCnh = "1212314134"
        }
        users[user.id] = user
        users[user2.id] = user2
    }

    fun addUser(request: CreateUserRequest): User? {
        validate(request.user)

        users[request.user.id] = request.user
        return users[request.user.id]
    }

    fun addCar(request: CreateOwnerRequest): User? {
        val user = findUser(request.user.id)

        if (request.cars.size > 1) {
            for (i in 0..request.cars.size) {
                validateOwner(user, request.cars[i])
                user?.cars?.add(request.cars[i])
            }
        } else {
            validateOwner(user, request.car)
            user?.cars?.add(request.car);
        }

        return users[user?.id]
    }

    fun findUser(id: Int): User? {
        if (id < 0) {
            throw IllegalArgumentException("O campo ID não pode ser menor que 0")
        }
        if (users[id] == null){
            throw NoSuchElementException("Usuário não encontrado")
        }
        return users[id]
    }

    companion object {
        private val users: MutableMap<Int, User> = hashMapOf()
    }

    private fun validate(user: User?) {
        user ?: throw IllegalArgumentException("O campo user não pode estar vazio")
        if (users[user.id] != null)
            throw Exception("Usuário já cadastrado")

        with(user) {
            if (id < 0) {
                throw IllegalArgumentException("O campo ID não pode ser menor que 0")
            }
            if (name.isNullOrBlank()) {
                throw IllegalArgumentException("O campo name é obrigatório")
            }
            if (email.isNullOrBlank()) {
                throw IllegalArgumentException("O campo email é obrigatório")
            }
            if (age < 0) {
                throw IllegalArgumentException("O campo age é obrigatório")
            }
            if (cpf.isNullOrBlank()) {
                throw IllegalArgumentException("Campo CPF não pode ser vazio")
            }
        }
    }

    private fun validateOwner(user: User?, car: Car?){
        validate(user)
    }
}
