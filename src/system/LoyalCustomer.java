package system;

public class LoyalCustomer extends User {

    private String membershipNo;
    private int points;

    public LoyalCustomer(String membershipNo){
        this.membershipNo = membershipNo;
        this.points = 0;
    }

    public LoyalCustomer(String membershipNo, int points){
        this.membershipNo = membershipNo;
        this.points = points;
    }

    public void addPoints(int pointsToAdd){
        this.points += pointsToAdd;
    }

    public void redeemPoints(int pointsToRedeem) throws NotEnoughPointsException{
        if(pointsToRedeem>points){
            throw new NotEnoughPointsException(this.points, pointsToRedeem);
        }
        else {
            this.points -= pointsToRedeem;
        }
    }

    public int getPoints() {
        return points;
    }

    public String getMembershipNo() {
        return membershipNo;
    }
}
