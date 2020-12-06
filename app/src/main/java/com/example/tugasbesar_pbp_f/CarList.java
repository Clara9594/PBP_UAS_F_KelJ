package com.example.tugasbesar_pbp_f;

import java.util.ArrayList;

public class CarList {
    public ArrayList<Car> CAR;
    public CarList(){
        CAR = new ArrayList();
        CAR.add(SIGRA);
        CAR.add(JAZZ);
        CAR.add(INNOVA);
        CAR.add(FREED);
        CAR.add(AVANZA);
    }

    public static final Car SIGRA = new Car("SUV", "Daihatsu SIGRA",
            5, 2, "Full to Full", 850000,
            "https://d2pa5gi5n2e1an.cloudfront.net/id/images/car_models/Daihatsu_Sigra/2/exterior/exterior_2L_1.jpg",
            "B9999AB");

    public static final Car JAZZ = new Car("SUV", "Honda JAZZ",
            4, 2, "Full to Full", 950000,
            "https://www.honda-indonesia.com/uploads/images/models/colors/FGPohiLM4Vmi6ynarEAM_honda_id__0042_jazzorenge.png",
            "AB6789PO");

    public static final Car INNOVA = new Car("SUV", "Toyota INNOVA",
            5, 2, "Full to Full", 1000000,
            "https://d2pa5gi5n2e1an.cloudfront.net/id/images/car_models/Toyota_Innova/2/main/L_1.jpg",
            "AD1234KL");

    public static final Car FREED = new Car("SUV", "Honda FREED",
            5, 2, "Full to Full", 1000000,
            "https://otodriver.com/image/load/749/421/gallery/honda-freed-2014-indonesia-24871.jpg",
            "H5643HJ");

    public static final Car AVANZA = new Car("SUV", "TOYOTA AVANZA",
            5, 2, "Full to Full", 900000,
            "https://d2pa5gi5n2e1an.cloudfront.net/id/images/car_models/Toyota_Avanza/4/main/Toyota_Avanza_L_1.jpg",
            "AG1523BN");
}
