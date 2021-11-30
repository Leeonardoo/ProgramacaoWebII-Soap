package br.furb.progweb.soap.repository

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
        users[user.id] = user
    }

    fun findUser(id: Int): User? = users[id]

    companion object {
        private val users: MutableMap<Int, User> = hashMapOf()
    }
}
