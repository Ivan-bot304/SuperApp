import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String[] inputData = {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300", "C200_1-100-750", "C300_1-32-15"};

        Car[] poolCar = new Car[inputData.length];

        for (int i=0;i<inputData.length;i++) {
            String code = inputData[i].split("_")[0];
            Integer number = Integer.parseInt(inputData[i].split("_")[1].split("-")[0]);
            Double mileage =  Double.parseDouble(inputData[i].split("_")[1].split("-")[1]);
            Integer additionInfo = 0;
            if(inputData[i].split("_")[1].split("-").length>2){
                additionInfo =  Integer.parseInt(inputData[i].split("_")[1].split("-")[2]);
            }
            poolCar[i]=new Car(code, number, mileage, additionInfo);
            //System.out.println(poolCar[i].toString());
        }

        /*общую стоимость расходов на ГСМ, так и расходы на каждый класс авто*/
        System.out.println(coastGSM(poolCar, ""));

        /*тип авто имеющий наибольшую стоимость расходов.
        тип авто имеющий наименьшую стоимость расходов
        вторым пареметром указать "MIN" или "MAX"*/
        System.out.println(expensesByType(poolCar, "MAX"));

        /*реализовать функции которые в разрезе каждого типа авто выводят
        информацию о каждом авто (тип, номер, пробег, доп. параметр), с
        сортировкой по пробегу и доп параметру. param = 1 - сортировка по пробегу,
        param = 2 - по доп параметру*/
        for (Car car:sortCar(poolCar,"C300",1)) {
            System.out.println(car.toString());
        }

    }

    static Double coastGSM(Car[] poolCar, String code){

        Double coast=0.;
        for (Car car:poolCar) {
            if(car.getCode().equals(code)||code.length()==0){
                coast = coast + (car.getMileage()/100)*car.getGasMileage()*car.getCoast();
            }
        }
        return coast;
    }

    static String expensesByType(Car[] poolCar, String arg){
        String[] poolTypeCar =new String[poolCar.length];
        int countType = 0;
        String result="";

        for (Car car:poolCar) {
            boolean flagSearch = false;
            for (int i = 0; i<=countType;i++){
                if(car.getCode().equals(poolTypeCar[i])){
                    flagSearch = true;
                }
            }
            if(!flagSearch){
                poolTypeCar[countType] = car.getCode();
                countType++;
            }
        }

        if(arg.equals("MAX")){
            result = poolTypeCar[0];
            double maxCoast = coastGSM(poolCar, result);
            for (String maxType:poolTypeCar) {
                if(maxType!=null){
                    if(maxCoast<coastGSM(poolCar, maxType)){
                        result = maxType;
                        maxCoast = coastGSM(poolCar, result);
                    }
                }
            }
        }

        if(arg.equals("MIN")){
            result = poolTypeCar[0];
            double minCoast = coastGSM(poolCar, result);
            for (String maxType:poolTypeCar) {
                if(maxType!=null){
                    if(minCoast>coastGSM(poolCar, maxType)){
                        result = maxType;
                        minCoast = coastGSM(poolCar, result);
                    }
                }
            }
        }

        return result;
    }

    static Car[] quickSort(Car[] array, int low, int high,int param) {
        if (array.length == 0)
            return array;

        if (low >= high)
            return array;

        int middle = low + (high - low) / 2;
        Double opora = 0.0;
        if(param == 1){
            opora = array[middle].getMileage();
        }
        else{
            opora = Double.parseDouble(array[middle].getAdditionInfo().toString());
        }

        int i = low, j = high;
        while (i <= j) {
            if(param == 1){
                while (array[i].getMileage() < opora) {
                    i++;
                }

                while (array[j].getMileage() > opora) {
                    j--;
                }
            }
            else{
                while (array[i].getAdditionInfo() < opora) {
                    i++;
                }

                while (array[j].getAdditionInfo() > opora) {
                    j--;
                }
            }

            if (i <= j) {
                Car temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j, param);

        if (high > i)
            quickSort(array, i, high, param);

        return array;
    }

    static Car[] sortCar(Car[] array, String code, int param) {
        Car[] tmp =new Car[array.length];
        int i=0;
        Car[] sortArray=quickSort(array,0,array.length-1,param);
        for (Car sortCar:sortArray) {
            if(sortCar.getCode().equals(code)){
                tmp[i]=sortCar;
                i++;
            }
        }
        Car[] result=new Car[i];
        i=0;
        for (Car copyCar:tmp) {
            if(copyCar!=null){
                result[i]=copyCar;
                i++;
            }
        }
        return result;
    }
}
