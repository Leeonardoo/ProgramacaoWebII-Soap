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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCarRequest")
    @ResponsePayload
    fun getCar(@RequestPayload request: GetCarRequest): GetCarResponse {
        return GetCarResponse().apply {
            car = carRepository.findCar(request.serialNumber)
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCarRequest")
    @ResponsePayload
    fun createCar(@RequestPayload request: CreateCarRequest): GetCarResponse {
        return GetCarResponse().apply {
            car = carRepository.addCar(request)
        }
    }
}