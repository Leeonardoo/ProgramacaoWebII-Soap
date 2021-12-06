package br.furb.progweb.soap.repository

import br.furb.progweb.soap.Car
import br.furb.progweb.soap.User
import br.furb.progweb.soap.domain.CarOwner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CarOwnerRepository @Autowired constructor(
    private val carRepository: CarRepository,
    private val userRepository: UserRepository
) {

    //userId -> carId
    companion object {
        private val carOwners = mutableSetOf<CarOwner>()

        fun removeUser(userId: Int) {
            val user = carOwners.filter { it.userId == userId }
            if (user.isEmpty()) {
                throw NoSuchElementException("Usuário não encontrado")
            } else
                carOwners.removeAll { it.userId == userId }
        }

        fun removeCar(carSerialNumber: Int) {
            val car = carOwners.filter { it.carSerialNumber == carSerialNumber }
            if (car.isEmpty()) {
                throw NoSuchElementException("Nenhum carro encontrado")
            } else
                carOwners.removeAll { it.carSerialNumber == carSerialNumber }
        }
    }

    fun getCarsByOwner(ownerId: Int): List<Car> {
        val carIds = carOwners.filter { it.userId == ownerId }.map { it.carSerialNumber }
        val user = carOwners.filter { it.userId == ownerId }

        if (user.isEmpty()) {
            throw NoSuchElementException("Usuário não encontrado")
        }

        if (carIds.isEmpty()) {
            throw NoSuchElementException("Nenhum carro encontrado com o usuário informado")
        }

        return carRepository.findAll(carIds)
    }

    //Lista de donos por carro
    fun getCarOwners(carSerialNumber: Int): List<User> {
        val list = carOwners.filter { it.carSerialNumber ==  carSerialNumber }.map { it.userId }
        val car = carOwners.filter { it.carSerialNumber == carSerialNumber }

        if (car.isEmpty()) {
            throw NoSuchElementException("Nenhum carro encontrado")
        }

        if (carOwners.isEmpty()) {
            throw NoSuchElementException("Nenhum dono encontrado com o carro informado")
        }
        return userRepository.findAll(list)
    }

    //Adicionar carro ao usuário
    fun addOwner(userId: Int, carSerialNumber: Int){
        if (userRepository.find(userId) != null && carRepository.find(carSerialNumber) != null) {
            carOwners.add(CarOwner(userId, carSerialNumber));
        } else
            throw Exception("Erro ao adicionar veículo")
    }

    //Remover carro do usuário/usuário do carro
    fun removeCarOwner(userId: Int, carSerialNumber: Int) {
        if (userRepository.find(userId) != null && carRepository.find(carSerialNumber) != null)
            carOwners.remove(CarOwner(userId, carSerialNumber))
        else
            throw Exception("Erro ao adicionar veículo")
    }

}