# drools
## Model:
	vehicle:
	  age
	  manufacturer
	  engine
	  gearbox
	  mileage
	
	customer:
	  age
	  yearsAsADriver
	  annualMileage
	  usage	
	  
	results:
	  carGroup
	  manufacturerGroup
	  engineGroup
	  gearboxGroup
	  mileageGroup
	  driverGroup
	  vehicleGroup
	  customerGroup
	  price
	  
## Rules:	
### Car Group:
	vehicle.age == 0 -> carGroup = 0 - new car
	vehicle.age <= 5 -> carGroup = 1 - young car
	vehicle.age <= 10 -> carGroup = 2 - used car
	vehicle.age <= 15 -> carGroup = 3 - old car
### Brand Group:
	vehicle.manufacturer == TOYOTA -> manufacturerGroup = 0
	vehicle.manufacturer == LEXUS -> manufacturerGroup = 0
	vehicle.manufacturer == AUDI -> manufacturerGroup = 0
	vehicle.manufacturer == BMW -> manufacturerGroup = 0
	vehicle.manufacturer == VOLKSWAGEN -> manufacturerGroup = 1
	vehicle.manufacturer == OPEL -> manufacturerGroup = 1
	vehicle.manufacturer == RENAULT -> manufacturerGroup = 2
	vehicle.manufacturer == NISSAN -> manufacturerGroup = 2
	vehicle.manufacturer == DACIA -> manufacturerGroup = 2
	vehicle.manufacturer == SKODA -> manufacturerGroup = 3
	vehicle.manufacturer == LADA -> manufacturerGroup = 3
	vehicle.manufacturer == OTHER -> manufacturerGroup = 4
### Engine Group:
	vehicle.engine == ELECTRIC -> engineGroup = 0
	vehicle.engine == HYDROGEN -> engineGroup = 1
	vehicle.engine == GASOLINA -> engineGroup = 2
	vehicle.engine == DIESEL -> engineGroup = 2
### Gearbox Group:
	vehicle.gearbox == MANUAL -> gearboxGroup = 0
	vehicle.gearbox == AUTOMATIC -> gearboxGroup = 1
### Mileage Group:
	vehicle.mileage == 0 -> mileageGroup = 0
	vehicle.mileage < 50000 -> mileageGroup = 1
	vehicle.mileage < 100000 -> mileageGroup = 2
	vehicle.mileage < 175000 -> mileageGroup = 3
	vehicle.mileage < 250000 -> mileageGroup = 4
	vehicle.mileage >= 250000 -> mileageGroup = 5
### Driver Group:
	customer.age < 24 && customer.yearsAsADriver < 3 -> driverGroup = 0
	customer.age < 24 && customer.yearsAsADriver >= 3 -> driverGroup = 1
	customer.age < 30 && customer.yearsAsADriver < 3 -> driverGroup = 1
	customer.age < 30 && customer.yearsAsADriver >= 3 -> driverGroup = 2
	customer.age < 60 && customer.yearsAsADriver < 3 -> driverGroup = 3
	customer.age < 60 && customer.yearsAsADriver >= 3 -> driverGroup = 4
	customer.age < 70 && customer.yearsAsADriver >= 3 -> driverGroup = 3
	customer.age < 70 && customer.yearsAsADriver < 3 -> driverGroup = 2
	customer.age < 75 && customer.yearsAsADriver >= 3 -> driverGroup = 2
	customer.age < 75 && customer.yearsAsADriver < 3 -> driverGroup = 1
	customer.age >= 75 -> driverGroup = 0
### Usage Group
	customer.usage == private && customer.annualMileage < 10000 -> usageGroup = 0
	customer.usage == private && customer.annualMileage >= 10000 -> usageGroup = 1
	customer.usage == commecrial && customer.annualMileage < 20000 -> usageGroup = 0
	customer.usage == commecrial && customer.annualMileage < 30000 -> usageGroup = 1
	customer.usage == commecrial && customer.annualMileage >= 30000 -> usageGroup = 2
