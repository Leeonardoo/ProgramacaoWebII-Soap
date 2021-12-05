package br.furb.progweb.soap.repository

import br.furb.progweb.soap.Car
import br.furb.progweb.soap.CreateCarRequest
import br.furb.progweb.soap.UpdateCarRequest
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class CarRepository {

    private val cars: MutableMap<Int, Car> = hashMapOf()

    @PostConstruct
    fun initData() {
        val car = Car().apply {
            serialNumber = 1
            make = "Teste"
            model = "Teste"
            type = "SUV"
            power = 999999
            seats = 10
            weight = 9992.2f
            year = 2029
            plate = "ASD1234"
        }
        val car2 = Car().apply {
            serialNumber = 2
            make = "Teste"
            model = "Teste"
            type = "SUV"
            power = 999999
            seats = 10
            weight = 9992.2f
            year = 2029
            plate = "ASD1234"
        }
        cars[car.serialNumber] = car
        cars[car2.serialNumber] = car2
    }

    fun create(request: CreateCarRequest): Car? {
        if (cars[request.car.serialNumber] != null) {
            throw IllegalArgumentException("Já existe um carro com o serialNumber informado")
        }

        validate(request.car)

        cars[request.car.serialNumber] = request.car
        return cars[request.car.serialNumber]
    }

    fun find(serialNumber: Int): Car? {
        if (cars[serialNumber] == null) {
            throw NoSuchElementException("Nenhum carro encontrado com o serialNumber informado")
        }

        return cars[serialNumber]
    }

    fun delete(serialNumber: Int): Car? {
        if (cars[serialNumber] == null) {
            throw NoSuchElementException("Nenhum carro encontrado com o serialNumber informado")
        }

        return cars.remove(serialNumber)
    }

    fun update(request: UpdateCarRequest): Car? {
        if (cars[request.car.serialNumber] == null) {
            throw NoSuchElementException("Nenhum carro encontrado com o serialNumber informado")
        }

        validate(request.car)

        cars[request.car.serialNumber] = request.car
        return cars[request.car.serialNumber]
    }

    private fun validate(car: Car?) {
        car ?: throw IllegalArgumentException("O campo car não pode estar vazio")

        with(car) {
            if (serialNumber < 0) {
                throw IllegalArgumentException("O serial não pode ser menor que 0")
            }

            if (make.isNullOrBlank()) {
                throw IllegalArgumentException("O campo make é obrigatório")
            }

            if (model.isNullOrBlank()) {
                throw IllegalArgumentException("O campo model é obrigatório")
            }

            if (type.isNullOrBlank()) {
                throw IllegalArgumentException("O campo type é obrigatório")
            }

            if (power < 1) {
                throw IllegalArgumentException("A potência não pode ser menor que 1")
            }
            if (seats < 1) {
                throw IllegalArgumentException("O número de lugares não pode ser menor que 1")
            }
            if (weight < 1) {
                throw IllegalArgumentException("O peso não pode ser menor que 1")
            }

            if (year < 1886) {
                throw IllegalArgumentException("Ano inválido")
            }

            if (plate.isNullOrBlank()) {
                throw IllegalArgumentException("O campo plate é obrigatório")
            }
        }
    }
}
