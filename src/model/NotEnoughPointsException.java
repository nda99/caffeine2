package model;

public class NotEnoughPointsException extends Exception {
    public NotEnoughPointsException(int points, int toRedeem){
        System.out.println("Not enough points, this user has only " + points +
                " points and you are trying to redeem more (" + toRedeem +")");
    }
}
