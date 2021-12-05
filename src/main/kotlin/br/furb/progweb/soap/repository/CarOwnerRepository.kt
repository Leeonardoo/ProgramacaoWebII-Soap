package br.furb.progweb.soap.repository

import br.furb.progweb.soap.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CarOwnerRepository @Autowired constructor(
    private val carRepository: CarRepository,
    private val userRepository: UserRepository
) {

    //userId -> Car
    private val carOwners = mutableMapOf<Int, Car>()

}