### Vehicle Group
	results.carGroup == 0 && results.manufacturerGroup == 0 && results.engineGroup == 0 && results.gearboxGroup == 0 && mileageGroup == 0 -> vehicleGroup = 0
	results.carGroup == 0 && results.manufacturerGroup == 0 && results.engineGroup == 0 && results.gearboxGroup == 0 -> vehicleGroup = 0
	results.carGroup == 0 && results.manufacturerGroup == 0 && results.engineGroup == 0 && results.gearboxGroup == 1 -> vehicleGroup = 1
	results.carGroup == 0 && results.manufacturerGroup == 0 && results.engineGroup == 1 && results.gearboxGroup == 0 -> vehicleGroup = 1
	results.carGroup == 0 && results.manufacturerGroup == 0 && results.engineGroup == 2 && results.gearboxGroup == 0 -> vehicleGroup = 1
	results.carGroup == 0 && results.manufacturerGroup == 0 && results.engineGroup == 1 && results.gearboxGroup == 1 -> vehicleGroup = 1
	results.carGroup == 0 && results.manufacturerGroup == 0 && results.engineGroup == 2 && results.gearboxGroup == 1 -> vehicleGroup = 2
	results.carGroup == 0 && results.manufacturerGroup == 1 -> vehicleGroup = 1
	results.carGroup == 0 && results.manufacturerGroup == 2 -> vehicleGroup = 1
	results.carGroup == 0 && results.manufacturerGroup == 3 -> vehicleGroup = 1
	results.carGroup == 0 && results.manufacturerGroup == 4 -> vehicleGroup = 2
	results.carGroup == 1 && mileageGroup < 2 -> vehicleGroup = 2
	results.carGroup == 1 && mileageGroup < 3 -> vehicleGroup = 3
	results.carGroup == 1 && mileageGroup >= 3 -> vehicleGroup = 4
	results.carGroup == 2 && mileageGroup < 3  && engineGroup > 2 -> vehicleGroup = 3
	results.carGroup == 2 && mileageGroup < 3  && engineGroup <= 2 -> vehicleGroup = 4
	results.carGroup == 2 && mileageGroup >= 3 -> vehicleGroup = 4
	results.carGroup == 3 && mileageGroup < 4 -> vehicleGroup = 4
	results.carGroup == 3 && mileageGroup >= 4 && gearbox == 1 -> vehicleGroup = 4
	results.carGroup == 3 && mileageGroup >= 4 && gearbox == 2 -> vehicleGroup = 5
### Customer Group
	results.driverGroup < 3 && results.usageGroup < 1 -> customerGroup = 0
	results.driverGroup < 3 && results.usageGroup >= 1 -> customerGroup = 1
	results.driverGroup >= 3 && results.usageGroup < 2 -> customerGroup = 2
	results.driverGroup >= 3 && results.usageGroup >= 2 -> customerGroup = 3
### Price
	results.vehicleGroup == 0 && results.customerGroup == 0 -> price = 100
	results.vehicleGroup == 0 && results.customerGroup == 1 -> price = 110
	results.vehicleGroup == 0 && results.customerGroup == 2 -> price = 125
	results.vehicleGroup == 0 && results.customerGroup == 3 -> price = 150
	results.vehicleGroup == 1 && results.customerGroup == 0 -> price = 210
	results.vehicleGroup == 1 && results.customerGroup == 1 -> price = 240
	results.vehicleGroup == 1 && results.customerGroup == 2 -> price = 280
	results.vehicleGroup == 1 && results.customerGroup == 3 -> price = 300
	results.vehicleGroup == 2 && results.customerGroup == 0 -> price = 285
	results.vehicleGroup == 2 && results.customerGroup == 1 -> price = 295
	results.vehicleGroup == 2 && results.customerGroup == 2 -> price = 310
	results.vehicleGroup == 2 && results.customerGroup == 3 -> price = 330
	results.vehicleGroup == 3 && results.customerGroup == 0 -> price = 300
	results.vehicleGroup == 3 && results.customerGroup == 1 -> price = 320
	results.vehicleGroup == 3 && results.customerGroup == 2 -> price = 345
	results.vehicleGroup == 3 && results.customerGroup == 3 -> price = 355
	results.vehicleGroup == 4 && results.customerGroup == 0 -> price = 315
	results.vehicleGroup == 4 && results.customerGroup == 1 -> price = 340
	results.vehicleGroup == 4 && results.customerGroup == 2 -> price = 365
	results.vehicleGroup == 4 && results.customerGroup == 3 -> price = 380
	results.vehicleGroup == 5 && results.customerGroup == 0 -> price = 320
	results.vehicleGroup == 5 && results.customerGroup == 1 -> price = 350
	results.vehicleGroup == 5 && results.customerGroup == 2 -> price = 375
	results.vehicleGroup == 5 && results.customerGroup == 3 -> price = 405
