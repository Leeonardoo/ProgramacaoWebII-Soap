package br.furb.progweb.soap.repository

import br.furb.progweb.soap.*
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class UserRepository {

    private val users: MutableMap<Int, User> = hashMapOf()

    @PostConstruct
    fun initData() {
        val user = User().apply {
            id = 1
            name = "Daniel"
            email = "daniel@furb.br"
            age = 20
            cpf = "000.000.000-00"
            numCnh = "1212314134"
        }
        val user2 = User().apply {
            id = 2
            name = "Leonardo"
            email = "leonardo@furb.br"
            age = 19
            cpf = "000.000.000-00"
            numCnh = "1212314134"
        }
        val user3 = User().apply {
            id = 3
            name = "Larson"
            email = "larson@furb.br"
            age = 19
            cpf = "000.000.000-00"
            numCnh = "1212314134"
        }
        users[user.id] = user
        users[user2.id] = user2
        users[user3.id] = user3
    }

    fun create(request: CreateUserRequest): User? {
        if (users[request.user.id] != null) {
            throw IllegalArgumentException("Já existe um usuário com o id informado")
        }

        validate(request.user)

        users[request.user.id] = request.user
        return users[request.user.id]
    }

    fun find(id: Int): User? {
        if (id < 0) {
            throw IllegalArgumentException("O campo ID não pode ser menor que 0")
        }
        if (users[id] == null) {
            throw NoSuchElementException("Usuário não encontrado")
        }
        return users[id]
    }

    fun delete(id: Int): User? {
        if (users[id] == null) {
            throw NoSuchElementException("Nenhum usuário encontrado com o id informado")
        }

        return users.remove(id)
    }

    fun update(request: UpdateUserRequest): User? {
        if (users[request.user.id] == null) {
            throw NoSuchElementException("Nenhum usúário encontrado com o id informado")
        }

        validate(request.user)

        users[request.user.id] = request.user
        return users[request.user.id]
    }

    private fun validate(user: User?) {
        user ?: throw IllegalArgumentException("O campo user não pode estar vazio")

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

    fun findAll(id: List<Int>): List<User> =
        users.filter { id.contains(it.key) }.values.toList()
}
