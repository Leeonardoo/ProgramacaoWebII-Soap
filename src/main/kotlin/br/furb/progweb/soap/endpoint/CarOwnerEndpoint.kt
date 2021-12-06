package br.furb.progweb.soap.endpoint

import br.furb.progweb.soap.*
import br.furb.progweb.soap.repository.CarOwnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
class CarOwnerEndpoint @Autowired constructor(
        private val carOwnerRepository: CarOwnerRepository
){

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCarsByOwnerRequest")
    @ResponsePayload
    fun getCarsByOwner(@RequestPayload request: GetCarsByOwnerRequest): CarsResponse {
        return CarsResponse().apply {
            cars.addAll(carOwnerRepository.getCarsByOwner(request.userId))
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCarOwnersRequest")
    @ResponsePayload
    fun getCarOwners(@RequestPayload request: GetCarOwnersRequest): UsersResponse {
        return UsersResponse().apply {
            users.addAll(carOwnerRepository.getCarOwners(request.carSerialNumber))
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addOwnerRequest")
    @ResponsePayload
    fun addCarOwner(@RequestPayload request: AddOwnerRequest): CarsResponse {
        return CarsResponse().apply {
            cars.addAll(carOwnerRepository.addOwner(request.userId, request.carSerialNumber))
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeCarOwnerRequest")
    @ResponsePayload
    fun removeCarOwner(@RequestPayload request: RemoveCarOwnerRequest): CarsResponse {
        return CarsResponse().apply {
            cars.addAll(carOwnerRepository.removeCarOwner(request.userId, request.carSerialNumber))
        }
    }
}