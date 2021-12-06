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
    private val carOwners = mutableSetOf<CarOwner>()


    fun getCarsByOwner(ownerId: Int): List<Car> {
        val carIds = carOwners.filter { it.userId == ownerId }.map { it.carSerialNumber }

        if (carIds.isEmpty()) {
            throw NoSuchElementException("Nenhum carro encontrado com o usuário informado")
        }

        return carRepository.findAll(carIds)
    }

    //Lista de donos por carro
    fun getCarOwners(carSerialNumber: Int): List<User> {
        val list = carOwners.filter { it.carSerialNumber ==  carSerialNumber }.map { it.userId }
        if (carOwners.isEmpty()) {
            throw NoSuchElementException("Nenhum dono encontrado com o carro informado")
        }
        return userRepository.findAll(list)
    }

    //Adicionar carro ao usuário
    fun addOwner(userId: Int, carSerialNumber: Int){
        carOwners.add(CarOwner(userId, carSerialNumber));
    }

    //Remover carro do usuário/usuário do carro
    fun removeCarOwner(userId: Int, carSerialNumber: Int) {
        carOwners.remove(CarOwner(userId, carSerialNumber))
    }

}