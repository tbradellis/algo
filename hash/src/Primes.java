public class Primes {

    public static boolean isPrime(int number){

        if(number ==2 | number == 1){
            return true;
        }
        if(number % 2 == 0){
            return false;
        }
        for(int i = 3; i < Math.sqrt(number); i=i+2){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
    //return the next prime number after requested array size. so the user doesn't
    //have to figure out a prime number
    public static int nextPrime(int n){
        while(!isPrime(n)){
            n += 1;
        }
        return n;
    }

}
