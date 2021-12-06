package br.furb.progweb.soap.repository

import br.furb.progweb.soap.Car
import br.furb.progweb.soap.User
import br.furb.progweb.soap.domain.CarOwner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class CarOwnerRepository @Autowired constructor(
    private val carRepository: CarRepository,
    private val userRepository: UserRepository
) {

    //userId -> carId
    companion object {
        private val carOwners = mutableSetOf<CarOwner>()

        fun removeUser(userId: Int) {
            carOwners.removeAll { it.userId == userId }
        }

        fun removeCar(carSerialNumber: Int) {
            carOwners.removeAll { it.carSerialNumber == carSerialNumber }
        }
    }

    fun getCarsByOwner(ownerId: Int): List<Car> {
        val carIds = carOwners.filter { it.userId == ownerId }.map { it.carSerialNumber }
        userRepository.find(ownerId) ?: throw NoSuchElementException("Usuário não encontrado")

        if (carIds.isEmpty()) {
            throw NoSuchElementException("Nenhum carro encontrado com o usuário informado")
        }

        return carRepository.findAllBySerial(carIds)
    }

    //Lista de donos por carro
    fun getCarOwners(carSerialNumber: Int): List<User> {
        val list = carOwners.filter { it.carSerialNumber == carSerialNumber }.map { it.userId }
        carRepository.find(carSerialNumber) ?: throw NoSuchElementException("Carro não encontrado")

        if (carOwners.isEmpty()) {
            throw NoSuchElementException("Nenhum dono encontrado com o carro informado")
        }

        return userRepository.findAllById(list)
    }

    //Adicionar carro ao usuário
    fun addOwner(userId: Int, carSerialNumber: Int): List<Car> {
        userRepository.find(userId) ?: throw NoSuchElementException("Usuário não encontrado")
        carRepository.find(carSerialNumber) ?: throw NoSuchElementException("Carro não encontrado")

        carOwners.add(CarOwner(userId, carSerialNumber))
        return getCarsByOwner(userId)
    }

    //Remover carro do usuário/usuário do carro
    fun removeCarOwner(userId: Int, carSerialNumber: Int): List<Car> {
        userRepository.find(userId) ?: throw NoSuchElementException("Usuário não encontrado")
        carRepository.find(carSerialNumber) ?: throw NoSuchElementException("Carro não encontrado")

        carOwners.remove(CarOwner(userId, carSerialNumber))
        return try {
            getCarsByOwner(userId)
        } catch (e: NoSuchElementException) {
            return listOf()
        }
    }
}