package br.furb.progweb.soap.repository

import br.furb.progweb.soap.Car
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
        val carIds = carOwners.filter { it.userId == ownerId }.map { it.carId }

        if (carIds.isEmpty()) {
            throw NoSuchElementException("Nenhum carro encontrado com o usuário informado")
        }

        return carRepository.findAll(carIds)
    }

    //Lista de donos por carro
    //Adicionar/remover carro ao usuário
    //Adicionar/remover usuário ao carro
    //Remover ambos
}