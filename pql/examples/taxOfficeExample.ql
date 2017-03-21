        form taxOfficeExample {
            "Did you buy a house in 2010?" hasBought: boolean
            if(hasBought){
                "Did you buy a house in 2010?" hasCar: boolean
            }
            else {
                "Did you buy a house in 2010?" hasBike: boolean
            }
            "Has house" hasHouse: boolean = hasCar
            "Has second house" hasHouse2: boolean = hasBike

        }