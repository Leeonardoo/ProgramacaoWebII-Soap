package br.furb.progweb.soap.endpoint

import br.furb.progweb.soap.*
import br.furb.progweb.soap.repository.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
class CarEndpoint @Autowired constructor(private val carRepository: CarRepository) {

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCarsRequest")
    @ResponsePayload
    fun getAllCars(@RequestPayload request: GetCarsRequest): CarsResponse {
        return CarsResponse().apply {
            cars.addAll(carRepository.findAll())
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCarRequest")
    @ResponsePayload
    fun getCar(@RequestPayload request: GetCarRequest): CarResponse {
        return CarResponse().apply {
            car = carRepository.find(request.serialNumber)
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCarRequest")
    @ResponsePayload
    fun createCar(@RequestPayload request: CreateCarRequest): CarResponse {
        return CarResponse().apply {
            car = carRepository.create(request)
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCarRequest")
    @ResponsePayload
    fun deleteCar(@RequestPayload request: DeleteCarRequest): CarResponse {
        return CarResponse().apply {
            car = carRepository.delete(request.serialNumber)
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCarRequest")
    @ResponsePayload
    fun updateCar(@RequestPayload request: UpdateCarRequest): CarResponse {
        return CarResponse().apply {
            car = carRepository.update(request)
        }
    }
}