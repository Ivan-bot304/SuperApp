public class Car {
    private String code;
    private Integer number;
    private Double mileage;
    private Integer additionInfo;
    private Double coast;
    private Double gasMileage;


    public  Car(String code, Integer number, Double mileage, Integer additionInfo){
        this.code = code;
        this.number = number;
        this.mileage = mileage;
        this.additionInfo = additionInfo;
        switch (code) {
            case "C100":
                this.coast = 46.10;
                this.gasMileage = 12.5;
                break;
            case "C200":
                this.coast = 47.50;
                this.gasMileage = 12.0;
                break;
            case "C300":
                this.coast = 48.90;
                this.gasMileage = 11.5;
                break;
            case "C400":
                this.coast = 48.90;
                this.gasMileage = 20.0;
                break;
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "code='" + code + '\'' +
                ", number=" + number +
                ", mileage=" + mileage +
                ", additionInfo=" + additionInfo +
               /* ", coast=" + coast +
                ", gasMileage=" + gasMileage +*/
                '}';
    }

    public Double getCoast() {
        return coast;
    }

    public Double getGasMileage() {
        return gasMileage;
    }

    public Integer getAdditionInfo() {
        return additionInfo;
    }

    public Double getMileage() {
        return mileage;
    }

    public Integer getNumber() {
        return number;
    }

    public String getCode() {
        return code;
    }
}
